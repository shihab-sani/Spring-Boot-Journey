package spring.secondproject.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.secondproject.Entities.Message;

@RestController
public class MessageController {
    @RequestMapping("/hello")
    public Message getMessage() {
        return new Message("Hello, this is a message from the MessageController!");
    }
}
