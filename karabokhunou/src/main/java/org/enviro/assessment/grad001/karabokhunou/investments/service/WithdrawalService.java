package org.enviro.assessment.grad001.karabokhunou.investments.service;

import org.enviro.assessment.grad001.karabokhunou.investments.model.WithdrawalNotice;

public interface WithdrawalService {
    void createWithdrawalNotice(Long investorId, Long productId, double withdrawalAmount);

    void createWithdrawalNotice(WithdrawalNotice withdrawalNotice);

    boolean validateWithdrawalNotice(WithdrawalNotice withdrawalNotice);
    // Other withdrawal related methods
}
