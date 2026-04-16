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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	
	//private User user;
	
	
	@GetMapping("/admin/verify/credentials")
	public String verifyCretentials(@ModelAttribute("admin") Admin admin, Model model) {
		
		if(adminService.verifyCredentials(admin.getEmail(), admin.getPassword())) {
			model.addAttribute("admin",new Admin());
			model.addAttribute("user",new User());
			model.addAttribute("product",new Product());
			return "redirect:/admin/home";
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
	
	
	@PostMapping("/add/admin")
	public String createAdmin(Admin admin) {
		adminService.createAdmin(admin);
		return "redirect:/admin/home";
	}
	
	
	@PostMapping("/update/admin")
	public String updateAdmin(Admin admin) {
		adminService.updateAdmin(admin);
		return "redirect:/admin/home";
	}
	
	@GetMapping("/update/admin/{id}")
	public String updateUser(@PathVariable Long id, Model model) {
		model.addAttribute("admin", adminService.getById(id));
		
		return "UpdateAdmin";
		
	}
	
	@PostMapping("/user/login")
	public String userLogin(User user, RedirectAttributes redirectAttributes) {
		if(userService.verifyCredentials(user.getEmail(), user.getPassword())) {
			user=userService.findByEmail(user.getEmail());
			//model.addAttribute("ordersList", orderService.findOrdersByUser(user));
		redirectAttributes.addAttribute("userId",user.getId());
			return "redirect:/user/home";
		}

		redirectAttributes.addAttribute("error","Invalid email or password");
		return "LoginPage";
		
	}
	
	@GetMapping("/user/home")
	public String userHome(@ModelAttribute("userId") Long userId,
							@ModelAttribute("error") String error, @ModelAttribute("messageSuccess") String messageSuccess, Model model) {
		User user=userService.getById(userId);
		if(!error.isEmpty()) {
			model.addAttribute("error","Invalid email or password");
			
		}
		if(!messageSuccess.isEmpty()) {
			model.addAttribute("messageSuccess",messageSuccess);
			
		}
		model.addAttribute("ordersList",orderService.findOrdersByUser(user));
		
		return "BuyProductPage";
	}
	
	@PostMapping("/place/order")
	public String placeOrder(Order order, Long userId, RedirectAttributes redirectAttributes) {
		double totalamount=order.getPrice()* order.getQuantity();
		order.setAmount(totalamount);
		order.setDate(new Date());
		
		User user=userService.getById(userId);
		order.setUser(user);
		
		orderService.createOrder(order);
		redirectAttributes.addAttribute("userId",userId);
		redirectAttributes.addAttribute("messageSuccess","The Order has been placed!");
		return "redirect:/user/home";
	} 
	
	@GetMapping("/delete/admin/{id}")
	public String deleteAdmin(@PathVariable Long id) {
		adminService.deleteAdmin(id);
		
		return "redirect:/admin/home";
	}
		
	@PostMapping("/product/search")
	public String productSearch(String name, Long userId, Model model) {
		Product product=productService.findProductByName(name);
		User user=userService.getById(userId);
		model.addAttribute("ordersList", orderService.findOrdersByUser(user));
		if(product != null) {	
			model.addAttribute("product", product);		
		}else {
		model.addAttribute("messageError","Product was not found...");
		}
		model.addAttribute("userId",user.getId());
	
		return "BuyProductPage";
	}
	

}
