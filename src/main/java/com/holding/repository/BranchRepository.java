package com.holding.repository;
import com.holding.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findByFranchiseId(Long franchiseId);
}
