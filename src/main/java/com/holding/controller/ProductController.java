package com.holding.controller;

import com.holding.exceptions.ResourceNotFoundException;
import com.holding.model.Product;
import com.holding.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

    @PutMapping("/update-name/{id}")
    public Product updateProductName(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        String newName = requestBody.get("name");
        return productService.updateProductName(id, newName);
    }

    @PutMapping("/update-stock/{id}")
    public Product updateProductStock(@PathVariable Long id, @RequestBody Map<String, Integer> requestBody) {
        Integer newStock = requestBody.get("stock");
        return productService.updateProductStock(id, newStock);
    }
}
