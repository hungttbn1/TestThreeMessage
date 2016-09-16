package com.testthree.message.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testthree.message.business.dto.MessageCreationRequest;
import com.testthree.message.model.entities.Message;
import com.testthree.message.model.repos.MessageRepository;


@Service
public class MessageServiceImpl implements MessageService{

	
	@Autowired
	private MessageRepository messageRepository;
	
	
	@Override
	public Message sendMessage(MessageCreationRequest request) {
		
		Message message = new Message();
		
		message.setSender(request.getSender());
		message.setRecipient(request.getRecipient());
		message.setTitle(request.getTitle());
		message.setContent(request.getContent());
		
		
		return messageRepository.save(message);
	}


	@Override
	public List<Message> listAll() {
		
		return messageRepository.findAll();
	}


	@Override
	public List<Message> findByRecipient(String recipient) {
		
		return messageRepository.findByRecipient(recipient);
	}
	
	



}
