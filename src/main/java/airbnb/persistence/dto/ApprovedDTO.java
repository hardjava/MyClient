package airbnb.persistence.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder

public class ApprovedDTO {
    private int approveId, houseId;
    private boolean isApproved;

    public ApprovedDTO(int approveId, int houseId, boolean isApproved) {
        this.approveId = approveId;
        this.houseId = houseId;
        this.isApproved = isApproved;
    }
}
