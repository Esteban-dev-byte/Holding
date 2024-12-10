package com.holding.controller;
import com.holding.model.Franchise;
import com.holding.model.Product;
import com.holding.service.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/franchises")
public class FranchiseController {

    @Autowired
    private FranchiseService franchiseService;

    @GetMapping
    public List<Franchise> getAllFranchises() {
        return franchiseService.getAllFranchises();
    }

    @GetMapping("/top-stock-products/{id}")
    public List<Product> getTopStockProducts(@PathVariable Long id) {
        return franchiseService.getTopStockProductsByFranchise(id);
    }

    @PostMapping
    public Franchise createFranchise(@RequestBody Franchise franchise) {
        return franchiseService.createFranchise(franchise);
    }

    @PutMapping("/update-name/{id}")
    public Franchise updateFranchiseName(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        String newName = requestBody.get("name");
        return franchiseService.updateFranchiseName(id, newName);
    }
}
