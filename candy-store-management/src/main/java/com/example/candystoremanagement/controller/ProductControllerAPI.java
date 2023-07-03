package com.example.candystoremanagement.controller;

import com.example.candystoremanagement.model.Product;
import com.example.candystoremanagement.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/products")
public class ProductControllerAPI {
    @Autowired
    private ProductService productService;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProductControllerAPI(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(@PageableDefault(value = 5) Pageable pageable) {
        Page<Product> products = productService.findAllByPage(pageable);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestParam("data") String productJson,
                                               @RequestParam("file") MultipartFile file) throws IOException {
        Product product = objectMapper.readValue(productJson, Product.class);
        String filename = file.getOriginalFilename();
        file.transferTo(new File("D:\\module4\\candy-store-front-end\\file\\" + filename));
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestParam("data") String productJson,
                                                 @RequestParam("file") MultipartFile file) throws IOException {
        return null;

    }

}
