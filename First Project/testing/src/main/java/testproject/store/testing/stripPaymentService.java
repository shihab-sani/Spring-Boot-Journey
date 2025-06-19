package testproject.store.testing;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("stripe")
@Primary
public class stripPaymentService implements paymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " using Stripe.");
    }
}
