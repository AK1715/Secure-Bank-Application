package com.bankApplication.bank_application.service.impl;

import com.bankApplication.bank_application.dto.BankResponse;
import com.bankApplication.bank_application.dto.CreditDebitRequest;
import com.bankApplication.bank_application.dto.EnquiryRequest;
import com.bankApplication.bank_application.dto.UserRequest;

public interface UserService {

    BankResponse createAccount(UserRequest userRequest);

    BankResponse balanceEnquiry(EnquiryRequest request);

    String nameEnquiry(EnquiryRequest request);

    BankResponse creditAccount(CreditDebitRequest request);

}
