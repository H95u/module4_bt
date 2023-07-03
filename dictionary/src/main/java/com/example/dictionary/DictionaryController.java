package com.example.dictionary;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DictionaryController {
    public static Map<String, String> dictionary = new HashMap<>();

    static {
        dictionary.put("hello", "xin chào");
        dictionary.put("you", "bạn");
        dictionary.put("eat", "ăn");
    }

    @GetMapping("/dictionary")
    public ModelAndView translate(@RequestParam("input") String input, Model model) {
        ModelAndView modelAndView = new ModelAndView("/result");
        String meaning = dictionary.get(input);
        if (meaning != null) {
            model.addAttribute("result", meaning);
        } else {
            model.addAttribute("result", "Not find this world");
        }
        return modelAndView;
    }
}
