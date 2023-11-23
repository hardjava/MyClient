package airbnb.persistence.dto;

import airbnb.network.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor

public class CompletedStayDTO implements Serializable {
    private int reservationId;
    private String houseName, checkIn, checkOut;
    private Status reservationStatus;
    private int cost;
    private boolean hasReview;

    public CompletedStayDTO(int reservationId, String houseName, String checkIn, String checkOut, Status reservationStatus, int cost) {
        this.reservationId = reservationId;
        this.houseName = houseName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.reservationStatus = reservationStatus;
        this.cost = cost;
    }
}