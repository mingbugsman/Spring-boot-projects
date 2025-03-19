package com.ZZZZ.UserService.controller;


import com.ZZZZ.UserService.DTO.Request.UserCreationRequest;
import com.ZZZZ.UserService.DTO.Request.UserUpdateInformationRequest;
import com.ZZZZ.UserService.DTO.Response.UserResponse;
import com.ZZZZ.UserService.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return ResponseEntity.ok(userService.getAll(page, size, sortBy));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserCreationRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateInformationUser(@PathVariable String id, @RequestBody @Valid UserUpdateInformationRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        userService.softDeleteUser(id);
        return ResponseEntity.ok("Successfully deleted user id : " + id);
    }

    @DeleteMapping("/by-admin/{id}")
    public ResponseEntity<String> deleteByAdmin(@PathVariable String id) {
        userService.absoluteDeleteUser(id);
        return ResponseEntity.ok("Successfully deleted user id : " + id);
    }

}
