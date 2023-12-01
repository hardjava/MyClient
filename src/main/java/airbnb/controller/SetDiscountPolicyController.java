package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.DiscountPolicyDTO;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;
import java.sql.Date;

public class SetDiscountPolicyController {
    public Protocol houseListRequest(UserDTO userDTO) throws IOException, ClassNotFoundException {
        Protocol protocol = new Protocol(Protocol.TYPE_SET_DISCOUNT_POLICY, Protocol.CODE_REQUEST_MY_HOUSE_LIST, userDTO);
        MyIOStream.oos.writeObject(protocol);
        //
        return (Protocol) MyIOStream.ois.readObject();
    }

    public Protocol setDiscountPolicyRequest(int houseId, int discountDay, int discount_amount, int discount_rate, String discountStart, String discountEnd) throws IOException, ClassNotFoundException {
        Date start = Date.valueOf(discountStart);
        Date end = Date.valueOf(discountEnd);
        DiscountPolicyDTO discountPolicyDTO = new DiscountPolicyDTO(houseId, discountDay, discount_amount, discount_rate, start, end);
        Protocol protocol = new Protocol(Protocol.TYPE_SET_DISCOUNT_POLICY, Protocol.CODE_SEND_DISCOUNT_POLICY_ON_CONSECUTIVE_NIGHTS, discountPolicyDTO);
        MyIOStream.oos.writeObject(protocol);
        //
        return (Protocol) MyIOStream.ois.readObject();
    }
}
