package com.example.ecommerceapp.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerceapp.entity.Message;
import com.example.ecommerceapp.repo.ContactUsRepo;


@Service
public class ContactUsService {
	
	
	@Autowired
	private ContactUsRepo contactRepo;
	
	public List<Message> getAllMessage(){
		return contactRepo.findAll();
		}
		
		public Message getById(Long id) {
			return contactRepo.findById(id).orElseThrow(()-> new RuntimeException("Message( with id"+ id +" not found"));
			
		}
		
		public void creatMessage(Message message) {
			contactRepo.save(message);
		}
		
		public void updateMessage(Message message) {
			contactRepo.findById(message.getId()).orElseThrow(()-> new RuntimeException("Message with id"+ message.getId() +" not found"));
			contactRepo.save(message);
			
		}
		
		public void deleteMessage(Long id) {
			contactRepo.findById(id).orElseThrow(()-> new RuntimeException("Message( with id"+ id +" not found"));
			contactRepo.deleteById(id);
			
			
		}

}
