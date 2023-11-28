package airbnb.persistence.dto;

import airbnb.network.HouseType;

import java.sql.Date;
import java.util.List;

public class FilterDTO {
    private String houseName;
    private Date checkIn, checkOut;
    private int guestNum;
    private HouseType houseType;
    private List<String> amenities;
}
