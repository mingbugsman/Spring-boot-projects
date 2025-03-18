package com.ZZZZ.UserService.service.impl;


import com.ZZZZ.UserService.DTO.Request.UserCreationRequest;
import com.ZZZZ.UserService.DTO.Request.UserUpdateInformationRequest;
import com.ZZZZ.UserService.DTO.Response.UserResponse;
import com.ZZZZ.UserService.entity.User;
import com.ZZZZ.UserService.mapper.UserMapper;
import com.ZZZZ.UserService.repository.UserRepo;
import com.ZZZZ.UserService.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepo userRepo;
    UserMapper userMapper;

    @Override
    public UserResponse createUser(UserCreationRequest request) {

        if (userRepo.getUserByEmail(request.getEmail()) != null) {
            throw new RuntimeException("User is already existed");
        }
        User user = userMapper.toUser(request);
        userRepo.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUser(UserUpdateInformationRequest request) {
        return null;
    }

    @Override
    public void softDeleteUser(String id) {

    }

    @Override
    public void absoluteDeleteUser(String id) {

    }

    @Override
    public UserResponse getUser(String id) {
        return null;
    }

    @Override
    public Page<UserResponse> getAll() {
        return null;
    }
}
