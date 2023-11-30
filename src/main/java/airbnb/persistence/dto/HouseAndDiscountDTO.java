package airbnb.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class HouseAndDiscountDTO implements Serializable {
    private HouseDTO houseDTO;
    private DiscountPolicyDTO discountPolicyDTO;

    public HouseAndDiscountDTO(int houseId, int hostId, String houseName, String houseAddress, String houseIntroduce, int bedroom, int bathroom,
                               Integer discountDay, Integer discount_amount, Integer discount_rate, Date discountStart, Date discountEnd) {
        this.houseDTO = new HouseDTO(hostId, houseName, houseAddress, houseIntroduce, bedroom, bathroom);
        if (discountDay == null)
            discountDay = discount_amount = discount_rate = 0;
        else {
            if (discount_amount == null)
                discount_rate = 0;
            if (discount_rate == null)
                discount_rate = 0;
        }
        this.discountPolicyDTO = new DiscountPolicyDTO(discountDay, discount_amount, discount_rate, houseId, discountStart, discountEnd);
    }
    public String toString() {
        return String.format("%-20s%-10d%-15d%-15d", houseDTO.getHouseName(), discountPolicyDTO.getDiscountDay(), discountPolicyDTO.getDiscount_amount(), discountPolicyDTO.getDiscount_rate());
    }
}
