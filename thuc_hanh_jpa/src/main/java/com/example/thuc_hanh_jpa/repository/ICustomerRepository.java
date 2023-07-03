package com.example.thuc_hanh_jpa.repository;

import com.example.thuc_hanh_jpa.model.Customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICustomerRepository extends CrudRepository<Customer, Long> {
}
