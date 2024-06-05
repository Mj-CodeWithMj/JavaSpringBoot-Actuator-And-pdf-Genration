package com.example.acutor.actuatorexample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.acutor.actuatorexample.model.Product;
import com.example.acutor.actuatorexample.repository.ProductRepositroy;

@Controller
public class RedirectController {
	
	@Autowired
	ProductRepositroy productRepositroy;
	
	@GetMapping("/index")
	public String index(Model model) {
        List<Product> product = productRepositroy.findAll();
        model.addAttribute("list", product);
        return "home";
	}

}
