package com.example.ecommerceapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import com.example.ecommerceapp.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	
	public User findByEmail(String Email);

}
