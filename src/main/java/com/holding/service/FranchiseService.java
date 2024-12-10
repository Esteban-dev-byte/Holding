package com.holding.service;

import com.holding.exceptions.ResourceNotFoundException;
import com.holding.model.Branch;
import com.holding.model.Franchise;
import com.holding.model.Product;
import com.holding.repository.BranchRepository;
import com.holding.repository.FranchiseRepository;
import com.holding.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FranchiseService {

    @Autowired
    private FranchiseRepository franchiseRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Franchise> getAllFranchises() {
        return franchiseRepository.findAll();
    }

    public List<Product> getTopStockProductsByFranchise(Long franchiseId) {
        List<Branch> branches = branchRepository.findByFranchiseId(franchiseId);
        List<Product> topProducts = new ArrayList<>();

        for (Branch branch : branches) {
            List<Product> products = productRepository.findTopByBranchIdOrderByStockDesc(branch.getId(), PageRequest.of(0, 1));
            if (!products.isEmpty()) {
                topProducts.add(products.getFirst());
            }
        }

        return topProducts;
    }

    public Franchise createFranchise(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    public Franchise updateFranchiseName(Long id, String name) {
        return franchiseRepository.findById(id).map(existingFranchise -> {
            existingFranchise.setName(name);

            return franchiseRepository.save(existingFranchise);
        }).orElseThrow(() -> new ResourceNotFoundException("Franchise not found"));
    };
}
