package org.enviro.assessment.grad001.karabokhunou.investments.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.enviro.assessment.grad001.karabokhunou.investments.dto.*;
import org.enviro.assessment.grad001.karabokhunou.investments.service.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/investor")
@Tag(name = "Investor account management APIs")
public class InvestorController {

    @Autowired
    private InvestorService investorService;
    @Operation(
            summary = "Create new user account",
            description = "Creating a new user and assigning an account ID"
    )

    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 Created"
    )

    @PostMapping
    public AppResponse createAccount(@RequestBody InvestorRequest investorRequest){
        return investorService.createAccount(investorRequest);
    }

    @Operation(
            summary = "Balance Enquiry",
            description = "Given an account , number how much the user has"
    )

    @ApiResponse (
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )

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
