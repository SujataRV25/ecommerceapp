package com.example.ecommerceapp.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerceapp.entity.User;
import com.example.ecommerceapp.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	
	public List<User> getAllUser(){
	return userRepo.findAll();
	}
	
	public User getById(Long id) {
		return userRepo.findById(id).orElseThrow(()-> new RuntimeException("User with id"+ id +" not found"));
		
	}
	
	public void createUser(User user) {
		userRepo.save(user);
	}
	
	public void updateUser(User user) {
		userRepo.findById(user.getId()).orElseThrow(()-> new RuntimeException("User with id"+ user.getId() +" not found"));
		userRepo.save(user);
		
	}
	
	public void deleteUser(Long id) {
		userRepo.findById(id).orElseThrow(()-> new RuntimeException("User with id"+ id +" not found"));
		userRepo.deleteById(id);
		
		
	}
	
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
		
	}
	
	public boolean verifyCredentials(String email, String passward) {
		
		User user=userRepo.findByEmail(email);
		if(user.getPassword().equals(passward)) {
			return true;
		}
		return false;
	}


}
