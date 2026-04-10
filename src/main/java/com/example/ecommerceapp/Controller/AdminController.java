package com.example.ecommerceapp.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.service.annotation.DeleteExchange;

import com.example.ecommerceapp.Services.AdminService;
import com.example.ecommerceapp.Services.OrderService;
import com.example.ecommerceapp.Services.ProductService;
import com.example.ecommerceapp.Services.UserService;
import com.example.ecommerceapp.entity.Admin;
import com.example.ecommerceapp.entity.Order;
import com.example.ecommerceapp.entity.Product;
import com.example.ecommerceapp.entity.User;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	
	private User user;
	
	
	@GetMapping("/verify/credentials")
	public String verifyCretentials(@ModelAttribute("admin") Admin admin, Model model) {
		
		if(adminService.verifyCredentials(admin.getEmail(), admin.getPassword())) {
			return "";
		}
		
		model.addAttribute("error","Invalid email or password");
		return "Login";
	}
	
	@GetMapping("/admin/home")
	public String adminHomePage(Model model) {
		model.addAttribute("adminList",adminService.getAllAdmin());
		model.addAttribute("userList",userService.getAllUser());
		model.addAttribute("orderList",orderService.getAllOrder());
		model.addAttribute("productList",productService.getAllProducts());
		
		return "AdminHomePage";
		
	}
	
	@GetMapping("/add/admin")
	public String createAdmin() {
		return "AddAdmin";
		
	}
	
	
	@PostMapping("/add/admin")
	public String createAdmin(Admin admin) {
		adminService.createAdmin(admin);
		return "/admin/home";
	}
	
	
	@GetMapping("/update/admin/{id}")
	public String updateAdmin(@PathVariable Long id, Model model) {
		model.addAttribute("admin", adminService.getById(id));
		
		return "UpdateUser";
		
	}
	
	@PostMapping("/update/admin")
	public String updateAdmin(Admin admin) {
		adminService.updateAdmin(admin);
		return "/admin/home";
	}
	
	@GetMapping("/user/login")
	public String userLogin(User user, Model model) {
		if(userService.verifyCredentials(user.getEmail(), user.getPassword())) {
			user=userService.findByEmail(user.getEmail());
			model.addAttribute("orderList", orderService.findOrdersByUser(user));
			
			
			return "ProductPage";
		}

		model.addAttribute("error","Invalid email or password");
		return "Login";
		
	}
	
	@GetMapping("/place/order")
	public String placeOrder(Order order, Model model) {
		double totalamount=order.getPrice()* order.getQuantity();
		order.setAmount(totalamount); 
		order.setUser(user);
		order.setDate(new Date());
		
		orderService.createOrder(order);
		model.addAttribute("amount", totalamount);
		return "OrderStatus";
	}
	
	@DeleteMapping("/delete/admin")
	public String deleteAdmin(@PathVariable Long id) {
		adminService.deleteAdmin(id);
		
		return "admin/home";
	}
	
	@GetMapping("/product/search")
	public String productSearch(String name, Model model) {
		Product product=productService.findProductByName(name);
		if(product != null) {
			model.addAttribute("orderList", orderService.findOrdersByUser(user));
			model.addAttribute("product", product);
			
			return "ProductPage";
			
		}
		model.addAttribute("error","Product was not found...");
		model.addAttribute("orderList", orderService.findOrdersByUser(user));
		
	
		return "ProductPage";
	}
	

}
