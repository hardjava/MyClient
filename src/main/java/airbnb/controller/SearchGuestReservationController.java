package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.network.Status;
import airbnb.persistence.dto.ReservationDTO;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;
import java.sql.Date;

public class SearchGuestReservationController {
    public Protocol reservationStayRequest(UserDTO userDTO) throws IOException, ClassNotFoundException {
        Protocol protocol = new Protocol(Protocol.TYPE_SEARCH_RESERVATION, Protocol.CODE_MY_RESERVATION_REQUEST, userDTO);
        MyIOStream.oos.writeObject(protocol);
        //
        return (Protocol) MyIOStream.ois.readObject();
    }

    public Protocol cancelReservationRequest(int reservationId, Status reservationStatus, String checkIn, String checkOut) throws IOException, ClassNotFoundException {
        Date checkInDate = Date.valueOf(checkIn);
        Date checkOutDate = Date.valueOf(checkOut);
        ReservationDTO reservationDTO = new ReservationDTO(reservationId, reservationStatus, checkInDate, checkOutDate);
        Protocol protocol = new Protocol(Protocol.TYPE_SEARCH_RESERVATION, Protocol.CODE_REQUEST_RESERVATION_CANCELLATION, reservationDTO);
        MyIOStream.oos.writeObject(protocol);
        //
        return (Protocol) MyIOStream.ois.readObject();
    }
}
