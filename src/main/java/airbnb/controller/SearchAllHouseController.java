package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;

import java.io.IOException;

public class SearchAllHouseController {
    public Protocol allHouseRequest() throws IOException, ClassNotFoundException {
        Protocol protocol = new Protocol(Protocol.TYPE_SEARCH_ALL_HOUSE, Protocol.CODE_SEARCH_ALL_HOUSE_REQUEST);
        MyIOStream.oos.writeObject(protocol);
        // 대기 wait
        return (Protocol) MyIOStream.ois.readObject();
    }

    public Protocol ascendingHouseRequest() throws IOException, ClassNotFoundException {
        Protocol protocol = new Protocol(Protocol.TYPE_SEARCH_ALL_HOUSE, Protocol.CODE_SEARCH_ALL_HOUSE_REQUEST_ASC);
        MyIOStream.oos.writeObject(protocol);

        return (Protocol) MyIOStream.ois.readObject();
    }

    public Protocol descendingHouseRequest() throws IOException, ClassNotFoundException {
        Protocol protocol = new Protocol(Protocol.TYPE_SEARCH_ALL_HOUSE, Protocol.CODE_SEARCH_ALL_HOUSE_REQUEST_DESC);
        MyIOStream.oos.writeObject(protocol);

        return (Protocol) MyIOStream.ois.readObject();
    }
}
