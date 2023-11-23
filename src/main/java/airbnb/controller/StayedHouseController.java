package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;

public class StayedHouseController {
    public Protocol completedStayRequest(UserDTO userDTO) throws IOException, ClassNotFoundException {
        Protocol protocol = new Protocol(Protocol.TYPE_STAYED_HOUSE, Protocol.CODE_STAYED_HOUSE_LIST_REQUEST, userDTO);
        MyIOStream.oos.writeObject(protocol);
        //대기
        return (Protocol) MyIOStream.ois.readObject();
    }
}
