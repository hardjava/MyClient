package airbnb.persistence.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
public class ReviewDTO implements Serializable {
    private int reviewId, reservationId, star;
    private String review;

    public ReviewDTO(int reservationId, int star, String review) {
        this.reservationId = reservationId;
        this.star = star;
        this.review = review;
    }
}
