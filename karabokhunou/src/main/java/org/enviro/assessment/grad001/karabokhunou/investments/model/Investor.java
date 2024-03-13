package org.enviro.assessment.grad001.karabokhunou.investments.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;


@Entity
public class Investor {
    @Id

    private Long id;
    private String name;
    private String contactInfo;
    private String taxId;

    private String investmentType;

    @OneToMany(mappedBy = "investor")
    private List<Product> products;

    @OneToMany(mappedBy = "investor")
    private List<WithdrawalNotice> withdrawalNotices;

    // Getters and setters
}