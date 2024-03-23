package org.enviro.assessment.grad001.karabokhunou.investments.controller;
import org.enviro.assessment.grad001.karabokhunou.investments.dto.*;
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

    @GetMapping("balanceEnquiry")
    public AppResponse balanceEnquiry (@RequestBody EnquiryRequest request){
        return investorService.balanceEnquiry(request);
    }

    @GetMapping("nameEnquiry")
    public String nameEnquiry(@RequestBody EnquiryRequest request){
        return  investorService.nameEnquiry(request);
    }
    @PostMapping("credit")
    public AppResponse creditAccount (@RequestBody CreditDebitRequest request) {
        return investorService.creditAccount (request);
    }

    @PostMapping("debit")
    public AppResponse debitAccount (@RequestBody CreditDebitRequest request) {
        return investorService.debitAccount (request);
    }

    @PostMapping("transfer")
    public AppResponse transfer(@RequestBody TransferRequest request){
        return  investorService.transfer(request);
    }
}
