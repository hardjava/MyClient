package airbnb.persistence.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder

public class DiscountOptDTO {
    private int discountOpt, discountAmount, discountRate;

    public DiscountOptDTO(int discountOpt, int discountAmount, int discountRate) {
        this.discountOpt = discountOpt;
        this.discountAmount = discountAmount;
        this.discountRate = discountRate;
    }
}
