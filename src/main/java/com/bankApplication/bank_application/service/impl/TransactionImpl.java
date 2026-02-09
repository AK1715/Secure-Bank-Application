package com.bankApplication.bank_application.service.impl;

import com.bankApplication.bank_application.dto.TransactionDto;
import com.bankApplication.bank_application.entity.Transaction;
import com.bankApplication.bank_application.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionImpl implements TransactionService{

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void savedTransaction(TransactionDto transactionDto){
        Transaction transaction = Transaction.builder()
                .transactionType(transactionDto.getTransactionType())
                .accountNumber(transactionDto.getAccountNumber())
                .amount(transactionDto.getAmount())
                .status("SUCCESS")
                .build();

        transactionRepository.save(transaction);
        System.out.println("Transaction Saved SuccessFully");
    }
}
