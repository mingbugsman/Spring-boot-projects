package com.NovelBookOnline.NovelBookOnline.Service.Impl;


import com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth.RegisterRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Request.User.UserUpdateRequest;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.User.UserDetailResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.User.UserSummaryResponse;
import com.NovelBookOnline.NovelBookOnline.DTO.Response.User.UserUpdateResponse;
import com.NovelBookOnline.NovelBookOnline.Entity.Role;
import com.NovelBookOnline.NovelBookOnline.Entity.User;
import com.NovelBookOnline.NovelBookOnline.Enum.TypeRole;
import com.NovelBookOnline.NovelBookOnline.Exception.ApplicationException;
import com.NovelBookOnline.NovelBookOnline.Exception.ErrorCode;
import com.NovelBookOnline.NovelBookOnline.Mapper.CustomMapper.CustomerMappingHelper;
import com.NovelBookOnline.NovelBookOnline.Mapper.UserMapper;
import com.NovelBookOnline.NovelBookOnline.Repository.IRoleRepository;
import com.NovelBookOnline.NovelBookOnline.Repository.IUserRepository;
import com.NovelBookOnline.NovelBookOnline.Service.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUserService {
    CustomerMappingHelper customerMappingHelper;
    UserMapper userMapper;
    IUserRepository userRepository;
    IRoleRepository roleRepository;

    @Override
    public UserDetailResponse getProfileUserById(String id) {
        return customerMappingHelper.toUserDetail(userRepository.findUserById(id));
    }

    @Override
    public UserSummaryResponse getSummaryUser(String id) {
        return customerMappingHelper.toSummaryUser(userRepository.findUserById(id));
    }

    @Override
    public void createNewUser(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ApplicationException(ErrorCode.EMAIL_EXISTED);
        }
        User user = userMapper.toEntity(request);
        user.setUsername("User"+Math.floor(Math.random()*1000+1000));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        List<Role> roles = new ArrayList<>();
        Role role = roleRepository.findByTypeRole(TypeRole.USER);
        roles.add(role);
        user.setRoles(new HashSet<>(roles));

        userRepository.save(user);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or #id.equals(authentication.name)")
    public UserUpdateResponse updateUser(String id, UserUpdateRequest request) throws IOException {
        User user = userRepository.findUserById(id);

        userMapper.updateUser(user, request);
        if (request.getUserImageData() != null) {
            var dataImage = request.getUserImageData().getBytes();
            user.setUserImageData(dataImage);
        }

        userRepository.save(user);
        return customerMappingHelper.toUserUpdateResponse(user);

    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or #id.equals(authentication.name)")
    public void deleteUser(String id) {
        String s = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserById(id);
        userRepository.deleteUser(user);
    }
}
