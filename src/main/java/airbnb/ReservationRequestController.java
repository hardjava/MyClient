package airbnb;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.ReservationDTO;

import java.io.IOException;
import java.sql.Date;

public class ReservationRequestController {
    public Protocol reservationRequest(int houseId, int userId, int guestNum, String checkIn, String checkOut, int cost) throws IOException, ClassNotFoundException {
        Date checkIndate = Date.valueOf(checkIn);
        Date checkOutDate = Date.valueOf(checkOut);
        ReservationDTO reservationDTO = new ReservationDTO(houseId, userId, guestNum, checkIndate, checkOutDate, cost);
        Protocol protocol = new Protocol(Protocol.TYPE_REQUEST_RESERVATION, Protocol.CODE_SEND_RESERVATION_INFO, reservationDTO);
        MyIOStream.oos.writeObject(protocol);

        return (Protocol) MyIOStream.ois.readObject();
    }
}
