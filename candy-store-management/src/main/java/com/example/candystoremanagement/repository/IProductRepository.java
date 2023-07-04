package com.example.candystoremanagement.repository;

import com.example.candystoremanagement.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByPriceBetween(double minPrice, double maxPrice, Pageable pageable);

    @Query(value = "select product.*,category.name from product join category  on product.category_id = category.id where product.name like :name or category.name like :name", nativeQuery = true)
    Page<Product> searchByName(@Param("name") String name, Pageable pageable);


}
