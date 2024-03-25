package org.enviro.assessment.grad001.karabokhunou.investments.service;

import org.enviro.assessment.grad001.karabokhunou.investments.dto.TransactionDto;
import org.enviro.assessment.grad001.karabokhunou.investments.entity.Transaction;

public interface TransactionService {
    void saveTransaction(TransactionDto  transactionDto);
}
