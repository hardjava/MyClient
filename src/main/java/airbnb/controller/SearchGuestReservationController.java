package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.network.Status;
import airbnb.persistence.dto.ReservationDTO;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;

public class SearchGuestReservationController {
    public Protocol reservationStayRequest(UserDTO userDTO) throws IOException, ClassNotFoundException {
        Protocol protocol = new Protocol(Protocol.TYPE_SEARCH_RESERVATION, Protocol.CODE_MY_RESERVATION_REQUEST, userDTO);
        MyIOStream.oos.writeObject(protocol);
        //
        return (Protocol) MyIOStream.ois.readObject();
    }

    public Protocol cancelReservationRequest(int reservationId, Status reservationStatus) throws IOException, ClassNotFoundException {
        ReservationDTO reservationDTO = new ReservationDTO(reservationId, reservationStatus);
        Protocol protocol = new Protocol(Protocol.TYPE_SEARCH_RESERVATION, Protocol.CODE_REQUEST_RESERVATION_CANCELLATION, reservationDTO);
        MyIOStream.oos.writeObject(protocol);
        //
        return (Protocol) MyIOStream.ois.readObject();
    }
}
