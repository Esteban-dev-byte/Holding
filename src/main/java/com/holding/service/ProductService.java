package com.holding.service;
import com.holding.exceptions.ResourceNotFoundException;
import com.holding.model.Branch;
import com.holding.model.Product;
import com.holding.repository.BranchRepository;
import com.holding.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BranchRepository branchRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    };

    public Product createProduct(Product product) {
        if (product.getBranch() == null) {
            throw new ResourceNotFoundException("You must select a branch");
        }

        Branch branch = branchRepository.findById(product.getBranch().getId())
                .orElseThrow(() -> new ResourceNotFoundException("The branch does not exist"));

        product.setBranch(branch);

        return productRepository.save(product);
    };

    public Product updateProductName(Long id, String name) {
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setName(name);

            return productRepository.save(existingProduct);
        }).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    };

    public Product updateProductStock(Long id, int stock) {
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setStock(stock);

            return productRepository.save(existingProduct);
        }).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    };

    public Product deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productRepository.deleteById(id);
        return product;
    };
}
