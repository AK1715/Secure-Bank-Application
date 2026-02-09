package com.bankApplication.bank_application.service.impl;

import com.bankApplication.bank_application.dto.TransactionDto;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    void savedTransaction(TransactionDto transactionDto);
}
