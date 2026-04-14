package com.example.ecommerceapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.service.annotation.DeleteExchange;

import com.example.ecommerceapp.Services.UserService;
import com.example.ecommerceapp.entity.User;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	@PostMapping("/add/user")
	public String addUser(User user) {
		userService.createUser(user);
		
		return "LoginPage";
	}
	@GetMapping("/update/user/{id}")
	public String updateUser(@PathVariable Long id, Model model) {
		model.addAttribute("admin", userService.getById(id));
		
		return "UpdateUser";
		
	}
	
	@PostMapping("/update/user")
	public String updateUser(User user) {
		userService.updateUser(user);
		return "redirect:/admin/home";
	}
	
	@DeleteMapping("delete/user/{id}")
	public String deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return "redirect:/admin/home";
	}
	
	

}
