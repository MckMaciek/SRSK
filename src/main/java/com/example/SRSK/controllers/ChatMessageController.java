package com.example.SRSK.controllers;


import com.example.SRSK.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ChatMessageController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage get(Principal principal, ChatMessage chatMessage){    //endpoint dla js

        chatMessage.setUser(principal.getName());

        DateFormat dateformat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date currentDate = new Date();
        chatMessage.setData(dateformat.format(currentDate));


        return chatMessage;
    }


}
