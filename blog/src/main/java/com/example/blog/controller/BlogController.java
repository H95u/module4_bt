package com.example.blog.controller;

import com.example.blog.model.Blog;
import com.example.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogRepository blogRepository;
    @Value("${path-upload}")
    private String upload;

    @GetMapping("")
    public ModelAndView displayAll() {
        ModelAndView modelAndView = new ModelAndView("/home");
        List<Blog> blogList = blogRepository.findAll();
        modelAndView.addObject("blogs", blogList);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewBlog(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/view");
        modelAndView.addObject("blog", blogRepository.findById(id));
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }

    @PostMapping("/create")
    public String createBlog(Blog blog, @RequestParam MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        if (!file.isEmpty()) {
            file.transferTo(new File(upload + fileName));
            blog.setImage(fileName);
        } else blog.setImage(null);
        blogRepository.save(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/update/{id}")
    public ModelAndView showUpdateForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/update");
        modelAndView.addObject("blog", blogRepository.findById(id));
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public String updateBlog(Blog blog, @PathVariable Long id, @RequestParam MultipartFile file) throws IOException {
        Blog blogEdit = blogRepository.findById(id);
        if (!file.isEmpty()) {
            blogEdit.setImage(file.getOriginalFilename());
            file.transferTo(new File(upload + file.getOriginalFilename()));
        }
        blogRepository.save(blog);
        return "redirect:/blogs";
    }

}
