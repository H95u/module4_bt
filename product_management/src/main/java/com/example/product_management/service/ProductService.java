package com.example.product_management.service;

import com.example.product_management.model.Product;
import com.example.product_management.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ProductService implements IGeneralService<Product> {

    @Autowired
    private IProductRepository iProductRepository;
    @Value("${path-upload}")
    private String upload;

    public List<Product> showAll() {
        return iProductRepository.findAll();
    }

    @Override
    public Product save(Product product) throws IOException {
        String filename = product.getFile().getOriginalFilename();
        String[] nameSplit = filename.split("\\.");
        if (!product.getFile().isEmpty()) {
            if (nameSplit[nameSplit.length - 1].equals("jpg")) {
                product.getFile().transferTo(new File(upload + filename));
                product.setImg(filename);
                iProductRepository.save(product);
                return product;
            }
        } else {
            Product productUpdate = findById(product.getId());
            product.setImg(productUpdate.getImg());
            iProductRepository.save(product);
            return product;
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        return iProductRepository.findAll();
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Product findById(Long id) {
        return iProductRepository.findById(id).get();
    }
}
