package org.enviro.assessment.grad001.karabokhunou.investments.service;

import org.enviro.assessment.grad001.karabokhunou.investments.dto.EmailDetails;
import org.springframework.stereotype.Service;

@Service

public interface EmailService {
    void sendEmailAlert(EmailDetails emailDetails);
}
