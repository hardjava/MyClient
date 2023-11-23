package airbnb.persistence.dto;

import lombok.*;
import airbnb.network.Status;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor

public class ReservationDTO {
    private int reservationId, guestNum, houseId, cost;
    private Status reservationStatus;
    private LocalDateTime reservationDate, checkIn, checkOut;
    private String userId, userPwd;

    public ReservationDTO(int guestNum, int houseId, int cost, Status reservationStatus, LocalDateTime reservationDate, LocalDateTime checkIn, LocalDateTime checkOut, String userId, String userPwd) {
        this.guestNum = guestNum;
        this.houseId = houseId;
        this.reservationStatus = reservationStatus;
        this.reservationDate = reservationDate;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.userId = userId;
        this.userPwd = userPwd;
        this.cost = cost;
    }

    public ReservationDTO(int reservationId, Status reservationStatus) {
        this.reservationId = reservationId;
        this.reservationStatus = reservationStatus;
    }
}
