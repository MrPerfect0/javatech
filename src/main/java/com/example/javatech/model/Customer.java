package com.example.javatech.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String personalEmail;
    private String officeEmail;
    private Integer familyMembers;
}