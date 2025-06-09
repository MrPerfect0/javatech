package com.example.javatech.controller;

import com.example.javatech.exception.ResourceNotFoundException;
import com.example.javatech.model.Customer;
import com.example.javatech.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerRepository repo;
    public CustomerController(CustomerRepository repo) {
        this.repo = repo;
    }

    //get customers lists
    @GetMapping
    public List<Customer> getAll() {
        return repo.findAll();
    }

    //get customer by ID
    @GetMapping("/{id}")
    public Customer getById(@PathVariable("id") Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }

    //create customer
    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        return repo.save(customer);
    }

    //Update customer
    @PutMapping("/{id}")
    public Customer update(@PathVariable("id") Long id, @RequestBody Customer cust) {
        Customer c = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        c.setFirstName(cust.getFirstName());
        c.setLastName(cust.getLastName());
        c.setPersonalEmail(cust.getPersonalEmail());
        c.setOfficeEmail(cust.getOfficeEmail());
        c.setFamilyMembers(cust.getFamilyMembers());
        return repo.save(c);
    }

    //Delete customer
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        repo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}