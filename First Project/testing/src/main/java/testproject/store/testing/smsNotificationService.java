package testproject.store.testing;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("sms")
public class smsNotificationService implements NotificationService {

    @Value("${sms.host}")
    private String host;

    @Value("${sms.port}")
    private String port;

    @Override
    public void send(String message, String recipientEmail) {
        System.out.println("Sending SMS to " + recipientEmail + " via " + host + ":" + port);
        System.out.println("Message: " + message);
    }
}
