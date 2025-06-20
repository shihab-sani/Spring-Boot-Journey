package testproject.store.testing;

import org.springframework.stereotype.Service;

@Service("sms")
public class smsNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("SMS Notification: " + message);
    }
}
