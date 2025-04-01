package com.ZZZZ.UserService;


import com.ZZZZ.UserService.DTO.Request.UserCreationRequest;
import com.ZZZZ.UserService.DTO.Request.UserUpdateInformationRequest;
import com.ZZZZ.UserService.DTO.Response.UserResponse;
import com.ZZZZ.UserService.Enum.SortType;
import com.ZZZZ.UserService.Helper.RandomGenerator;
import com.ZZZZ.UserService.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void createUser() {
        UserCreationRequest request = new UserCreationRequest("1250080116@sv.hcmunre.edu.vn", RandomGenerator.generateRandomPassword());
        UserResponse response = userService.createUser(request);
        System.out.println(response);
    }

    @Test
    public void updateUser() {
        UserUpdateInformationRequest request = new UserUpdateInformationRequest("ming bugs man",null);
        System.out.println(userService.updateUser("2fc36e4b-8355-4a52-9b05-c801756a7013", request));
    }

    @Test
    public void softDeleteUser() {
        userService.softDeleteUser("2fc36e4b-8355-4a52-9b05-c801756a7013");
    }

    @Test
    public void absolutelyDeleteUser() {
        userService.absoluteDeleteUser("2fc36e4b-8355-4a52-9b05-c801756a7013");
    }


    @Test
    public void getUser() {
        System.out.println(userService.getUser("2fc36e4b-8355-4a52-9b05-c801756a7013"));
    }


    @Test
    public void getAll() {
        int size = 10;
        for (int i = 0; i < 10; i++) {
            print(userService.getAll(i,size,"createdAt"));
        }
    }

    private void print(Page<UserResponse> users) {
        for (var user : users) {
            System.out.println(user);
        }
    }


    @Test
    public void verifyEmailTesting() {
        if(userService.verifyEmail("1250080116@sv.hcmunre.edu.vn","462917")) {
            System.out.println("Completed verified email");
        } else {
            System.out.println("Your email suck");
        }

    }
}

