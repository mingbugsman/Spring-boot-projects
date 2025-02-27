package com.NovelBookOnline.NovelBookOnline.Service.Impl;


import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.RegisterRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.User.UserUpdateRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.User.UserDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.User.UserSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.User.UserUpdateResponse;
import com.NovelBookOnline.NovelBookOnline.Entity.User;
import com.NovelBookOnline.NovelBookOnline.Mapper.CustomMapper.CustomerMappingHelper;
import com.NovelBookOnline.NovelBookOnline.Mapper.UserMapper;
import com.NovelBookOnline.NovelBookOnline.Repository.IUserRepository;
import com.NovelBookOnline.NovelBookOnline.Service.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUserService {
    CustomerMappingHelper customerMappingHelper;
    UserMapper userMapper;
    IUserRepository userRepository;

    @Override
    public UserDetailResponse getProfileUserById(String id) {
        return customerMappingHelper.toUserDetail(userRepository.findUserById(id));
    }

    @Override
    public UserSummaryResponse getSummaryUser(String id) {
        return customerMappingHelper.toSummaryUser(userRepository.findUserById(id));
    }

    @Override
    public boolean createNewUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return false;
        }
        User user = userMapper.toEntity(request);
        userRepository.save(user);
        return true;
    }

    @Override
    public UserUpdateResponse updateUser(String id, UserUpdateRequest request) throws IOException {
        User user = userRepository.findUserById(id);

        userMapper.updateUser(user, request);
        if (request.getUserImageDate() != null) {
            var dataImage = request.getUserImageDate().getBytes();
            user.setUserImageData(dataImage);
        }
        return userMapper.toUpdateResponse(user);
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepository.findUserById(id);
        userRepository.deleteUser(user);
    }
}
