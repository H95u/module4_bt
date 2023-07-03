package com.example.product_management.controller;

import com.example.product_management.model.Category;
import com.example.product_management.model.Product;
import com.example.product_management.service.CategoryService;
import com.example.product_management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> classes() {
        return categoryService.findAll();
    }

    @GetMapping
    public ModelAndView findAll() {
        ModelAndView modelAndView = new ModelAndView("/product/home");
        modelAndView.addObject("products", productService.showAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public String createNewProduct(Product product) throws IOException {
        if (productService.save(product) != null) {
            return "redirect:/products";
        } else return "/error/400";
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable Long id) {
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/product/update");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public String updateProduct(Product product, @PathVariable Long id) throws IOException {
        product.setId(id);
        if (productService.save(product) != null) {
            return "redirect:/products";
        } else return "/error/400";
    }

}
