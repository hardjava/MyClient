package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.ReviewDTO;

import java.io.IOException;

public class SendReviewController {
    public Protocol sendReviewRequest(int reservationId, int star, String review) throws IOException, ClassNotFoundException {
        ReviewDTO reviewDTO = new ReviewDTO(reservationId, star, review);
        Protocol protocol = new Protocol(Protocol.TYPE_SEND_REVIEW, Protocol.CODE_SEND_REVIEW, reviewDTO);
        MyIOStream.oos.writeObject(protocol);
        //
        return (Protocol) MyIOStream.ois.readObject();
    }
}
