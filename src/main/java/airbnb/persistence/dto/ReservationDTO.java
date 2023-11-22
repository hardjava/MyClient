package airbnb.persistence.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import airbnb.network.Status;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Builder

public class ReservationDTO {
    private int reservationId, guest_num;
    private Status reservationStatus;
    private LocalDateTime reservationDate, checkIn, checkOut;
    private String userId, userPwd;

    public ReservationDTO(int reservationId, int guest_num, Status reservationStatus, LocalDateTime reservationDate, LocalDateTime checkIn, LocalDateTime checkOut, String userId, String userPwd) {
        this.reservationId = reservationId;
        this.guest_num = guest_num;
        this.reservationStatus = reservationStatus;
        this.reservationDate = reservationDate;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.userId = userId;
        this.userPwd = userPwd;
    }
}
