package airbnb.persistence.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder

public class ReviewDTO {
    private int reviewId, reservationId, star;
    private String review;

    public ReviewDTO(int reviewId, int reservationId, int star, String review) {
        this.reviewId = reviewId;
        this.reservationId = reservationId;
        this.star = star;
        this.review = review;
    }
}
