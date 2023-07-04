package com.example.candystoremanagement.repository;

import com.example.candystoremanagement.DTO.CategoryDTO;
import com.example.candystoremanagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT SUM(price * quantity) AS total_sum_price\n" +
            "FROM category\n" +
            "         LEFT JOIN product p ON category.id = p.category_id\n" +
            "GROUP BY category.id;", nativeQuery = true)
    List<Double> getTotalMoneyOfEachCategory();

    @Query(value = "SELECT category.id,category.name,SUM(price * quantity) AS total_sum_price\n" +
            "FROM category\n" +
            "         LEFT JOIN product p ON category.id = p.category_id\n" +
            "GROUP BY category.id;", nativeQuery = true)
    List<Object[]> getAllTotalDTO();
}
