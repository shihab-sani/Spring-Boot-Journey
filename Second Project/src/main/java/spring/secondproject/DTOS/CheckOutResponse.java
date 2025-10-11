package spring.secondproject.DTOS;

import lombok.Data;

@Data
public class CheckOutResponse {
    private Long orderId;

    public CheckOutResponse(Long orderId) {
        this.orderId = orderId;
    }
}
