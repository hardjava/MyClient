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
}