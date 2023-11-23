package airbnb.persistence.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder

public class AmenitiesDTO {
    private int amenitiesId, houseId, typeId, count;
    private String amenities;

    public AmenitiesDTO(int amenitiesId, int houseId, int typeId, int count, String amenities) {
        this.amenitiesId = amenitiesId;
        this.houseId = houseId;
        this.typeId = typeId;
        this.count = count;
        this.amenities = amenities;
    }
}
