package airbnb.persistence.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@Builder
public class HouseAndReservationDTO implements Serializable {
    private String houseName;
    int reservationId, guestNum;
    Date checkIn, checkOut;
    int cost, userId;
    String loginId, userName, userPhone;

    public HouseAndReservationDTO(String houseName, int reservationId, int guestNum, Date checkIn, Date checkOut, int cost, int userId, String loginId, String userName, String userPhone) {
        this.houseName = houseName;
        this.reservationId = reservationId;
        this.guestNum = guestNum;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.cost = cost;
        this.userId = userId;
        this.loginId = loginId;
        this.userName = userName;
        this.userPhone = userPhone;
    }

    public String toString() {
        return String.format("%-30s%-15s%-15s%-15d%-15s%-15s%-30s%-30d", houseName, checkIn,
                checkOut, guestNum, userName,
                userPhone, loginId, cost);
    }
}
