package airbnb.persistence.dto;

import airbnb.network.HouseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Builder
@AllArgsConstructor

public class HouseAndHostDTO implements Serializable {
    private int houseId, hostId;
    private String houseName, houseAddress, houseIntroduce;
    private int bedroom, bathroom;
    private HouseType houseType;
    private String loginId, hostName;

    public String toString() {
        return String.format("%-50s%-70s%-10s%-20s", houseName, houseAddress, hostName, loginId);
    }
}