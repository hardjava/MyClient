package airbnb.persistence.dto;

import airbnb.network.HouseType;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@AllArgsConstructor
public class FilterDTO implements Serializable {
    private String houseName;
    private Date checkIn, checkOut;
    private int guestNum;
    private HouseType houseType;
    private List<String> amenities;
}
