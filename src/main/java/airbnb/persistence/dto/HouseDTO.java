package airbnb.persistence.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder

public class HouseDTO {
    private int house_id, capacity, bedroom, bathroom;
    private String house_name, house_address, house_introduce;

    public HouseDTO(int house_id, int capacity, int bedroom, int bathroom, String house_name, String house_address, String house_introduce) {
        this.house_id = house_id;
        this.capacity = capacity;
        this.bedroom = bedroom;
        this.bathroom = bathroom;
        this.house_name = house_name;
        this.house_address = house_address;
        this.house_introduce = house_introduce;
    }
}
