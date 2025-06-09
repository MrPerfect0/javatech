package com.example.javatech.controller;

import com.example.javatech.model.Customer;
import com.example.javatech.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    CustomerRepository repo;

    @Test
    void getById_returnsCustomer() throws Exception {
        Customer c = new Customer(); c.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(c));

        mvc.perform(get("/api/customers/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getAll_returnsList() throws Exception {
        Customer c = new Customer(); c.setId(1L);
        when(repo.findAll()).thenReturn(Collections.singletonList(c));

        mvc.perform(get("/api/customers").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void create_returnsCreated() throws Exception {
        Customer c = new Customer(); c.setId(2L);
        when(repo.save(any())).thenReturn(c);

        mvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"A\",\"lastName\":\"B\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2));
    }

    @Test
    void update_returnsUpdatedCustomer() throws Exception {
        Customer existing = new Customer(); existing.setId(3L);
        Customer updated = new Customer(); updated.setId(3L);
        updated.setFirstName("New");
        updated.setLastName("Name");

        when(repo.findById(3L)).thenReturn(Optional.of(existing));
        when(repo.save(any())).thenReturn(updated);

        mvc.perform(put("/api/customers/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"New\",\"lastName\":\"Name\",\"personalEmail\":\"p@x.com\",\"officeEmail\":\"o@x.com\",\"familyMembers\":4}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("New"))
                .andExpect(jsonPath("$.lastName").value("Name"));
    }

    @Test
    void delete_returnsOk() throws Exception {
        Customer c = new Customer(); c.setId(4L);
        when(repo.findById(4L)).thenReturn(Optional.of(c));
        doNothing().when(repo).deleteById(4L);

        mvc.perform(delete("/api/customers/4"))
                .andExpect(status().isOk());

        verify(repo).deleteById(4L);
    }
}