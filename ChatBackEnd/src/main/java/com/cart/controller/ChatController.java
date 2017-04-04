package com.cart.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cart.model.Message;
import com.cart.model.OutputMessage;

@Controller
public class ChatController {
	
	Logger log = LoggerFactory.getLogger(ChatController.class);

	@MessageMapping("/chat")
	@SendTo("/topic/message")
	public OutputMessage sendMessage(Message message) {
		log.debug("calling the method sendMessage");
		log.debug("Message : " + message.getMessage());
		log.debug("MessageId : " + message.getId());
		
		return new OutputMessage(message, new Date());
	}
}
