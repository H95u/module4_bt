package com.example.product_management.controller;

import com.example.product_management.DTO.CategoryDTO;
import com.example.product_management.model.Category;
import com.example.product_management.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ModelAndView findAll() {
        ModelAndView modelAndView = new ModelAndView("/category/home");
        List<CategoryDTO> categories = categoryService.countProduct();
        modelAndView.addObject("categoriesDTO", categories);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("/create")
    public String createClasses(Category category) {
        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/category/update");
        Category category = categoryService.findById(id);
        modelAndView.addObject("category", category);
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public String updateClasses(Category updateCategory, @PathVariable Long id) {
        updateCategory.setId(id);
        categoryService.save(updateCategory);
        return "redirect:/categories";
    }

    @GetMapping("/sortCategoryAsc")
    public ModelAndView sortCategoryByProductAsc() {
        ModelAndView modelAndView = new ModelAndView("/category/home");
        List<CategoryDTO> categories = categoryService.sortByProductAsc();
        modelAndView.addObject("categoriesDTO", categories);
        return modelAndView;
    }

    @GetMapping("/sortCategoryDes")
    public ModelAndView sortCategoryByProductDes() {
        ModelAndView modelAndView = new ModelAndView("/category/home");
        List<CategoryDTO> categories = categoryService.sortByProductDes();
        modelAndView.addObject("categoriesDTO", categories);
        return modelAndView;
    }
}
