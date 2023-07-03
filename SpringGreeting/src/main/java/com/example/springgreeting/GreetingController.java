package com.example.springgreeting;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GreetingController {
    @GetMapping("/greeting")
    public String greeting(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/hello")
    public String greeting1() {
        return "index1";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam double input, Model model) {
        double usd = input * 23000;
        model.addAttribute("result", usd);
        return "result";
    }
}
