package airbnb.persistence.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder

public class DiscountPolicyDTO {
    private int discountId, discountDay, discountOpt, houseId;

    public DiscountPolicyDTO(int discountId, int discountDay, int discountOpt, int houseId) {
        this.discountId = discountId;
        this.discountDay = discountDay;
        this.discountOpt = discountOpt;
        this.houseId = houseId;
    }
}
