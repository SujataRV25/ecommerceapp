package com.example.ecommerceapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ecommerceapp.Services.ProductService;
import com.example.ecommerceapp.entity.Admin;

@Controller
public class HomeController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/home")
	public String homePage() {
		return "HomePage";
		
	}
	
	public String productPage(Model model) {
		model.addAttribute("productList",productService.getAllProducts());
		
		return "ProductPage";
		
	}
	
	@GetMapping("/contacts")
	public String contactPage() {
		return "ContactPage";
	}
	
	@GetMapping("/aboutUs")
	public String aboutUs() {
		return "AboutUs";
		
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("admin",new Admin());
		return "Login";
		
	}

}
