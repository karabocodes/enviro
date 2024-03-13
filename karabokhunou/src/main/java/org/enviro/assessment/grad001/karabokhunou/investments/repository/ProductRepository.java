package org.enviro.assessment.grad001.karabokhunou.investments.repository;

import org.enviro.assessment.grad001.karabokhunou.investments.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Add custom methods if needed
}
