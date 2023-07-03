package com.example.candystoremanagement.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String img;
    private double price;
    private int quantity;
    private LocalDate manufactureDate;
    private LocalDate expireDate;
    @ManyToOne
    private Category category;
}
