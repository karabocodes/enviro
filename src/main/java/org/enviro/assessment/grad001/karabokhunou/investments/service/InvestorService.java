package org.enviro.assessment.grad001.karabokhunou.investments.service;

import org.enviro.assessment.grad001.karabokhunou.investments.dto.AppResponse;
import org.enviro.assessment.grad001.karabokhunou.investments.dto.InvestorRequest;

public interface InvestorService {
    AppResponse createAccount(InvestorRequest appResponse);

}
