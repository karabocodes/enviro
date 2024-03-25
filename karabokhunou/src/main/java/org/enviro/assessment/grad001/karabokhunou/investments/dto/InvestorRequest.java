package org.enviro.assessment.grad001.karabokhunou.investments.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvestorRequest {
    private String firstName;
    private String lastName;
    private String gender;
    private String address;
    private String phoneNumber;
    private String alternateNumber;
//    private String status;
    private String taxId;
    private String investmentType;
    private String email;
}
