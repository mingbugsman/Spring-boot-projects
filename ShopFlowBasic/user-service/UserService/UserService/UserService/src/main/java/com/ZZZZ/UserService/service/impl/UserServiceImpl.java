package com.ZZZZ.UserService.service.impl;


import com.ZZZZ.UserService.DTO.Request.UserCreationRequest;
import com.ZZZZ.UserService.DTO.Request.UserUpdateInformationRequest;
import com.ZZZZ.UserService.DTO.Response.UserResponse;
import com.ZZZZ.UserService.base.exception.ApplicationException;
import com.ZZZZ.UserService.base.exception.ErrorCode;
import com.ZZZZ.UserService.base.util.Generator;
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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepo userRepo;
    UserMapper userMapper;
    UserEventProducer userEventProducer;
    RedisTemplate<String, String> redisTemplate;

    @Override
    public UserResponse createUser(UserCreationRequest request) {

        if (userRepo.existsUserByEmail(request.getEmail())) {
            throw new ApplicationException(ErrorCode.EMAIL_EXISTED);
        }
        User user = userMapper.toUser(request);
        user.setUsername( Generator.generatorUsername());

        // hash password
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepo.save(user);
        userEventProducer.sendUserOTPEmailEvent(new EmailRequest(request.getEmail(), user.getUsername()));
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUser(String id, UserUpdateInformationRequest request) {
        User foundUser = userRepo.getUser(id);
        userMapper.updateUser(foundUser, request);
        userRepo.save(foundUser);
        return userMapper.toUserResponse(foundUser);
    }

    @Override
    public void softDeleteUser(String id) {
        User foundUser = userRepo.getUser(id);
        userRepo.softDeleteUser(foundUser);

    }

    @Override
    public void absoluteDeleteUser(String id) {
        User foundUser = userRepo.getUser(id);
        userRepo.deleteUserByAdmin(foundUser);
    }

    @Override
    public UserResponse getUser(String id) {
        User foundUser = userRepo.getUser(id);
        return userMapper.toUserResponse(foundUser);
    }

    @Override
    public Page<UserResponse> getAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortBy));
        return userRepo.getAll(pageable).map(userMapper::toUserResponse);
    }

    @Override
    public boolean verifyEmail(String email, String otp) {
        String storedOTP = redisTemplate.opsForValue().get("OTP:"+email);
        User user = userRepo.getUserByEmail(email);
        if (storedOTP != null && storedOTP.equals(otp)) {
            if (user == null) {
                throw new ApplicationException(ErrorCode.EMAIL_NOT_EXISTED);
            } else {
                user.setVerified(true);
                userRepo.save(user);
                redisTemplate.delete("OTP:"+email);
            }
        } else {
            throw new ApplicationException(ErrorCode.OTP_IS_INVALID);
        }
        userEventProducer.sendUserCreatedEvent(new EmailRequest(email, user.getUsername()));
        return true;
    }
}