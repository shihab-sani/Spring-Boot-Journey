package spring.secondproject.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.secondproject.DTOS.CheckOutRequest;
import spring.secondproject.DTOS.CheckOutResponse;
import spring.secondproject.DTOS.ErrorDtos;
import spring.secondproject.Exceptions.CartEmptyException;
import spring.secondproject.Exceptions.CartNotFoundExceptions;
import spring.secondproject.Services.CheckoutServices;

@AllArgsConstructor
@RestController
@RequestMapping( "/checkout")
public class CheckOutController {
    private final CheckoutServices checkoutServices;

    @PostMapping
    public CheckOutResponse checkOut(@Valid @RequestBody CheckOutRequest request) {
        return checkoutServices.checkout(request);
    }

    @ExceptionHandler({CartEmptyException.class, CartNotFoundExceptions.class})
    public ResponseEntity<ErrorDtos> handlerBadRequest(Exception exc) {
        return ResponseEntity.badRequest().body(new ErrorDtos(exc.getMessage()));
    }
}
