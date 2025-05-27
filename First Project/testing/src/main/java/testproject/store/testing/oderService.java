package testproject.store.testing;

import org.springframework.stereotype.Component;

@Component
public class oderService {
    private paymentService paymentService;

    public oderService(paymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void setPaymentService(paymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOrder() {
        paymentService.processPayment(100);
    }
}
