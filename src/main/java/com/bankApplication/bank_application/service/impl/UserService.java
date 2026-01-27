package com.bankApplication.bank_application.service.impl;

import com.bankApplication.bank_application.dto.BankResponse;
import com.bankApplication.bank_application.dto.UserRequest;

public interface UserService {

    BankResponse createAccount(UserRequest userRequest);
}
