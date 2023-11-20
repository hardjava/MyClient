package airbnb.controller;

import airbnb.network.Protocol;
import airbnb.persistence.dto.LoginDTO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Handler {
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public Handler(ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) {
        this.objectOutputStream = objectOutputStream;
        this.objectInputStream = objectInputStream;
    }

    public void sendLoginRequest(Protocol protocol) throws IOException {
        protocol.setProtocolType(Protocol.TYPE1_LOGIN_REQUEST);
        protocol.setProtocolCode(Protocol.CODE_UNDEFINED);
        objectOutputStream.writeObject(protocol); // 로그인 요청을 보냄
    }

    public Protocol loginRequest(LoginDTO loginDTO, Protocol protocol) throws IOException, ClassNotFoundException { // 로그인 시도
        LoginController loginController = new LoginController();
        return loginController.login(objectOutputStream, objectInputStream, loginDTO, protocol);
    }
}
