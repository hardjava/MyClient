package airbnb.controller;

import airbnb.network.MyObjectIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.LoginDTO;

import java.io.IOException;

public class LoginController {
    public Protocol loginRequest(String id, String password) throws IOException, ClassNotFoundException {
        LoginDTO loginDTO = new LoginDTO(id, password);
        Protocol protocol = new Protocol(Protocol.TYPE_LOGIN, Protocol.CODE_LOGIN_REQUEST, loginDTO);
        MyObjectIOStream.oos.writeObject(protocol);
        // 대기 wait
        return (Protocol) MyObjectIOStream.ois.readObject();
    }
}