package airbnb.persistence.dto;

import airbnb.network.HouseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class HouseAndFeeDTO implements Serializable {
    private int houseId, hostId;
    private String houseName, houseAddress, houseIntroduce;
    private int bedroom, bathroom;
    private HouseType houseType;
    private int weekday, weekend;

    public String toString() {
        return String.format("%-50s%-50s%-10d%-10d%-20d%s", houseName, houseAddress, weekday, weekend
                , bedroom, houseType.toString());
    }
}
