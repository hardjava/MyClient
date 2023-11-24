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

public class HouseDTO implements Serializable {
    private int houseId, hostId;
    private String houseName, houseAddress, houseIntroduce;
    private int bedroom, bathroom;
    private HouseType houseType;

    public HouseDTO(int hostId, String houseName, String houseAddress, String houseIntroduce, int bedroom, int bathroom, HouseType houseType) {
        this.hostId = hostId;
        this.houseName = houseName;
        this.houseAddress = houseAddress;
        this.houseIntroduce = houseIntroduce;
        this.bedroom = bedroom;
        this.bathroom = bathroom;
        this.houseType = houseType;
    }

    public HouseDTO(String houseName, String houseAddress, String houseIntroduce, int bedroom, int bathroom) {
        this.houseName = houseName;
        this.houseAddress = houseAddress;
        this.houseIntroduce = houseIntroduce;
        this.bedroom = bedroom;
        this.bathroom = bathroom;
    }

    public HouseDTO(String houseName, String houseAddress, String houseIntroduce, int bedroom, int bathroom, HouseType houseType) {
        this.houseName = houseName;
        this.houseAddress = houseAddress;
        this.houseIntroduce = houseIntroduce;
        this.bedroom = bedroom;
        this.bathroom = bathroom;
        this.houseType = houseType;
    }

    public String toString() {
        return String.format("%-40s%-40s", houseName, houseAddress);
    }
}
