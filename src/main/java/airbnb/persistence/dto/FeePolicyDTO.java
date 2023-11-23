package airbnb.persistence.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@Builder

public class FeePolicyDTO implements Serializable {
    private int feeId, houseId, weekday, weekend;

    public FeePolicyDTO(int feeId, int houseId, int weekday, int weekend) {
        this.feeId = feeId;
        this.houseId = houseId;
        this.weekday = weekday;
        this.weekend = weekend;
    }

    public FeePolicyDTO(int weekday, int weekend) {
        this.weekday = weekday;
        this.weekend = weekend;
    }
}
