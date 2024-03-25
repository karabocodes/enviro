package org.enviro.assessment.grad001.karabokhunou.investments.repository;

import org.enviro.assessment.grad001.karabokhunou.investments.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
