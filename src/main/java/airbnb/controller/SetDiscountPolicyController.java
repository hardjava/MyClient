package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;

public class SetDiscountPolicyController {
    public Protocol houseListRequest(UserDTO userDTO) throws IOException, ClassNotFoundException {
        Protocol protocol = new Protocol(Protocol.TYPE_SET_DISCOUNT_POLICY, Protocol.CODE_REQUEST_MY_HOUSE_LIST, userDTO);
        MyIOStream.oos.writeObject(protocol);
        //
        return (Protocol) MyIOStream.ois.readObject();
    }
}
