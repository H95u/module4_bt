package com.example.candystoremanagement.controller;

import com.example.candystoremanagement.DTO.CategoryDTO;
import com.example.candystoremanagement.model.Category;
import com.example.candystoremanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/categories")
public class CategoryControllerAPI {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Iterable<Category>> findAll() {
        List<Category> categoryList = categoryService.findAll();
        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findStudentById(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (!category.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> saveStudent(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateStudent(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> categoryUpdate = categoryService.findById(id);
        if (!categoryUpdate.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            category.setId(categoryUpdate.get().getId());
            return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
        }
    }

    @GetMapping("/total")
    public ResponseEntity<Iterable<Double>> findTotalMoney() {
        List<Double> moneyOfEachCategory = categoryService.getTotalMoneyOfEachCategory();
        return new ResponseEntity<>(moneyOfEachCategory, HttpStatus.OK);
    }

    @GetMapping("/total-dto")
    public ResponseEntity<Iterable<CategoryDTO>> findTotalMoneyDTO() {
        List<CategoryDTO> moneyOfEachCategory = categoryService.getAllTotalDTO();
        return new ResponseEntity<>(moneyOfEachCategory, HttpStatus.OK);
    }

}
