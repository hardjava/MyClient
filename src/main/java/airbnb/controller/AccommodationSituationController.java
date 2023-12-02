package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.HouseDTO;

import java.io.IOException;

public class AccommodationSituationController {
    public Protocol listRequest() throws IOException, ClassNotFoundException {
        Protocol protocol = new Protocol(Protocol.TYPE_MONTHLY_RESERVATION_STATUS_FOR_ACCOMMODATION, Protocol.CODE_REQUEST_ACCOMMODATION_LIST);
        MyIOStream.oos.writeObject(protocol);

        return (Protocol) MyIOStream.ois.readObject();
    }

    public Protocol monthlyReservationRequest(int houseId) throws IOException, ClassNotFoundException {
        HouseDTO houseDTO = new HouseDTO(houseId);
        Protocol protocol = new Protocol(Protocol.TYPE_MONTHLY_RESERVATION_STATUS_FOR_ACCOMMODATION, Protocol.CODE_REQUEST_CALCULATE, houseDTO);
        MyIOStream.oos.writeObject(protocol);

        return (Protocol) MyIOStream.ois.readObject();
    }

    public Protocol afterStayReservationRequest(HouseDTO houseDTO) throws IOException, ClassNotFoundException {
        Protocol protocol = new Protocol(Protocol.TYPE_MONTHLY_RESERVATION_STATUS_FOR_ACCOMMODATION, Protocol.CODE_REQUEST_COMPLETED_RESERVATION, houseDTO);
        MyIOStream.oos.writeObject(protocol);

        return (Protocol) MyIOStream.ois.readObject();
    }
}
