package testproject.store.testing;

public class stripPaymentService implements paymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " using Stripe.");
    }
}
