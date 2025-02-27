package com.NovelBookOnline.NovelBookOnline.Controller;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.RegisterRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.User.UserUpdateRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.User.UserDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.User.UserSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.User.UserUpdateResponse;
import com.NovelBookOnline.NovelBookOnline.Service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService service;

    @GetMapping("/profile/{id}")
    ResponseEntity<UserDetailResponse> getProfileUserById(@PathVariable String id) {
        return ResponseEntity.ok(service.getProfileUserById(id));
    }
    @GetMapping("/summary/{id}")
    ResponseEntity<UserSummaryResponse> getSummaryUser(@PathVariable String id){
        return ResponseEntity.ok(service.getSummaryUser(id));
    }
    @PostMapping
    ResponseEntity<Boolean> createNewUser(@RequestBody @Valid RegisterRequest request){
        return ResponseEntity.ok(service.createNewUser(request));
    }
    @PutMapping("/setup-profile/{id}")
    ResponseEntity<UserUpdateResponse> updateUser(@PathVariable String id, @RequestBody @Valid UserUpdateRequest request) throws IOException{
        return ResponseEntity.ok(service.updateUser(id, request));
    }
    @DeleteMapping
    ResponseEntity<String> deleteUser(String id){
        service.deleteUser(id);
        return ResponseEntity.ok("Successfully deleted");
    }
}
