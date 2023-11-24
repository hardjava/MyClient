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

    public FeePolicyDTO(int houseId, int weekday, int weekend) {
        this.houseId = houseId;
        this.weekday = weekday;
        this.weekend = weekend;
    }
}
