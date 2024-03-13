package org.enviro.assessment.grad001.karabokhunou.investments.model;

import jakarta.persistence.JoinColumn;
import org.hibernate.annotations.ManyToAny;

public class Product{
    private String productId; // unique identifier for the product
    private String name; // name of the product
    private String description; // brief description of the product
    private String type; // e.g., stock, bond, mutual fund
    private double interestRate; // applicable interest rate, if any
    private double minimumInvestment; // minimum amount required to invest

    @ManyToAny
    @JoinColumn
    private Investor investor;

    public double getBalance() {
        return 0;
    }
}
