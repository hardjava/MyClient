package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.HouseDTO;

import java.io.IOException;

public class RequestHouseRegistrationController {

    public Protocol listRequest() throws IOException, ClassNotFoundException {
        Protocol protocol = new Protocol(Protocol.TYPE_VIEW_ACCOMMODATION_REGISTRATION_LIST, Protocol.CODE_REQUEST_ACCOMMODATION_REGISTRATION_LIST);
        MyIOStream.oos.writeObject(protocol);

        return (Protocol) MyIOStream.ois.readObject();
    }

    public Protocol detailInfoRequest(int houseId) throws IOException, ClassNotFoundException {
        HouseDTO houseDTO = new HouseDTO(houseId);
        Protocol protocol = new Protocol(Protocol.TYPE_VIEW_ACCOMMODATION_REGISTRATION_LIST, Protocol.CODE_REQUEST_MORE_INFO, houseDTO);
        MyIOStream.oos.writeObject(protocol);

        return (Protocol) MyIOStream.ois.readObject();
    }

    public Protocol approvalRequest(int houseId) throws IOException, ClassNotFoundException {
        HouseDTO houseDTO = new HouseDTO(houseId);
        Protocol protocol = new Protocol(Protocol.TYPE_VIEW_ACCOMMODATION_REGISTRATION_LIST, Protocol.CODE_SEND_APPROVAL_FORMATION, houseDTO);
        MyIOStream.oos.writeObject(protocol);

        return (Protocol) MyIOStream.ois.readObject();
    }

    public Protocol rejectRequest(int houseId) throws IOException, ClassNotFoundException {
        HouseDTO houseDTO = new HouseDTO(houseId);
        Protocol protocol = new Protocol(Protocol.TYPE_VIEW_ACCOMMODATION_REGISTRATION_LIST, Protocol.CODE_SEND_REJECT_INFORMATION, houseDTO);
        MyIOStream.oos.writeObject(protocol);

        return (Protocol) MyIOStream.ois.readObject();
    }
}
