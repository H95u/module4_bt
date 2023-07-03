package com.example.sandwich;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class HomeController {

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestParam("option") String[] option, Model model) {
        model.addAttribute("option", option);
        return "home";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String calculate(@RequestParam("input1") double num1,
                            @RequestParam("input2") double num2,
                            @RequestParam char op, Model model) {
        double result = 0;
        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                break;
        }
        model.addAttribute("result", result);
        return "calculator";
    }
}
