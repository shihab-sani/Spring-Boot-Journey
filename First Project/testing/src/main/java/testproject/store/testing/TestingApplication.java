package testproject.store.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestingApplication.class, args);

		// var paymentService = new oderService(new stripPaymentService());
		// paymentService.placeOrder();

		// var paymentService = new oderService(new paypalPaymentService());
		// paymentService.placeOrder();

	}
}
