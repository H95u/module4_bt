package com.example.product_management.DTO;

import com.example.product_management.model.Category;
import lombok.Data;


@Data
public class CategoryDTO {
    private Category category;
    private Long productCount;

    public CategoryDTO(Category category, Long productCount) {
        this.category = category;
        this.productCount = productCount;
    }
}
