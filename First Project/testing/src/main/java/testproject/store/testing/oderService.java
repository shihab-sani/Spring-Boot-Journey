package testproject.store.testing;
public class oderService {
    private final paymentService paymentService;

    // public oderService(@Qualifier("bkash") paymentService paymentService) {
    //     this.paymentService = paymentService;
    // }

    public oderService(paymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOrder() {
        paymentService.processPayment(100);
    }
}
