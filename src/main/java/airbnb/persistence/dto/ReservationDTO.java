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
    private int reservationId, houseId, userId;
    private Status reservationStatus;
    private int guestNum;
    private Date reservationDate, checkIn, checkOut;
    private int cost;


    public ReservationDTO(int houseId, int userId, int guestNum, Date checkIn, Date checkOut, int cost) {
        this.houseId = houseId;
        this.userId = userId;
        this.guestNum = guestNum;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.cost = cost;
    }

    public ReservationDTO(int reservationId, Status reservationStatus) {
        this.reservationId = reservationId;
        this.reservationStatus = reservationStatus;
    }

    public ReservationDTO(int reservationId, int guestNum, Date checkIn, Date checkOut, int cost) {
        this.reservationId = reservationId;
        this.guestNum = guestNum;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.cost = cost;
    }
}
