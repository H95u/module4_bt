package com.example.demospringboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
@Controller
public class DemoSpringBootApplication {

    @Value("${path-upload}")
    private String upload;

    @Value("${path-audio}")
    private String audio;

    @Value("${path-video}")
    private String video;


    public static void main(String[] args) {
        SpringApplication.run(DemoSpringBootApplication.class, args);
    }

    @GetMapping("/demo")
    public String demo() {
        return "demo";
    }

    @PostMapping("/upload")
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        ModelAndView modelAndView = new ModelAndView("upload");
        String fileName = file.getOriginalFilename();
        String[] nameSplit = fileName.split("\\.");
        String fileType = nameSplit[nameSplit.length - 1];
        if (fileType.equals("jpg")) {
            file.transferTo(new File(upload + fileName));
            modelAndView.addObject("img", fileName);
            return modelAndView;
        } else if (fileType.equals("m4a")) {
            file.transferTo(new File(audio + fileName));
            modelAndView.addObject("audio", fileName);
            return modelAndView;
        } else if (fileType.equals("mp4")) {
            file.transferTo(new File(video + fileName));
            modelAndView.addObject("video", fileName);
            return modelAndView;
        } else {
            return new ModelAndView("demo");
        }
    }

}
