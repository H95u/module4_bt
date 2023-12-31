package com.example.candystoremanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IGeneralService<T> {
    List<T> findAll();

    Page<T> findAllByPage(Pageable pageable);

    T save(T t);

    void remove(Long id);

    Optional<T> findById(Long id);
}
