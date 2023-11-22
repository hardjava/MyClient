package airbnb.view;

import airbnb.controller.LoginController;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;

public class LoginView {

    public void showView() throws IOException, ClassNotFoundException {
        System.out.println("\n\t\t<Login>");
        System.out.print("\t\tID : ");
        String id = MyIOStream.sc.next();
        System.out.print("\t\tPassword : ");
        String password = MyIOStream.sc.next();

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
                        HostView hostView = new HostView();
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
                System.out.println(protocol.getObject());
                break;
        }
    }
}
