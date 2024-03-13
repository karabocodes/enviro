package org.enviro.assessment.grad001.karabokhunou.investments.controller;

import jakarta.validation.Valid;
import org.enviro.assessment.grad001.karabokhunou.investments.model.Investor;
import org.enviro.assessment.grad001.karabokhunou.investments.model.WithdrawalNotice;
import org.enviro.assessment.grad001.karabokhunou.investments.service.InvestorService;
import org.enviro.assessment.grad001.karabokhunou.investments.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class InvestorController {

    @Autowired
    private InvestorService investorService;

    @Autowired
    private WithdrawalService withdrawalService;

    @GetMapping("/investors")
    public ResponseEntity<List<Investor>> getAllInvestors() {
        List<Investor> investors = investorService.getAllInvestors();
        return new ResponseEntity<>(investors, HttpStatus.OK);
    }

    @PostMapping("/withdrawal-notices")
    public ResponseEntity<String> createWithdrawalNotice(@Valid @RequestBody WithdrawalNotice withdrawalNotice) {
        // Validate withdrawal notice
        boolean isValid = withdrawalService.validateWithdrawalNotice(withdrawalNotice);
        if (!isValid) {
            return new ResponseEntity<>("Withdrawal notice validation failed", HttpStatus.BAD_REQUEST);
        }
        // Create withdrawal notice
        withdrawalService.createWithdrawalNotice(withdrawalNotice);
        return new ResponseEntity<>("Withdrawal notice created successfully", HttpStatus.CREATED);
    }
}
