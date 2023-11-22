package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;

import java.io.IOException;

public class StayedHouseController {
    public Protocol completedStayRequest() throws IOException, ClassNotFoundException {
        Protocol protocol = new Protocol(Protocol.TYPE_STAYED_HOUSE, Protocol.CODE_STAYED_HOUSE_LIST_REQUEST);
        MyIOStream.oos.writeObject(protocol);
        //대기
        return (Protocol) MyIOStream.ois.readObject();
    }
}
