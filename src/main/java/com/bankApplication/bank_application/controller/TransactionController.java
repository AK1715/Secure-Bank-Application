package com.bankApplication.bank_application.controller;

import com.bankApplication.bank_application.entity.Transaction;
import com.bankApplication.bank_application.service.impl.BankStatement;
import com.itextpdf.text.DocumentException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/bankStatement")
@AllArgsConstructor
@Tag(name = "Transaction Management API's")
public class TransactionController {

    private BankStatement bankStatement;

    @GetMapping
    public List<Transaction> generateBankStatement(@RequestParam String accountNumber,
                                                   @RequestParam String startDate,
                                                   @RequestParam String endDate) throws FileNotFoundException, DocumentException {
        return bankStatement.generateStatement(accountNumber, startDate, endDate);
    }

}
