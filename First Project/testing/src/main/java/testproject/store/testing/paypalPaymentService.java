package testproject.store.testing;

import org.springframework.stereotype.Service;

@Service("paypal")
public class paypalPaymentService implements paymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " using PayPal.");
    }
}