package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.network.Status;
import airbnb.persistence.dto.ReservationDTO;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;

public class ReservationAllowOrRefuseController {

    public Protocol listRequest(UserDTO userDTO) throws IOException, ClassNotFoundException {
        Protocol protocol = new Protocol(Protocol.TYPE_RESERVATION_ALLOW_OR_REFUSE, Protocol.CODE_REQUEST_WAITING_FOR_RESERVATION_APPROVAL, userDTO);
        MyIOStream.oos.writeObject(protocol);

        return (Protocol) MyIOStream.ois.readObject();
    }

    public Protocol statusChangeRequest(int reservationId, Status reservationStatus) throws IOException, ClassNotFoundException {
        ReservationDTO reservationDTO = new ReservationDTO(reservationId, reservationStatus);
        Protocol protocol = new Protocol(Protocol.TYPE_RESERVATION_ALLOW_OR_REFUSE, Protocol.CODE_REQUEST_APPROVE_OR_REFUSE_RESERVATION, reservationDTO);
        MyIOStream.oos.writeObject(protocol);

        return (Protocol) MyIOStream.ois.readObject();
    }
}
