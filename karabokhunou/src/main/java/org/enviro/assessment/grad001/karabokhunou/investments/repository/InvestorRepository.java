package org.enviro.assessment.grad001.karabokhunou.investments.repository;
import org.enviro.assessment.grad001.karabokhunou.investments.model.Investor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestorRepository extends JpaRepository<Investor, Long> {
    // Add custom methods if needed
}
