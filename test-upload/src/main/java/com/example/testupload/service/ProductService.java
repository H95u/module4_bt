package com.example.testupload.service;

import com.example.testupload.model.Product;
import com.example.testupload.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private IProductRepository iProductRepository;

    public List<Product> findAll() {
        return iProductRepository.findAll();
    }

    public Optional<Product> findOne(Long id) {
        return iProductRepository.findById(id);
    }

    public Product save(Product product) {
        return iProductRepository.save(product);
    }

    public void remove(Long id) {
        iProductRepository.deleteById(id);
    }
}
