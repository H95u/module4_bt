package com.example.candystoremanagement.controller;

import com.example.candystoremanagement.model.Product;
import com.example.candystoremanagement.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
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
        LocalDate dateNow = LocalDate.now();
        for (Product product : products) {
            if (product.getExpireDate().compareTo(dateNow) < 3) {
                product.setName(product.getName() + " (-20%)");
                product.setPrice((product.getPrice() * 80) / 100);
            } else if (product.getExpireDate().compareTo(dateNow) < 7) {
                product.setName(product.getName() + " (-10%)");
                product.setPrice((product.getPrice() * 90) / 100);
            }
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
                                                 @RequestParam("file") MultipartFile file,
                                                 @PathVariable Long id) throws IOException {
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            Product product = objectMapper.readValue(productJson, Product.class);
            String filename = file.getOriginalFilename();
            product.setId(id);
            file.transferTo(new File("D:\\module4\\candy-store-front-end\\file\\" + filename));
            return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            productService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/sort-price-des")
    public ResponseEntity<Page<Product>> sortPriceDes(@PageableDefault(value = 5, sort = "price", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Product> products = productService.findAllByPage(pageable);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/sort-price-asc")
    public ResponseEntity<Page<Product>> sortPriceAsc(@PageableDefault(value = 5, sort = "price", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Product> products = productService.findAllByPage(pageable);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/find-price-between")
    public ResponseEntity<Page<Product>> findPriceBetween(@PageableDefault(value = 5) Pageable pageable,
                                                          @RequestParam Double min,
                                                          @RequestParam Double max) {
        Page<Product> products = productService.findPriceBetween(min, max, pageable);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/search-by-name")
    public ResponseEntity<Page<Product>> searchByName(@RequestParam String nameSearch, Pageable pageable) {
        Page<Product> products = productService.findByName(nameSearch, pageable);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


}
