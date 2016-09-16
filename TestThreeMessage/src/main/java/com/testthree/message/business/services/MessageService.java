package com.testthree.message.business.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.testthree.message.business.dto.MessageCreationRequest;
import com.testthree.message.model.entities.Message;

@Service
public interface MessageService {

	Message sendMessage(MessageCreationRequest request);
	
	List<Message> listAll();
	
	List<Message> findByRecipient(String recipient);
}
