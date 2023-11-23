package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;

import java.io.IOException;

public class SearchAllHouseController {
    public Protocol printAllHouseRequest() throws IOException, ClassNotFoundException {
        Protocol protocol = new Protocol(Protocol.TYPE_SEARCH_ALL_HOUSE, Protocol.CODE_SEARCH_ALL_HOUSE_REQUEST);
        MyIOStream.oos.writeObject(protocol);
        // 대기 wait
        return (Protocol) MyIOStream.ois.readObject();
    }

}
