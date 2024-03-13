package org.enviro.assessment.grad001.karabokhunou.investments.service;

import jakarta.validation.ValidationException;
import org.enviro.assessment.grad001.karabokhunou.investments.model.Investor;
import org.enviro.assessment.grad001.karabokhunou.investments.model.Product;
import org.enviro.assessment.grad001.karabokhunou.investments.model.WithdrawalNotice;
import org.enviro.assessment.grad001.karabokhunou.investments.repository.InvestorRepository;
import org.enviro.assessment.grad001.karabokhunou.investments.repository.ProductRepository;
import org.enviro.assessment.grad001.karabokhunou.investments.repository.WithdrawalNoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WithdrawalServiceImpl implements WithdrawalService {

    @Autowired
    private InvestorRepository investorRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WithdrawalNoticeRepository withdrawalNoticeRepository;

    @Override
    public void createWithdrawalNotice(Long investorId, Long productId, double withdrawalAmount) {
        // Retrieve investor and product
        Investor investor = investorRepository.findById(investorId).orElseThrow(() -> new ChangeSetPersister.NotFoundException("Investor not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new ChangeSetPersister.NotFoundException("Product not found"));

        // Business logic for withdrawal
        if (withdrawalAmount > product.getBalance()) {
            throw new ValidationException("Withdrawal amount exceeds product balance");
        }

        // Other business logic checks (e.g., retirement age)

        // Create withdrawal notice
        WithdrawalNotice withdrawalNotice = new WithdrawalNotice();
        withdrawalNotice.setInvestor(investor);
        withdrawalNotice.setProduct(product);
        withdrawalNotice.setAmount(withdrawalAmount);
        withdrawalNotice.setDateSubmitted(new Date());
        withdrawalNotice.setStatus("Pending");

        withdrawalNoticeRepository.save(withdrawalNotice);
    }

    // Other methods
}
