package com.holding.controller;

import com.holding.model.Branch;
import com.holding.model.Product;
import com.holding.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }

    @PostMapping
    public Branch createBranch(@RequestBody Branch branch) {
        return branchService.createBranch(branch);
    }

    @PutMapping("/update-name/{id}")
    public Branch updateBranchName(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        String newName = requestBody.get("name");
        return branchService.updateBranchName(id, newName);
    }
}
