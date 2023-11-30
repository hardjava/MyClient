package airbnb.persistence.dto;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor

public class DiscountPolicyDTO implements Serializable {
    private int discountId, houseId, discountDay, discount_amount, discount_rate;
    private Date discountStart, discountEnd;
    public DiscountPolicyDTO() {
    }

    public DiscountPolicyDTO(int houseId, int discountDay, int discount_amount, int discount_rate, Date discountStart, Date discountEnd) {
        this.houseId = houseId;
        this.discountDay = discountDay;
        this.discount_amount = discount_amount;
        this.discount_rate = discount_rate;
        this.discountStart = discountStart;
        this.discountEnd = discountEnd;
    }
}
