package airbnb.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class ReviewDTO implements Serializable {
    private int reviewId, reservationId, star;
    private String review;
    private int houseId;

    public ReviewDTO() {
    }

    public ReviewDTO(int reservationId, int star, String review) {
        this.reservationId = reservationId;
        this.star = star;
        this.review = review;
    }

    public ReviewDTO(int reservationId, int star, String review, int houseId) {
        this.reservationId = reservationId;
        this.star = star;
        this.review = review;
        this.houseId = houseId;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < star; i++) {
            str.append("*");
        }

        return String.format("Star : %s", str);
    }
}
