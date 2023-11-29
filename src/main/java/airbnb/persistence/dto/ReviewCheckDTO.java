package airbnb.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter

public class ReviewCheckDTO implements Serializable {
    private ReviewDTO reviewDTO = new ReviewDTO();
    private UserDTO userDTO = new UserDTO();
    private String houseName;

    public ReviewCheckDTO(int reviewId, int reservationId, int star, String review, int houseId, String userName, String loginId, String houseName) {
        reviewDTO.setReviewId(reviewId);
        reviewDTO.setReservationId(reservationId);
        reviewDTO.setStar(star);
        reviewDTO.setReview(review);
        reviewDTO.setHouseId(houseId);
        userDTO.setUserName(userName);
        userDTO.setLoginId(loginId);
        this.houseName = houseName;
    }

    public ReviewCheckDTO(ReviewDTO reviewDTO, String houseName) {
        this.reviewDTO = reviewDTO;
        this.houseName = houseName;
    }

    public String toString() {
        return String.format("House Name : %s\nGuestName : %s\n%s", houseName, userDTO.getUserName(), reviewDTO.toString());
    }
}
