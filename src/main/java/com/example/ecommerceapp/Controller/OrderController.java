package com.example.ecommerceapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ecommerceapp.Services.OrderService;
import com.example.ecommerceapp.entity.Order;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	
	
}
