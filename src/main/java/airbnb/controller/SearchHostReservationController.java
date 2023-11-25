package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.HouseDTO;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;

public class SearchHostReservationController {
    public Protocol houseListRequest(UserDTO userDTO) throws IOException, ClassNotFoundException {
        Protocol protocol = new Protocol(Protocol.TYPE_VIEW_MY_HOUSE, Protocol.CODE_REQUEST_MY_HOUSE_LIST, userDTO);

        MyIOStream.oos.writeObject(protocol);
        //
        return (Protocol) MyIOStream.ois.readObject();
    }

    public Protocol reservationListRequest(HouseDTO houseDTO) throws IOException, ClassNotFoundException {
        Protocol protocol = new Protocol(Protocol.TYPE_VIEW_MY_HOUSE, Protocol.CODE_REQUEST_RESERVATION_LIST, houseDTO);

        MyIOStream.oos.writeObject(protocol);
        //
        return (Protocol) MyIOStream.ois.readObject();
    }
}
