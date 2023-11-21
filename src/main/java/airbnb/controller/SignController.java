package airbnb.controller;

import airbnb.network.MyObjectIOStream;
import airbnb.network.Protocol;
import airbnb.network.RoleType;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;

public class SignController {
    public Protocol signRequest(String userName, String userPhoneNumber, String loginId, String loginPwd, String userBirthday, RoleType role) throws IOException, ClassNotFoundException {
        UserDTO userDTO = new UserDTO(userName, userPhoneNumber, loginId, role, loginPwd, userBirthday);
        Protocol protocol = new Protocol(Protocol.TYPE_SIGN_UP, Protocol.CODE_SEND_SIGN_UP_INFO, userDTO);
        MyObjectIOStream.oos.writeObject(protocol);
//         대기 wait
        return (Protocol) MyObjectIOStream.ois.readObject();
    }
}
