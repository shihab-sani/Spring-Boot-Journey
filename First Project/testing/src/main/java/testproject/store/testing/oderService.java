package testproject.store.testing;

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
