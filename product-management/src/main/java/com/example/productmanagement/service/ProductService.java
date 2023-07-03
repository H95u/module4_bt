package com.example.productmanagement.service;

import com.example.productmanagement.DAO.ProductDAO;
import com.example.productmanagement.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {

    public List<Product> findAll() {
        return ProductDAO.getInstance().findAll();
    }

    public void update(Product product) {
        ProductDAO.getInstance().updateProduct(product);
    }

    public void add(Product product) {
        ProductDAO.getInstance().addNewProduct(product);
    }

    public Product findById(int id) {
        return ProductDAO.getInstance().findById(id);
    }

    public void deleteProduct(int id) {
        ProductDAO.getInstance().deleteProduct(id);
    }

    public List<Product> searchByName(String searchName) {
        return ProductDAO.getInstance().searchByName(searchName);
    }
}
