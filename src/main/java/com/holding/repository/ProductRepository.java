package com.holding.repository;
import com.holding.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.branch.id = :branchId ORDER BY p.stock DESC")
    List<Product> findTopByBranchIdOrderByStockDesc(Long branchId, Pageable pageable);
}
