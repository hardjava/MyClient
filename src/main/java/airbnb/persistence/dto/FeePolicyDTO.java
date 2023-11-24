package airbnb.persistence.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor

public class FeePolicyDTO implements Serializable {
    private int feeId, houseId, weekday, weekend;


    public FeePolicyDTO(int weekday, int weekend) {
        this.weekday = weekday;
        this.weekend = weekend;
    }

    public FeePolicyDTO(FeePolicyDTO feePolicyDTO) {
        this.feeId = feePolicyDTO.getFeeId();
        this.houseId = feePolicyDTO.getHouseId();
        this.weekday = feePolicyDTO.getWeekday();
        this.weekend = feePolicyDTO.getWeekend();
    }
}
