package org.enviro.assessment.grad001.karabokhunou.investments.entity;

import jakarta.persistence.*;
import lombok.*;
//import org.enviro.assessment.grad001.karabokhunou.investments.model.Product;
//import org.enviro.assessment.grad001.karabokhunou.investments.model.WithdrawalNotice;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "investors")
public class Investor {
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String address;
    private String phoneNumber;
    private String alternateNumber;
    private String status;
    private String accountNumber;
    private BigDecimal accountBalance = BigDecimal.ZERO;
    private String taxId;
    private String email;
    private String investmentType;
    @CreationTimestamp
    private LocalDateTime createdAT;
    @UpdateTimestamp
    private LocalDateTime modifiedAt;



//    @OneToMany(mappedBy = "investor")
//    private List<Product> products;
//
//    @OneToMany(mappedBy = "investor")
//    private List<WithdrawalNotice> withdrawalNotices;

}