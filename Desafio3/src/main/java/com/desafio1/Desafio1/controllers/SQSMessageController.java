package com.desafio1.Desafio1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sqs.model.Message;
import com.desafio1.Desafio1.domains.User;
import com.desafio1.Desafio1.services.UserService;
import com.desafio1.Desafio1.util.AwsSqsUtil;

@RestController
@RequestMapping("/sqs")
public class SQSMessageController {
	
	private AwsSqsUtil awsSqsUtil;
	private UserService userService;

	@Autowired
	public SQSMessageController(AwsSqsUtil awsSqsUtil, UserService userService) {
		this.awsSqsUtil = awsSqsUtil;
		this.userService = userService;
	}	

	@PostMapping("/produce")
	public String sendMessageToSQS() {
		List<User> users = userService.getAllUsers();
		for(User user : users){
			String response = user.toString();
			this.awsSqsUtil.produceMessageToSQS(response);
		}
		return "Message pushed to sqs: " + users;
	}
	
	@GetMapping("/consume")
	public List<Message> retrieveMessageFromSQS() {
		return awsSqsUtil.consumeMessageFromSQS();
	}
}
