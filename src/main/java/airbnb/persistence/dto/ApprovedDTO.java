package airbnb.persistence.dto;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor

public class ApprovedDTO {
    private int approveId, houseId;
    private int isApproved;

    public ApprovedDTO(int houseId, int isApproved) {
        this.houseId = houseId;
        this.isApproved = isApproved;
    }
}
