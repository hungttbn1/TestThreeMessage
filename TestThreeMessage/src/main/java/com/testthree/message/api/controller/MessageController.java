package com.testthree.message.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.testthree.message.business.common.ErrorInfo;
import com.testthree.message.business.dto.MessageCreationRequest;
import com.testthree.message.business.dto.ResponseObject;
import com.testthree.message.business.services.MessageService;
import com.testthree.message.model.entities.Message;


@Controller
@RequestMapping(value = "/message")
public class MessageController {


	@Autowired
	private MessageService messageService;
	// private List<String> listEmail = new ArrayList<String>();

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public final ResponseEntity<Object> sendMessage(@Valid @RequestBody MessageCreationRequest request,
																		BindingResult result) {

		ResponseObject<Object> response = new ResponseObject<Object>();
		Message message = new Message();

		if (result.hasErrors()) {
			response.setError(ErrorInfo.DATA_FORMAT_INVAILD);

		} else {			
				message = messageService.sendMessage(request);
				response.setResponseData(message);
			
		}

		return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.OK);

		/*
		 * for (String email : request.getRecipient().split(",")){
		 * listEmail.add(email); }
		 * 
		 * if(listEmail.isEmpty()){ response.setError(ErrorInfo.CONTENT_NULL);
		 * }else{ //request.setRecipients(listEmail); }
		 */

		/*
		 * if(StringUtils.isEmpty(request.getSender())){
		 * response.setError(ErrorInfo.SENDER_NULL);
		 * 
		 * }else if(StringUtils.isEmpty(request.getRecipient())){
		 * response.setError(ErrorInfo.RECIPIENT_NULL);
		 * 
		 * }else if(StringUtils.isEmpty(request.getTitle())){
		 * response.setError(ErrorInfo.TITLE_NULL);
		 * 
		 * }else if(StringUtils.isEmpty(request.getContent())){
		 * response.setError(ErrorInfo.CONTENT_NULL);
		 * 
		 * }else if(result.hasErrors()){
		 * response.setError(ErrorInfo.BAD_REQUEST);
		 * 
		 * }else{ message = messageService.sendMessage(request);
		 * response.setResponseData(message); }
		 */

	}

	@RequestMapping(value = "/findByRecipient", method = RequestMethod.GET)
	@ResponseBody
	public final ResponseEntity<Object> findByRecipient(
			@RequestParam(value = "recipient", required = false) String recipient) {

		ResponseObject<Object> response = new ResponseObject<Object>();
		List<Message> messages;

		if (StringUtils.isEmpty(recipient)) {
			messages = messageService.listAll();

		} else {
			messages = messageService.findByRecipient(recipient);

			if (messages.isEmpty()) {
				response.setError(ErrorInfo.NO_RECIPIENT);
				messages = null;
			}

		}
		response.setResponseData(messages);
		return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.OK);
	}

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	@ResponseBody
	public final ResponseEntity<Object> listAll() {

		ResponseObject<Object> response = new ResponseObject<Object>();
		List<Message> messages = messageService.listAll();

		response.setResponseData(messages);

		return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.OK);
	}
}
