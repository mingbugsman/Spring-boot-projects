package com.NovelBookOnline.NovelBookOnline.Controller;

import com.NovelBookOnline.NovelBookOnline.DTO.Request.User.UserUpdateRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.ApiResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.User.UserDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.User.UserSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.User.UserUpdateResponse;
import com.NovelBookOnline.NovelBookOnline.Service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService service;

    @GetMapping("/profile/{id}")
    public ApiResponse<UserDetailResponse> getProfileUserById(@PathVariable String id) {
        return ApiResponse.<UserDetailResponse>builder()
                .result(service.getProfileUserById(id))
                .build();
    }
    @GetMapping("/summary/{id}")
    public ApiResponse<UserSummaryResponse> getSummaryUser(@PathVariable String id){
        return ApiResponse.<UserSummaryResponse>builder()
                .result(service.getSummaryUser(id))
                .build();
    }

    @PutMapping(value = "/setup-profile/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<UserUpdateResponse> updateUser(@PathVariable String id, @ModelAttribute @Valid UserUpdateRequest request) throws IOException{
        System.out.println("update user...");
        return ApiResponse.<UserUpdateResponse>builder()
                .result(service.updateUser(id, request))
                .build();
    }
    @DeleteMapping("/{userId}")
    public ApiResponse<String> deleteUser(@PathVariable String userId){
        service.deleteUser(userId);
        return ApiResponse.<String>builder()
                .result("successfully deleted user id : " + userId)
                .build();
    }
}
