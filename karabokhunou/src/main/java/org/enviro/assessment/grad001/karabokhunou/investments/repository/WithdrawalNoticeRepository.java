package org.enviro.assessment.grad001.karabokhunou.investments.repository;

import org.enviro.assessment.grad001.karabokhunou.investments.model.WithdrawalNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawalNoticeRepository extends JpaRepository<WithdrawalNotice, Long> {
    // Add custom methods if needed
}