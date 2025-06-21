package testproject.store.testing;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${payment-gateway:stripe}")
    private String paymentGate;
  
    @Bean
    public paymentService bkash() {
        return new bkashPaymentService();
    }

    @Bean
    public paymentService paypal() {
        return new paypalPaymentService();
    }

    @Bean
    public paymentService stripe() {
        return new stripPaymentService();
    }

    @Bean
    public oderService oderService() {
        if (paymentGate.equals("stripe")) {
            return new oderService(stripe());
        }
        System.out.println(paymentGate);
        
        return new oderService(paypal());
    }
}
