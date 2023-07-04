package com.example.candystoremanagement.service;

import com.example.candystoremanagement.DTO.CategoryDTO;
import com.example.candystoremanagement.model.Category;
import com.example.candystoremanagement.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Override
    public List<Category> findAll() {
        return iCategoryRepository.findAll();
    }

    @Override
    public Page<Category> findAllByPage(Pageable pageable) {
        return iCategoryRepository.findAll(pageable);
    }

    @Override
    public Category save(Category category) {
        return iCategoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return iCategoryRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iCategoryRepository.deleteById(id);
    }

    public List<Double> getTotalMoneyOfEachCategory() {
        return iCategoryRepository.getTotalMoneyOfEachCategory();
    }

    public List<CategoryDTO> getAllTotalDTO() {
        List<Object[]> resultList = iCategoryRepository.getAllTotalDTO();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Object[] objects : resultList) {
            Long categoryId = ((Number) objects[0]).longValue();
            String name = (String) objects[1];
            if (objects[2] != null) {
                Double total = ((Number) objects[2]).doubleValue();
                categoryDTOList.add(new CategoryDTO(categoryId, name, total));
            } else {
                categoryDTOList.add(new CategoryDTO(categoryId, name, 0D));
            }
        }
        return categoryDTOList;
    }
}
