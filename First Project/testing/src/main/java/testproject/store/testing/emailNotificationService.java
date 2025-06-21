package testproject.store.testing;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("email")
@Primary
public class emailNotificationService implements NotificationService {

    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private String port;

    @Override
    public void send(String message, String recipientEmail) {
        System.out.println("Sending email to " + recipientEmail + " via " + host + ":" + port);
        System.out.println("Message: " + message);
    } 
}
