package airbnb.view;

import airbnb.controller.LoginController;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;

public class LoginView {

    public void showView() throws IOException, ClassNotFoundException {

        System.out.format("             ┌────────────────────────────────────────────────┐%n");
        System.out.format("             │       _      ____   _____ _____ _   _          │%n");
        System.out.format("             │      | |    / __ \\ / ____|_   _| \\ | |         │%n");
        System.out.format("             │      | |   | |  | | | |_ | | | | . ` |         │%n");
        System.out.format("             │      | |___| |__| | |__| |_| |_| |\\  |         │%n");
        System.out.format("             │      |______\\____/ \\_____|_____|_| \\_|         │%n");
        System.out.format("             └────────────────────────────────────────────────┘%n");

        System.out.format("┌───                                                                     ───┐%n");
        System.out.format("│                                 <Login>                                   │%n");
        System.out.format("                                                                             %n");
        System.out.print("\t\t\t\tID       : ");
        String id = MyIOStream.sc.next();
        System.out.print("\t\t\t\tPassword : ");
        String password = MyIOStream.sc.next();
        System.out.format("└───────────────────────────────────────────────────────────────────────────┘%n");

        LoginController loginController = new LoginController();
        Protocol protocol = loginController.loginRequest(id, password);

        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SUCCESS:
                UserDTO userDTO = (UserDTO) protocol.getObject();
                // Role 타입에 맞는 View 띄워주기
                switch (userDTO.getRole()) {
                    case GUEST:
                        GuestView guestView = new GuestView(userDTO);
                        guestView.showView();
                        break;
                    case HOST:
                        HostView hostView = new HostView(userDTO);
                        hostView.showView();
                        break;
                    case ADMIN:
                        AdminView adminView = new AdminView();
                        adminView.showView();
                        break;
                }
                break;
            case Protocol.CODE_ERROR:
                //Error 타입일 경우 예외처리
                System.out.format("  Error: %s%n", protocol.getObject());
                System.out.format("└───────────────────────────────────────────────────────────────────────────┘%n");
                break;
        }
    }
}
