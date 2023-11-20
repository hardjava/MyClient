package airbnb.persistence.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString

public class WaitingDTO {
    private int waitingId, houseId;

    public WaitingDTO(int waitingId, int houseId) {
        this.waitingId = waitingId;
        this.houseId = houseId;
    }
}
