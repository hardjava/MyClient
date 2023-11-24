package airbnb.persistence.dto;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor

public class ApprovedDTO {
    private int approveId, houseId;
    private boolean isApproved;

    public ApprovedDTO(int houseId, boolean isApproved) {
        this.houseId = houseId;
        this.isApproved = isApproved;
    }
}
