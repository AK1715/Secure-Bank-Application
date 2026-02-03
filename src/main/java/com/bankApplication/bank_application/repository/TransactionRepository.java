package com.bankApplication.bank_application.repository;

import com.bankApplication.bank_application.entity.Transaction;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<@NonNull Transaction, @NonNull String> {
}
