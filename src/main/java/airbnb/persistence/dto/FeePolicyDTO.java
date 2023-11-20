package airbnb.persistence.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder

public class FeePolicyDTO {
    private int feeId, houseId, weekday, weekend;

    public FeePolicyDTO(int feeId, int houseId, int weekday, int weekend) {
        this.feeId = feeId;
        this.houseId = houseId;
        this.weekday = weekday;
        this.weekend = weekend;
    }
}
