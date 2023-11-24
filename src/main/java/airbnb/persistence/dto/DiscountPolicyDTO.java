package airbnb.persistence.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor

public class DiscountPolicyDTO implements Serializable {
    private int discountId, houseId, discountDay, discount_amount, discount_rate;

    public DiscountPolicyDTO(int discountDay, int discount_amount, int discount_rate, int houseId) {
        this.discountDay = discountDay;
        this.discount_amount = discount_amount;
        this.discount_rate = discount_rate;
        this.houseId = houseId;
    }
}
