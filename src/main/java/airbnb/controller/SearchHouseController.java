package airbnb.controller;

import airbnb.network.HouseType;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.FilterDTO;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class SearchHouseController {
    public Protocol filteringHouseRequest(String houseName, String checkIn, String checkOut, int guestNum, HouseType houseType, List<String> amenities) throws IOException, ClassNotFoundException {
        Date checkInDate = null, checkOutDate = null;
        if (!checkIn.equals("")) {
            checkInDate = Date.valueOf(checkIn);
            checkOutDate = Date.valueOf(checkOut);
        }
        FilterDTO filterDTO = new FilterDTO(houseName, checkInDate, checkOutDate, guestNum, houseType, amenities);
        Protocol protocol = new Protocol(Protocol.TYPE_REQUEST_SEARCH, Protocol.CODE_REQUEST_SEARCH_WITH_FILTER, filterDTO);
        MyIOStream.oos.writeObject(protocol);

        return (Protocol) MyIOStream.ois.readObject();
    }
}
