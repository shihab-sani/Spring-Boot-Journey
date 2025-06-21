package testproject.store.testing;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

// @Service("stripe")
// @Primary
public class stripPaymentService implements paymentService {

    @Value("${stripe.apiURL}")
    private String apiURL;

    @Value("${stripe.currency}")
    private List<String> supportedCurrencies;

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " using Stripe.");
        System.out.println("API URL: " + apiURL);
        System.out.println("Supported currencies: " + String.join(", ", supportedCurrencies));
    }
}
