package com.ZZZZ.UserService.service.impl;


import com.ZZZZ.UserService.DTO.Request.UserCreationRequest;
import com.ZZZZ.UserService.DTO.Request.UserUpdateInformationRequest;
import com.ZZZZ.UserService.DTO.Response.UserResponse;
import com.ZZZZ.UserService.entity.User;
import com.ZZZZ.UserService.kafka.UserEventProducer;
import com.ZZZZ.UserService.mapper.UserMapper;
import com.ZZZZ.UserService.repository.UserRepo;
import com.ZZZZ.UserService.service.UserService;
import com.ZZZZ.commonDTO.EmailRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepo userRepo;
    UserMapper userMapper;
    UserEventProducer userEventProducer;

    @Override
    public UserResponse createUser(UserCreationRequest request) {

        if (userRepo.getUserByEmail(request.getEmail()) != null) {
            throw new RuntimeException("User is already existed");
        }
        User user = userMapper.toUser(request);
        userRepo.save(user);
        userEventProducer.sendUserCreatedEvent(new EmailRequest(request.getEmail(), "user001"));
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUser(String id, UserUpdateInformationRequest request) {
        User foundUser = userRepo.getUser(id);
        if (foundUser == null) {
            throw new RuntimeException("User is not existed");
        }
        userMapper.updateUser(foundUser, request);
        userRepo.save(foundUser);
        return userMapper.toUserResponse(foundUser);
    }

    @Override
    public void softDeleteUser(String id) {
        User foundUser = userRepo.getUser(id);
        if (foundUser == null) {
            throw new RuntimeException("User is not existed");
        }
        userRepo.softDeleteUser(foundUser);

    }

    @Override
    public void absoluteDeleteUser(String id) {
        User foundUser = userRepo.getUser(id);
        if (foundUser == null) {
            throw new RuntimeException("User is not existed");
        }
        userRepo.deleteUserByAdmin(foundUser);
    }

    @Override
    public UserResponse getUser(String id) {
        User foundUser = userRepo.getUser(id);
        if (foundUser == null) {
            throw new RuntimeException("User is not existed");
        }
        return userMapper.toUserResponse(foundUser);
    }

    @Override
    public Page<UserResponse> getAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortBy));
        return userRepo.getAll(pageable).map(userMapper::toUserResponse);
    }
}