package airbnb.persistence.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class AmenitiesDTO implements Serializable {
    private int amenitiesId, houseId;
    private String amenities;
    private int typeId, count;

    public AmenitiesDTO(int houseId, String amenities, int typeId, int count) {
        this.houseId = houseId;
        this.amenities = amenities;
        this.typeId = typeId;
        this.count = count;
    }

    public AmenitiesDTO(String amenities, int typeId) {
        this.amenities = amenities;
        this.typeId = typeId;
    }
}
