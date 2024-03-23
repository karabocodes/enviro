package org.enviro.assessment.grad001.karabokhunou.investments.service;

import org.enviro.assessment.grad001.karabokhunou.investments.dto.*;

public interface InvestorService {
    AppResponse createAccount(InvestorRequest appResponse);
    AppResponse balanceEnquiry(EnquiryRequest request);
    String nameEnquiry(EnquiryRequest request);
    AppResponse creditAccount(CreditDebitRequest request);
    AppResponse debitAccount(CreditDebitRequest request);
    AppResponse transfer(TransferRequest request);
}
