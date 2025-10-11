package spring.secondproject.DTOS;

import lombok.Data;

import java.util.UUID;

@Data
public class CheckOutRequest {
    private UUID cartId;
}
