package com.holding.service;

import com.holding.exceptions.ResourceNotFoundException;
import com.holding.model.Branch;
import com.holding.model.Franchise;
import com.holding.model.Product;
import com.holding.repository.BranchRepository;
import com.holding.repository.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private FranchiseRepository franchiseRepository;

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public Branch createBranch(Branch branch) {
        if (branch.getFranchise() == null) {
            throw new ResourceNotFoundException("You must select a franchise");
        }

        Franchise franchise = franchiseRepository.findById(branch.getFranchise().getId())
                .orElseThrow(() -> new ResourceNotFoundException("The franchise does not exist"));

        branch.setFranchise(franchise);

        return branchRepository.save(branch);
    }

    public Branch updateBranchName(Long id, String name) {
        return branchRepository.findById(id).map(existingBranch -> {
            existingBranch.setName(name);

            return branchRepository.save(existingBranch);
        }).orElseThrow(() -> new ResourceNotFoundException("Branch not found"));
    };
}
