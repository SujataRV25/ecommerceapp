	package com.example.ecommerceapp.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerceapp.entity.Admin;
import com.example.ecommerceapp.entity.User;
import com.example.ecommerceapp.repo.AdminRepo;


@Service
public class AdminService {
	
	@Autowired
	private AdminRepo adminRepo;
	
	
	public List<Admin> getAllAdmin(){
		return adminRepo.findAll();
	}
	
	public Admin getById(Long id) {
		return adminRepo.findById(id).orElseThrow(()-> new RuntimeException("Admin with id"+ id +" not found"));
		
	}
	public void createAdmin(Admin admin) {
		adminRepo.save(admin);
	}
	
	
	public void updateAdmin(Admin admin) {
		 adminRepo.findById(admin.getId()).orElseThrow(()-> new RuntimeException("Admin with id"+ admin.getId() +" not found"));
		 adminRepo.save(admin);
		
	}
	
	public void deleteAdmin(Long id) {
		adminRepo.findById(id).orElseThrow(()-> new RuntimeException("Admin with id"+ id +" not found"));
		adminRepo.deleteById(id);
		
		
	}
	
	public boolean verifyCredentials(String email, String passward) {
		
		Admin admin=adminRepo.findByEmail(email);
		if(admin.getPassword().equals(passward)) {
			return true;
		}
		return false;
	}

}
