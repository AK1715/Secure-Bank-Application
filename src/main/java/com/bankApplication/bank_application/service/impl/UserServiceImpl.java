package com.bankApplication.bank_application.service.impl;

import com.bankApplication.bank_application.dto.*;
import com.bankApplication.bank_application.entity.User;
import com.bankApplication.bank_application.repository.UserRepository;
import com.bankApplication.bank_application.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    @Override
    public BankResponse createAccount(UserRequest userRequest){
        /*
        * Creating an account -  saving a new user into the db
        * check if user already has an account
        */

        if(userRepository.existsByEmail(userRequest.getEmail())){
            System.out.println("User Already Exists");
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        User newUser = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .otherName(userRequest.getOtherName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())
                .stateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountBalance(BigDecimal.ZERO)
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .alternativeNumber(userRequest.getAlternativeNumber())
                .status("ACTIVE")
                .build();

        User savedUser = userRepository.save(newUser);

        //Send email Alert
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(savedUser.getEmail())
                .subject("Your Bank Account was created successfully")
                .messageBody("Congratulations! Your Account Has Been Successfully Created..! \nYour Account Details: \n" +
                        "Account Name:" + savedUser.getFirstName() + " " + savedUser.getLastName() + "\nAccount Number: " + savedUser.getAccountNumber() + "\nPhone Number: " + savedUser.getPhoneNumber())
                .build();
        emailService.sendEmailAlert(emailDetails);

        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(savedUser.getAccountBalance())
                        .accountNumber(savedUser.getAccountNumber())
                        .accountName(savedUser.getFirstName() + " " + savedUser.getLastName() + " " + savedUser.getOtherName())
                        .build())
                .build();
    }

    // Balance Enquiry, name Enquiry, Credit, Debit, transfer

    @Override
    public BankResponse balanceEnquiry(EnquiryRequest request){
        // check if the provided account number exists in the db
        boolean isAccountExist = userRepository.existsByAccountNumber(request.getAccountNumber());
        if(!isAccountExist){
            System.out.println("User Not Exist");
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        User foundUser = userRepository.findByAccountNumber(request.getAccountNumber());
        System.out.println("User Founded");

        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_FOUND_CODE)
                .responseMessage(AccountUtils.ACCOUNT_FOUND_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(foundUser.getAccountBalance())
                        .accountNumber(request.getAccountNumber())
                        .accountName(foundUser.getFirstName() + " " + foundUser.getLastName())
                        .build())
                .build();

    }

    @Override
    public String nameEnquiry(EnquiryRequest request){
        boolean isAccountExist = userRepository.existsByAccountNumber(request.getAccountNumber());
        if(!isAccountExist){
            System.out.println("User Not Found");
            return AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE;
        }

        User foundUser = userRepository.findByAccountNumber(request.getAccountNumber());
        System.out.println("User Founded send response to the users");
        return foundUser.getFirstName() +" " + foundUser.getLastName();

    }

    @Override
    public BankResponse creditAccount(CreditDebitRequest request){
        // checking if the account exits
        boolean isAccountExist = userRepository.existsByAccountNumber(request.getAccountNumber());
        if(!isAccountExist){
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        User userToCredit = userRepository.findByAccountNumber(request.getAccountNumber());
        userToCredit.setAccountBalance(userToCredit.getAccountBalance().add(request.getAmount()));
        userRepository.save(userToCredit);

        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREDIT_CODE)
                .responseMessage(AccountUtils.ACCOUNT_CREDIT_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountName(userToCredit.getFirstName() + " " + userToCredit.getLastName())
                        .accountNumber(request.getAccountNumber())
                        .accountBalance(userToCredit.getAccountBalance())
                        .build())
                .build();
    }


}
