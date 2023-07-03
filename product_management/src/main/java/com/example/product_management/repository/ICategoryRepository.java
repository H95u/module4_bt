package com.example.product_management.repository;

import com.example.product_management.DTO.CategoryDTO;
import com.example.product_management.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "select * from productCountView   ", nativeQuery = true)
    List<Object[]> countProduct();

    @Query(value = "select * from productCountView order by productCount desc ", nativeQuery = true)
    List<Object[]> countProductDes();

    @Query(value = "select * from productCountView order by productCount asc ", nativeQuery = true)
    List<Object[]> countProductAsc();

}

