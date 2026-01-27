package com.bankApplication.bank_application.service.impl;

import com.bankApplication.bank_application.dto.BankResponse;
import com.bankApplication.bank_application.dto.UserRequest;
import com.bankApplication.bank_application.entity.User;
import com.bankApplication.bank_application.utils.AccountUtils;

public class UserServiceImpl implements UserService{

    @Override
    public BankResponse createAccount(UserRequest userRequest){
        /*
        * Creating an account -  saving a new user into the db
        */

        User newUser = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .otherName(userRequest.getOtherName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())
                .stateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(AccountUtils.generateAccountNumber())
                .build();

        return null;
    }
}
