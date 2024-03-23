package org.enviro.assessment.grad001.karabokhunou.investments.service;

import org.enviro.assessment.grad001.karabokhunou.investments.dto.*;
import org.enviro.assessment.grad001.karabokhunou.investments.entity.Investor;
import org.enviro.assessment.grad001.karabokhunou.investments.repository.InvestorRepository;
import org.enviro.assessment.grad001.karabokhunou.investments.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

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
                .messageBody(("Congratulation Account has been created. \n" +
                        "your account deatils" + savedInvestor.getAccountNumber() + " " + savedInvestor.getFirstName()))
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

    @Override
    public AppResponse balanceEnquiry(EnquiryRequest request) {
        //check if the provided account exists
        boolean isAccountExist = investorRepository.existsByAccountNumber(request.getAccountNumber());
        if (!isAccountExist) {
            return AppResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_DOESNT_EXIST_FOUND_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_DOESNT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        Investor foundUser = investorRepository.findByAccountNumber(request.getAccountNumber());

        return AppResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_FOUND_CODE)
                .responseMessage(AccountUtils.ACCOUNT_FOUND_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(foundUser.getAccountBalance())
                        .accountNumber(foundUser.getAccountNumber())
                        .accountName(foundUser.getFirstName() + " " + foundUser.getLastName())
                        .build())
                .build();
    }

    @Override
    public String nameEnquiry(EnquiryRequest request) {
        boolean isAccountExist = investorRepository.existsByAccountNumber(request.getAccountNumber());
        if (!isAccountExist) {
            return AccountUtils.ACCOUNT_DOESNT_EXIST_MESSAGE;
        }
        Investor foundUser = investorRepository.findByAccountNumber(request.getAccountNumber());
        return foundUser.getFirstName() + " " + foundUser.getLastName();
    }

    @Override
    public AppResponse creditAccount(CreditDebitRequest request) {
        // checking if account exist
        boolean isAccountExist = investorRepository.existsByAccountNumber(request.getAccountNumber());
        if (!isAccountExist) {
            return AppResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_DOESNT_EXIST_FOUND_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_DOESNT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }
        if (request.getAmount() == null) {
            return AppResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_FOUND_CODE)
                    .responseMessage("Invalid credit amount. Amount cannot be null.")
                    .build();
        }
        Investor userToCredit = investorRepository.findByAccountNumber(request.getAccountNumber());
        userToCredit.setAccountBalance(userToCredit.getAccountBalance().add(request.getAmount()));

        investorRepository.save(userToCredit);

        return AppResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREDITED_SUCCESS_CODE)
                .responseMessage(AccountUtils.ACCOUNT_CREDITED_SUCCESS_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountName(userToCredit.getFirstName() + " " + userToCredit.getLastName())
                        .accountBalance(userToCredit.getAccountBalance())
                        .accountNumber(request.getAccountNumber())
                        .build())
                .build();
    }

    @Override
    public AppResponse debitAccount(CreditDebitRequest request) {
        // checking if account exist
        boolean isAccountExist = investorRepository.existsByAccountNumber(request.getAccountNumber());
        if (!isAccountExist) {
            return AppResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_DOESNT_EXIST_FOUND_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_DOESNT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }
        if (request.getAmount() == null) {
            return AppResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_FOUND_CODE)
                    .responseMessage("Invalid Debit amount. Amount cannot be null.")
                    .build();
        }

        Investor userToDebit = investorRepository.findByAccountNumber(request.getAccountNumber());

        // Use BigDecimal for balance and amount
        BigDecimal availableBalance = userToDebit.getAccountBalance();
        BigDecimal debitAmount = request.getAmount();
        if (availableBalance.compareTo(debitAmount) < 0) {
            return AppResponse.builder()
                    .responseCode("233")
                    .responseMessage(AccountUtils.INSUFFICIENT_BALANCE_MESSAGE)
                    .accountInfo(null)
                    .build();
        } else {
            userToDebit.setAccountBalance(availableBalance.subtract(debitAmount));
            investorRepository.save(userToDebit);
            return AppResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_DEBITED_SUCCESS)
                    .responseMessage(AccountUtils.ACCOUNT_DEBITED_MESSAGE)
                    .accountInfo(AccountInfo.builder()
                            .accountName(userToDebit.getFirstName() + " " + userToDebit.getLastName())
                            .accountBalance(userToDebit.getAccountBalance())
                            .accountNumber(request.getAccountNumber())
                            .build())
                    .build();
        }
    }

    @Override
    public AppResponse transfer(TransferRequest request) {
        // Check if the source account exists
//        boolean isSourceAccountExist = investorRepository.existsByAccountNumber(request.getSourceAccountNumber());
//        if (!isSourceAccountExist) {
//            return AppResponse.builder()
//                    .responseCode(AccountUtils.ACCOUNT_DOESNT_EXIST_FOUND_CODE)
//                    .responseMessage("Source account doesn't exist.")
//                    .accountInfo(null)
//                    .build();
//        }
//
//        // Check if the destination account exists
//        boolean isDestinationAccountExist = investorRepository.existsByAccountNumber(request.getDestinationAccountNumber());
//        if (!isDestinationAccountExist) {
//            return AppResponse.builder()
//                    .responseCode(AccountUtils.ACCOUNT_DOESNT_EXIST_FOUND_CODE)
//                    .responseMessage("Destination account doesn't exist.")
//                    .accountInfo(null)
//                    .build();
//        }
//
//        // Check if the source account has sufficient balance for transfer
//        Investor sourceAccount = investorRepository.findByAccountNumber(request.getSourceAccountNumber());
//        BigDecimal transferAmount = BigDecimal.valueOf(request.getAmount());
//
//
//        if (sourceAccount.getAccountBalance().compareTo(transferAmount) < 0) {
//            return AppResponse.builder()
//                    .responseCode(AccountUtils.INSUFFICIENT_BALANCE_CODE)
//                    .responseMessage(AccountUtils.INSUFFICIENT_BALANCE_MESSAGE)
//                    .accountInfo(null)
//                    .build();
//        }
//
//        // Debit the source account
//        sourceAccount.setAccountBalance(sourceAccount.getAccountBalance().subtract(transferAmount));
//        investorRepository.save(sourceAccount);
//
//        // Credit the destination account
//        Investor destinationAccount = investorRepository.findByAccountNumber(request.getDestinationAccountNumber());
//        destinationAccount.setAccountBalance(destinationAccount.getAccountBalance().add(transferAmount));
//        investorRepository.save(destinationAccount);
//
//        return AppResponse.builder()
//                .responseCode("AccountUtilsTRANSFER_SUCCESS_CODE")
//                .responseMessage("TRANSFER_SUCCESS_MESSAGE")
//                .accountInfo(AccountInfo.builder()
//                        .accountName(sourceAccount.getFirstName() + " " + sourceAccount.getLastName())
//                        .accountBalance(sourceAccount.getAccountBalance())
//                        .accountNumber(sourceAccount.getAccountNumber())
//                        .build())
//                .build();
//    }

        boolean isDestinationAccountExist = investorRepository.existsByAccountNumber(request.getDestinationAccountNumber());

        if (!isDestinationAccountExist) {
            return AppResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_DOESNT_EXIST_FOUND_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_DOESNT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        Investor sourceAccountInvestor = investorRepository.findByAccountNumber(request.getSourceAccountNumber());

        if (request.getAmount().compareTo(sourceAccountInvestor.getAccountBalance()) > 0) {
            return AppResponse.builder()
                    .responseCode("233")
                    .responseMessage(AccountUtils.INSUFFICIENT_BALANCE_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        sourceAccountInvestor.setAccountBalance(sourceAccountInvestor.getAccountBalance().subtract(request.getAmount()));

        String sourceUsername = sourceAccountInvestor.getFirstName() + " " + sourceAccountInvestor.getLastName();

        investorRepository.save(sourceAccountInvestor);
        EmailDetails debitAlert = EmailDetails.builder()
                .subject("Debit Alert")
                .recipient(sourceAccountInvestor.getEmail())
                .messageBody("The sum of " + request.getAmount() + " has been deducted from your account current balance is " + sourceAccountInvestor.getAccountBalance())
                .build();

        emailService.sendEmailAlert(debitAlert);

        Investor destinationAccountInvestor = investorRepository.findByAccountNumber(request.getDestinationAccountNumber());

        destinationAccountInvestor.setAccountBalance(destinationAccountInvestor.getAccountBalance().add(request.getAmount()));
//        String recipientUsername = destinationAccountInvestor.getFirstName() + " " + sourceAccountInvestor.getLastName();

        investorRepository.save(destinationAccountInvestor);
        EmailDetails creditAlert = EmailDetails.builder()
                .subject("credit Alert")
                .recipient(sourceAccountInvestor.getEmail())
                .messageBody("The sum of " + request.getAmount() + " has been Deposited to your account from " + sourceAccountInvestor.getFirstName() + sourceAccountInvestor.getAccountBalance())
                .build();

//        emailService.sendEmailAlert(debitAlert);

        return AppResponse.builder()
                .responseCode(AccountUtils.TRANSFER_SUCCESSFUL_CODE)
                .responseMessage(AccountUtils.TRANSFER_SUCCESSFUL_MESSAGE)
                .accountInfo(null)
                .build();
    }
}