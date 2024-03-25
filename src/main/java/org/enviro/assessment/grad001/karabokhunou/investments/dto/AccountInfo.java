package org.enviro.assessment.grad001.karabokhunou.investments.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountInfo {

    @Schema(
            name = "Investor account name"
    )
    private String accountName;

    @Schema(
            name = "Investor account balance"
    )
    private BigDecimal accountBalance;

    @Schema(
            name = "Investor account number"
    )
    private String accountNumber;
}
