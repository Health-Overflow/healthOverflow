package com.healthoverflow.healthOverflow.web;


import com.healthoverflow.healthOverflow.domain.ApplicationUser;
import com.healthoverflow.healthOverflow.domain.OutputMessage;
import com.healthoverflow.healthOverflow.domain.UserMsg;
import com.healthoverflow.healthOverflow.infrastructure.ApplicationUserRepo;
import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@Controller
public class SocketController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(UserMsg userMsg) throws Exception {
        Thread.sleep(500); // simulated delay
        return new OutputMessage(userMsg.getUserName(), userMsg.getUserImage(), userMsg.getUserMessage());
    }


    @GetMapping("/chat")
    public String messagePage(){
        return "chat";
    }


}
