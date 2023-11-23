package airbnb.persistence.dto;

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
    private int houseId;
    private String houseName, houseAddress, houseIntroduce;
    private int capacity, bedroom, bathroom;

    public String toString() {
        return String.format("%-20s%-20s", houseName, houseAddress);
    }
}
