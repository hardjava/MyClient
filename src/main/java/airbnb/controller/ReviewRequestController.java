package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.ReplyDTO;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;

public class ReviewRequestController {
    public Protocol listRequest(UserDTO userDTO) throws IOException, ClassNotFoundException {
        Protocol protocol = new Protocol(Protocol.TYPE_REVIEW_MANAGEMENT, Protocol.CODE_REQUEST_NOT_REPLY_REVIEW, userDTO);
        MyIOStream.oos.writeObject(protocol);

        return (Protocol) MyIOStream.ois.readObject();
    }

    public Protocol writeReviewRequest(int reservationId, String loginId, String userName, String reply) throws IOException, ClassNotFoundException {
        ReplyDTO replyDTO = new ReplyDTO(reservationId, loginId, userName, reply);
        Protocol protocol = new Protocol(Protocol.TYPE_REVIEW_MANAGEMENT, Protocol.CODE_SEND_REPLYING_TO_REVIEW, replyDTO);
        MyIOStream.oos.writeObject(protocol);

        return (Protocol) MyIOStream.ois.readObject();
    }
}
