package com.example.demo_upload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
public class UploadController {
    @RequestMapping("/demo")
    public ModelAndView demo() {
        ModelAndView modelAndView = new ModelAndView("demo_upload");
        modelAndView.addObject("uploadFile", new Upload());
        return modelAndView;
    }

    @PostMapping("/upload")
    public ModelAndView upload(@RequestParam MultipartFile file) throws IOException {
        ModelAndView modelAndView = new ModelAndView("view");
        String fileName = file.getOriginalFilename();
        file.transferTo(new File("D:\\module4\\demo_upload\\src\\main\\webapp\\files\\" + fileName));
        modelAndView.addObject("img", fileName);
        return modelAndView;
    }
}
