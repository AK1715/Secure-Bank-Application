package com.bankApplication.bank_application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountInfo {

    private String accountName;

    private String accountBalance;

    private String accountNumber;
}
