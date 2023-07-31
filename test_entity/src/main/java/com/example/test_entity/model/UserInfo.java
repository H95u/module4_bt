package com.example.test_entity.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String img;
    private double price;
    private int gender;
    private String email;
    private LocalDate dob;
    private int role;
    private double money;
    @OneToOne
    private User user;
    @ManyToOne
    private Address address;
}
