package com.example.ecommerceapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ecommerceapp.Services.ContactUsService;
import com.example.ecommerceapp.entity.Message;

@Controller
public class ContactUsController {	
	
	@Autowired
	private ContactUsService contactUsService;
	
	@PostMapping("/send/message")
	public String sendMessage(Message message,Model model) {
	 	contactUsService.creatMessage(message);
		
		model.addAttribute("confirmation","Your message has been successfully sent!");
		
		return "contactUs";
		
	}

}
