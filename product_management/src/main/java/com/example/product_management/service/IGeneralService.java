package com.example.product_management.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface IGeneralService<T> {
    T save(T t) throws IOException;

    List<T> findAll();

    void remove(Long id);
    T findById(Long id);
}
