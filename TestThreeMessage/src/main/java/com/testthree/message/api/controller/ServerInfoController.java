package com.testthree.message.api.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping(value = "/")
public class ServerInfoController {

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public final String getServerInfo(){
		return "OKKK";
	}
	
}
