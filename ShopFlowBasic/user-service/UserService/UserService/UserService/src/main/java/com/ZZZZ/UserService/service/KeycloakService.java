package com.ZZZZ.UserService.service;

import com.ZZZZ.UserService.DTO.Request.UserCreationRequest;
import com.ZZZZ.UserService.base.exception.ApplicationException;
import com.ZZZZ.UserService.base.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.ZZZZ.UserService.base.util.Generator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KeycloakService {

    private final RestTemplate restTemplate;

    @Value("${keycloak.server-url}")
    private String keycloakServerUrl;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.admin-username}")
    private String adminUsername;
    @Value("${keycloak.admin-password}")
    private String adminPassword;

    // 1. Lấy token admin
    private String getAdminToken() {
        System.out.println("KEYCLOAK SERVICE:: GET ADMIN TOKEN");
        String tokenUrl = keycloakServerUrl + "/realms/master/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "password");
        form.add("client_id", "admin-cli");
        form.add("username", adminUsername);
        form.add("password", adminPassword);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);
        return response.getBody().get("access_token").toString();
    }

    // 2. Tạo user
    public String createUserInKeycloak(UserCreationRequest request) {
        System.out.println("KEYCLOAK SERVICE CREATE USER IN KEYCLOAK");
        String token = getAdminToken();

        // 1. Tạo user
        String url = keycloakServerUrl + "/admin/realms/" + realm + "/users";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String lastname = Generator.generatorUsername();
        Map<String, Object> user = new HashMap<>();
        user.put("username", request.getEmail());
        user.put("email", request.getEmail());
        user.put("firstName", lastname);
        user.put("lastName", "user");
        user.put("enabled", true);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(user, headers);

        var object= restTemplate.postForEntity(url, entity, Void.class);
        System.out.println("Result:::" + object);
        // 2. Lấy user ID
        String userId = findUserIdByUsername(request.getEmail(), token);

        // 3. Gán role USER
        assignRealmRolesToUser(userId, List.of("USER"), token);

        // 4. Set mật khẩu cho user
        resetPasswordForUser(userId, request.getPassword(), token);

        return userId;
    }

    // 3. Tìm user ID theo email (username)
    private String findUserIdByUsername(String username, String token) {

        String url = keycloakServerUrl + "/admin/realms/" + realm + "/users?username=" + username;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, entity, List.class);
        List<Map<String, Object>> users = response.getBody();

        if (users != null && !users.isEmpty()) {
            return users.getFirst().get("id").toString();
        }
        throw new RuntimeException("User not found after creation");
    }
    private void assignRealmRolesToUser(String userId, List<String> roles, String token) {
        System.out.println("KEYCLOAK SERVICE ASSIGN REALM ROLES TO USER");
        String url = keycloakServerUrl + "/admin/realms/" + realm + "/users/" + userId + "/role-mappings/realm";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Mỗi role cần tên + id (id là của role trong Keycloak)
        // Nếu bạn biết trước role name thì nên lấy role info từ Keycloak
        // Ví dụ với role "USER":
        List<Map<String, Object>> rolesToAdd = roles.stream().map(role -> {
            Map<String, Object> map = new HashMap<>();
            map.put("name", role);
            map.put("clientRole", false);
            map.put("composite", false);
            map.put("containerId", realm); // realm name
            map.put("id", getRealmRoleId(role, token)); // thêm id bằng hàm hỗ trợ
            return map;
        }).toList();

        HttpEntity<?> entity = new HttpEntity<>(rolesToAdd, headers);
        System.out.println("Assign realm role to user::" + entity);
        restTemplate.postForEntity(url, entity, Void.class);
    }

    private String getRealmRoleId(String roleName, String token) {
        System.out.println("KEYCLOAK SERVICE GET REALM ROLE ID");
        String url = keycloakServerUrl + "/admin/realms/" + realm + "/roles/" + roleName;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        return response.getBody().get("id").toString();
    }

    private void resetPasswordForUser(String userId, String password, String token) {
        System.out.println("Keycloak service reset password");
        String url = keycloakServerUrl + "/admin/realms/" + realm + "/users/" + userId + "/reset-password";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> credential = new HashMap<>();
        credential.put("type", "password");
        credential.put("value", password);
        credential.put("temporary", false); // false = không yêu cầu đổi pass sau login

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(credential, headers);
        restTemplate.exchange(url, HttpMethod.PUT, entity, Void.class);
    }


    public Map<String,Object> loginAndGetTokens(String email, String password) {
        String url = "http://localhost:8181/realms/"+ realm +"/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("client_id", "gateway-client");
        body.add("client_secret", "X2wyPoIg59fp2cnIeKZ5D2fqhsTktSiA");
        body.add("username", email);
        body.add("password", password);
        body.add("scope", "openid profile email");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
        return response.getBody();
    }

    public Map<String, Object> refreshAccessToken(String refreshToken) {
        String url = "http://localhost:8181/realms/shopflow/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "refresh_token");
        body.add("client_id", "gateway-client");
        body.add("client_secret", "X2wyPoIg59fp2cnIeKZ5D2fqhsTktSiA");
        body.add("refresh_token", refreshToken);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
            return response.getBody();
        } catch (RuntimeException e) {
            throw new ApplicationException(ErrorCode.UNAUTHORIZED);
        }
    }

    public boolean logout(String refreshToken) {
        String url = "http://localhost:8181/realms/shopflow/protocol/openid-connect/logout";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("client_id", "gateway-client");
        form.add("client_secret", "X2wyPoIg59fp2cnIeKZ5D2fqhsTktSiA");
        form.add("refresh_token", refreshToken);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);

        try {
            restTemplate.postForEntity(url, request, String.class);
            return true;
        } catch (Exception e) {
            throw new ApplicationException(ErrorCode.UNAUTHORIZED);
        }
    }
}
