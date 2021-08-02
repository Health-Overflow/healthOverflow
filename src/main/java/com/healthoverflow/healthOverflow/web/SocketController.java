package com.healthoverflow.healthOverflow.web;


import com.healthoverflow.healthOverflow.domain.OutputMessage;
import com.healthoverflow.healthOverflow.domain.UserMsg;
import org.h2.engine.User;
import org.springframework.messaging.handler.annotation.MessageMapping;
        import org.springframework.messaging.handler.annotation.SendTo;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.util.HtmlUtils;

@Controller
public class SocketController {
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(UserMsg userMsg) throws Exception {
        Thread.sleep(500); // simulated delay
        return new OutputMessage(userMsg.getUserName(), userMsg.getUserImage(), userMsg.getUserMessage());
    }
}
