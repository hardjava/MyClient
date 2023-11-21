package airbnb.controller;

import airbnb.network.Protocol;
import airbnb.persistence.dto.LoginDTO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LoginHandler {

    public Protocol loginRequest(String id, String password) throws IOException, ClassNotFoundException { // 로그인 시도
        LoginDTO loginDTO = new LoginDTO(id, password);
        Protocol protocol = new Protocol(Protocol.TYPE_LOGIN, Protocol.CODE_LOGIN_REQUEST, loginDTO);
        LoginController loginController = new LoginController();
        return loginController.login(protocol);
    }
}
