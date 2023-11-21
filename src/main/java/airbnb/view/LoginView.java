package airbnb.view;

import airbnb.controller.LoginController;
import airbnb.network.Protocol;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;
import java.util.Scanner;

public class LoginView {
    Scanner sc = new Scanner(System.in);

    public void showView() throws IOException, ClassNotFoundException {
        System.out.println("\n\t\t<Login>");
        System.out.print("\t\tID : ");
        String id = sc.next();
        System.out.print("\t\tPassword : ");
        String password = sc.next();

        LoginController loginController = new LoginController();
        Protocol protocol = loginController.loginRequest(id, password);

        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_LOGIN_ACCEPT:
                UserDTO userDTO = (UserDTO) protocol.getObject();
                // Role 타입에 맞는 View 띄워주기
                System.out.println(userDTO.getUserName());
                switch (userDTO.getRole()) {
                    case GUEST:
                        GuestView guestView = new GuestView();
                        break;
                    case HOST:
                        HostView hostView = new HostView();
                        break;
                    case ADMIN:
                        AdminView adminView = new AdminView();
                        break;
                }
                break;
            case Protocol.CODE_LOGIN_FAIL:
                //Error 타입일 경우 예외처리
                System.out.println(protocol.getObject());
                break;
        }
    }
}
