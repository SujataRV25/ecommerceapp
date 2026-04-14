package com.example.ecommerceapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerceapp.entity.Message;

@Repository
public interface ContactUsRepo extends JpaRepository<Message, Long>{
	
	

}
