package airbnb.persistence.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class ReplyDTO implements Serializable {
    private int replyId, reservationId;
    private String loginId, userName, reply;

    public ReplyDTO(int reservationId, String loginId, String userName, String reply) {
        this.reservationId = reservationId;
        this.loginId = loginId;
        this.userName = userName;
        this.reply = reply;
    }

    public String toString() {
        return String.format("%50s", reply);
    }
}

