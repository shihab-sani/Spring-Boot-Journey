package testproject.store.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TestingApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TestingApplication.class, args);
		context.getBean(oderService.class);

		var oderService = context.getBean(oderService.class);
		oderService.placeOrder();

		var UserService = context.getBean(UserService.class);
		UserService.registerUser(new User("Shihab","mdsani633@gmail.com",2320590,"1234567890"));
		UserService.registerUser(new User("Sani","ssani@kent.edu",2320590,"1234567890"));

		// var notificationManager = context.getBean(NotificationManager.class);
		// notificationManager.sendNotification("Order placed successfully!");
	}
}
