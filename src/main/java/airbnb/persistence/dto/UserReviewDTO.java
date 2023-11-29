package airbnb.persistence.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class UserReviewDTO implements Serializable {
    private int star, reservationId;
    private String review, userName;

    public String toString() {
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < star; i++) {
            stars.append("*");
        }
        return String.format("User Name: %s\nSTAR: %s\nREVIEW: %s", userName, stars.toString(), review);
    }
}
