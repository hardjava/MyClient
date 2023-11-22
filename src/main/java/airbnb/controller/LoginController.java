package airbnb.controller;

import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.LoginDTO;

import java.io.IOException;

public class LoginController {
    public Protocol loginRequest(String id, String password) throws IOException, ClassNotFoundException {
        LoginDTO loginDTO = new LoginDTO(id, password);
        Protocol protocol = new Protocol(Protocol.TYPE_LOGIN, Protocol.CODE_LOGIN_REQUEST, loginDTO);
        MyIOStream.oos.writeObject(protocol);
        // 대기 wait
        return (Protocol) MyIOStream.ois.readObject();
    }
}