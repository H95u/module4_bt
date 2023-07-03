package com.example.product_management.service;

import com.example.product_management.DTO.CategoryDTO;
import com.example.product_management.model.Category;
import com.example.product_management.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements IGeneralService<Category> {
    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Override
    public Category save(Category category) {
        iCategoryRepository.save(category);
        return category;
    }

    @Override
    public List<Category> findAll() {
        return iCategoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return iCategoryRepository.findById(id).get();
    }


    @Override
    public void remove(Long id) {
        iCategoryRepository.deleteById(id);
    }

    public List<CategoryDTO> sortByProductDes() {
        List<Object[]> resultList = iCategoryRepository.countProductDes();
        return getCategoryDTOList(resultList);
    }

    public List<CategoryDTO> sortByProductAsc() {
        List<Object[]> resultList = iCategoryRepository.countProductAsc();
        return getCategoryDTOList(resultList);
    }

    public List<CategoryDTO> countProduct() {
        List<Object[]> resultList = iCategoryRepository.countProduct();
        return getCategoryDTOList(resultList);
    }


    private static List<CategoryDTO> getCategoryDTOList(List<Object[]> resultList) {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Object[] objects : resultList) {
            Long categoryId = ((Number) objects[0]).longValue();
            String name = (String) objects[1];
            Long productCount = ((Number) objects[2]).longValue();
            categoryDTOList.add(new CategoryDTO(new Category(categoryId, name), productCount));
        }
        return categoryDTOList;
    }

}
