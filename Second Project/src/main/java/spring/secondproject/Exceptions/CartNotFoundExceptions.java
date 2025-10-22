package spring.secondproject.Exceptions;

public class CartNotFoundExceptions extends RuntimeException{
    public CartNotFoundExceptions() {
        super("Cart not found");
    }
}
