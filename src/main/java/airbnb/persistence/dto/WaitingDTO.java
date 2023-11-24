package airbnb.persistence.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor

public class WaitingDTO {
    private int waitingId, houseId;

    public WaitingDTO(int houseId) {
        this.houseId = houseId;
    }
}
