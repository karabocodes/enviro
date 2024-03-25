package org.enviro.assessment.grad001.karabokhunou.investments.service;

import org.enviro.assessment.grad001.karabokhunou.investments.dto.TransactionDto;
import org.enviro.assessment.grad001.karabokhunou.investments.entity.Transaction;
import org.enviro.assessment.grad001.karabokhunou.investments.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionImpl implements TransactionService{

    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public void saveTransaction(TransactionDto transactionDto) {
        Transaction transaction = Transaction.builder()
                .transactionType (transactionDto.getTransactionType())
                .accountNumber (transactionDto.getAccountNumber())
                . amount (transactionDto.getAmount())
                .status("SUCCESS")
                .build();
        transactionRepository.save(transaction);
        System.out.println("Transaction saved successfully");
    }
}
