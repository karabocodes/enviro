package org.enviro.assessment.grad001.karabokhunou.investments.controller;
import org.enviro.assessment.grad001.karabokhunou.investments.dto.AppResponse;
import org.enviro.assessment.grad001.karabokhunou.investments.dto.InvestorRequest;
import org.enviro.assessment.grad001.karabokhunou.investments.service.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/investor")
public class InvestorController {

    @Autowired
    private InvestorService investorService;

    @PostMapping
    public AppResponse createAccount(@RequestBody InvestorRequest investorRequest){
        return investorService.createAccount(investorRequest);
    }

//    @Autowired

//    private WithdrawalService withdrawalService;

//    @GetMapping("/investors")
//    public ResponseEntity<List<Investor>> getAllInvestors() {
//        List<Investor> investors = investorService.getAllInvestors();
//        return new ResponseEntity<>(investors, HttpStatus.OK);
//    }
//
//    @PostMapping("/withdrawal-notices")
//    public ResponseEntity<String> createWithdrawalNotice(@Valid @RequestBody WithdrawalNotice withdrawalNotice) {
//        // Validate withdrawal notice
//        boolean isValid = withdrawalService.validateWithdrawalNotice(withdrawalNotice);
//        if (!isValid) {
//            return new ResponseEntity<>("Withdrawal notice validation failed", HttpStatus.BAD_REQUEST);
//        }
//        // Create withdrawal notice
//        withdrawalService.createWithdrawalNotice(withdrawalNotice);
//        return new ResponseEntity<>("Withdrawal notice created successfully", HttpStatus.CREATED);
//    }
}
