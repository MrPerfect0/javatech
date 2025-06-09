package com.example.javatech.controller;

import com.example.javatech.exception.ResourceNotFoundException;
import com.example.javatech.model.Product;
import com.example.javatech.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository repo;
    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    //get product listing
    @GetMapping
    public List<Product> getAll() {
        return repo.findAll();
    }

    //get product by ID
    @GetMapping("/{id}")
    public Product getById(@PathVariable("id") Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    //create product
    @PostMapping
    public Product create(@RequestBody Product product) {
        return repo.save(product);
    }

    //Update Product
    @PutMapping("/{id}")
    public Product update(@PathVariable("id") Long id, @RequestBody Product prod) {
        Product p = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        p.setBookTitle(prod.getBookTitle());
        p.setBookPrice(prod.getBookPrice());
        p.setBookQuantity(prod.getBookQuantity());
        return repo.save(p);
    }

    //Delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        repo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}