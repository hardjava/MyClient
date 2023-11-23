package airbnb.persistence.dto;

import lombok.*;
import airbnb.network.Status;

import java.io.Serializable;
import java.sql.Date;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor

public class ReservationDTO implements Serializable {
    private int reservationId;
    private Status reservationStatus;
    private int guestNum;
    private Date reservationDate, checkIn, checkOut;
    private int houseId, userId;
    private int cost;


    public ReservationDTO(int reservationId, Status reservationStatus) {
        this.reservationId = reservationId;
        this.reservationStatus = reservationStatus;
    }
}
