package com.example.candystoremanagement.DTO;

import lombok.Data;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private Double total;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String name, Double total) {
        this.id = id;
        this.name = name;
        this.total = total;
    }
}
