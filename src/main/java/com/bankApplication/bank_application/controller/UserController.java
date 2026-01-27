package com.bankApplication.bank_application.controller;

import com.bankApplication.bank_application.dto.BankResponse;
import com.bankApplication.bank_application.dto.UserRequest;
import com.bankApplication.bank_application.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public BankResponse createAccount(@RequestBody UserRequest userRequest){
        return userService.createAccount(userRequest);
    }
}
