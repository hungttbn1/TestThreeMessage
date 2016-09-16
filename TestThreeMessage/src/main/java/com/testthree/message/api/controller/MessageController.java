package com.testthree.message.api.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	private String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

	Pattern pattern = Pattern.compile(regex);

	@Autowired
	private MessageService messageService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public final ResponseEntity<Object> sendMessage(@Valid @RequestBody MessageCreationRequest request,
																		BindingResult result) {

		ResponseObject<Object> response = new ResponseObject<Object>();
		Message message = new Message();
		boolean error = false;

		if (StringUtils.isEmpty(request.getRecipient()) == false) {
			
			for (String email : request.getRecipient().split(",")) {

				Matcher matcher = pattern.matcher(email);
				if (matcher.matches() == false) {
					error = true;
					break;
				}
			}
		}
		if (result.hasErrors() || error == true) {
			response.setError(ErrorInfo.DATA_FORMAT_INVAILD);

		} else {
			message = messageService.sendMessage(request);
			response.setResponseData(message);
			
		}
		return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.OK);
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
