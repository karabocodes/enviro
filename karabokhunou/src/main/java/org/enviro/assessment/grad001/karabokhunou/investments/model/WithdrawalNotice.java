package org.enviro.assessment.grad001.karabokhunou.investments.model;

import jakarta.persistence.*;
import java.util.List;

import java.util.Date;

/**
 * Represents a request by an investor to withdraw funds from a product.
 */
@Entity
public class WithdrawalNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long withdrawalId;

    @ManyToOne
    @JoinColumn(name = "investor_id")
    private Investor investor;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private double amount;
    private Date dateSubmitted;
    private String status;

    // Getters and setters
}
