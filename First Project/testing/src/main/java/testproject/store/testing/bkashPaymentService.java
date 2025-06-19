package testproject.store.testing;

import org.springframework.stereotype.Service;

@Service("bkash")
public class bkashPaymentService implements paymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " using bKash.");
    }
}
