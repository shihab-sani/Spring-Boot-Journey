package testproject.store.testing;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("email")
@Primary
public class emailNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Email Notification: " + message);
    }   
}
