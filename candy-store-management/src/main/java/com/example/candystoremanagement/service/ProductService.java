package com.example.candystoremanagement.service;

import com.example.candystoremanagement.model.Product;
import com.example.candystoremanagement.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IGeneralService<Product> {
    @Autowired
    private IProductRepository iProductRepository;

    @Override
    public List<Product> findAll() {
        return iProductRepository.findAll();
    }

    @Override
    public Page<Product> findAllByPage(Pageable pageable) {
        return iProductRepository.findAll(pageable);
    }

    @Override
    public Product save(Product product) {
        boolean check = product.getExpireDate().isBefore(product.getManufactureDate());
        if (check) {
            return null;
        }
        return iProductRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        iProductRepository.deleteById(id);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return iProductRepository.findById(id);
    }
}
