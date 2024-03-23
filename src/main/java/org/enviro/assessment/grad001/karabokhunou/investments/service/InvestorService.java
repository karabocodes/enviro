package org.enviro.assessment.grad001.karabokhunou.investments.service;

import org.enviro.assessment.grad001.karabokhunou.investments.dto.AppResponse;
import org.enviro.assessment.grad001.karabokhunou.investments.dto.CreditDebitRequest;
import org.enviro.assessment.grad001.karabokhunou.investments.dto.EnquiryRequest;
import org.enviro.assessment.grad001.karabokhunou.investments.dto.InvestorRequest;

public interface InvestorService {
    AppResponse createAccount(InvestorRequest appResponse);
    AppResponse balanceEnquiry(EnquiryRequest request);
    String nameEnquiry(EnquiryRequest request);
    AppResponse creditAccount(CreditDebitRequest request);
}
