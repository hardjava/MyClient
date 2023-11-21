package airbnb.controller;

import airbnb.network.MyObjectIOStream;
import airbnb.network.Protocol;

import java.io.IOException;

public class LoginController {
    public Protocol login(Protocol protocol) throws IOException, ClassNotFoundException {
        MyObjectIOStream.oos.writeObject(protocol);
        // 대기 wait
        return (Protocol) MyObjectIOStream.ois.readObject();
    }
}
