package testproject.store.testing;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class oderService {
    private paymentService paymentService;

    public oderService(@Qualifier("bkash") paymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOrder() {
        paymentService.processPayment(100);
    }
}
