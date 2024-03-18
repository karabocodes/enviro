package org.enviro.assessment.grad001.karabokhunou.investments.service;

import org.enviro.assessment.grad001.karabokhunou.investments.dto.AccountInfo;
import org.enviro.assessment.grad001.karabokhunou.investments.dto.AppResponse;
import org.enviro.assessment.grad001.karabokhunou.investments.dto.EmailDetails;
import org.enviro.assessment.grad001.karabokhunou.investments.dto.InvestorRequest;
import org.enviro.assessment.grad001.karabokhunou.investments.entity.Investor;
import org.enviro.assessment.grad001.karabokhunou.investments.repository.InvestorRepository;
import org.enviro.assessment.grad001.karabokhunou.investments.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class InvestorServiceImpl implements  InvestorService {

    @Autowired
    InvestorRepository investorRepository;
    @Autowired
    EmailService emailService;

    @Override
    public AppResponse createAccount(InvestorRequest investorRequest) {
        /**
         * saving a new account
         */
        if (investorRepository.existsByTaxId(investorRequest.getTaxId())) {
            return AppResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                    .accountInfo(null)
                    .build();

        }

        Investor newInvestor = Investor.builder()
                .firstName(investorRequest.getFirstName())
                .lastName(investorRequest.getLastName())
                .gender(investorRequest.getGender())
                .phoneNumber(investorRequest.getPhoneNumber())
                .alternateNumber(investorRequest.getAlternateNumber())
                .taxId(investorRequest.getTaxId())
                .address(investorRequest.getAddress())
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountBalance(BigDecimal.ZERO)
                .email(investorRequest.getEmail())
                .status("ACTIVE")
                .build();

        Investor savedInvestor = investorRepository.save(newInvestor);

        //Send email alert
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(savedInvestor.getEmail())
                .subject(("Account Creation"))
                .messageBody(("COngradulation Account has been created. \n" +
                        "your account deatils" + savedInvestor.getAccountNumber() + " "+ savedInvestor.getFirstName()))
                .build();
        emailService.sendEmailAlert(emailDetails);

        return AppResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_CODE)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(savedInvestor.getAccountBalance())
                        .accountNumber(savedInvestor.getAccountNumber())
                        .accountName(savedInvestor.getFirstName() + " " + savedInvestor.getLastName())
                        .build())
                .build();
    }

}