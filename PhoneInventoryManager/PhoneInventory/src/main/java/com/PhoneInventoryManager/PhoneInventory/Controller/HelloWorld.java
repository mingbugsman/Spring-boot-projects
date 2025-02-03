package com.PhoneInventoryManager.PhoneInventory.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("/HelloWorld")
public class HelloWorld {


    @GetMapping("/Hi")
    public ResponseEntity<String> Hi() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello world");
    }

}
