package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;

import java.io.IOException;

public class AmenitiesRequestController {
    public Protocol basicAmenitiesListRequest() throws IOException, ClassNotFoundException {
        Protocol protocol = new Protocol(Protocol.TYPE_HOUSE_REGISTRATION, Protocol.CODE_REQUEST_BASIC_FACILITIES_LIST);
        MyIOStream.oos.writeObject(protocol);

        return (Protocol) MyIOStream.ois.readObject();
    }

    public Protocol safetyAmenitiesListRequest() throws IOException, ClassNotFoundException {
        Protocol protocol = new Protocol(Protocol.TYPE_HOUSE_REGISTRATION, Protocol.CODE_REQUEST_SAFETY_FACILITIES_LIST);
        MyIOStream.oos.writeObject(protocol);

        return (Protocol) MyIOStream.ois.readObject();
    }

    public Protocol accessAmenitiesListRequest() throws IOException, ClassNotFoundException {
        Protocol protocol = new Protocol(Protocol.TYPE_HOUSE_REGISTRATION, Protocol.CODE_REQUEST_ACCESSIBILITY_FACILITIES_LIST);
        MyIOStream.oos.writeObject(protocol);

        return (Protocol) MyIOStream.ois.readObject();
    }
}
