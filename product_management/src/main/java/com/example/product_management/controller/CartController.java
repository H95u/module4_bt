package com.example.product_management.controller;

import com.example.product_management.model.Product;
import com.example.product_management.service.CartDetail;
import com.example.product_management.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CartController {
    @Autowired
    private ProductService productService;

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        Product product = productService.findById(id);
        CartDetail cartDetail = new CartDetail();

        cartDetail.addProduct(product);
        String str = cartDetail.toJson();

        Cookie[] cookies = request.getCookies();
        boolean cartExists = false;
        StringBuilder cartBuilder = new StringBuilder();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart")) {
                    cartExists = true;
                    if (cartExists) {
                        cartBuilder.append(cookie.getValue()).append("/");
                    }
                }
            }
        }

        cartBuilder.append(str);

        Cookie cookie = new Cookie("cart", cartBuilder.toString());
        cookie.setMaxAge(24 * 60 * 60 * 7);
        response.addCookie(cookie);

        getCartDetail(request, cartDetail);

        model.addAttribute("cart", cartDetail);
        return "/cart";
    }


    @GetMapping("/cart")
    public ModelAndView showCart(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("/cart");
        CartDetail cartDetail = new CartDetail();
        getCartDetail(request, cartDetail);
        return modelAndView;
    }

    private void getCartDetail(HttpServletRequest request, CartDetail cartDetail) {
        Cookie[] cookies = request.getCookies();
        Map<Product, Integer> cartMap = new HashMap<>();
        String[] strSplit = new String[0];
        for (Cookie ck : cookies) {
            if (ck.getName().equals("cart")) {
                String value = ck.getValue();
                strSplit = value.split("/");
            }
        }
        for (String s : strSplit) {
            String[] sr = s.split("\\.");
            Product productAdd = productService.findById(Long.parseLong(sr[0]));
            Integer quantity = Integer.parseInt(sr[1]);
            cartMap.put(productAdd, quantity);
        }
        cartDetail.setProducts(cartMap);
    }
}
