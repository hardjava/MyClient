package airbnb.controller;

import airbnb.network.Protocol;
import airbnb.persistence.dto.LoginDTO;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LoginController {
    public Protocol login(ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream, LoginDTO loginDTO, Protocol protocol) throws IOException, ClassNotFoundException {
        protocol.setProtocolType(Protocol.TYPE1_LOGIN_REQUEST);
        protocol.setProtocolCode(Protocol.CODE_TYPE1_LOGIN);
        protocol.setObject(loginDTO);
        objectOutputStream.writeObject(protocol);
        // 대기 wait
        return  (Protocol) objectInputStream.readObject();
    }
}
