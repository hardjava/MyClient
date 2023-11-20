package airbnb.view;

import airbnb.controller.Handler;
import airbnb.network.Protocol;

import java.io.IOException;
import java.util.Scanner;

public class StartView {
    Scanner sc = new Scanner(System.in);

    public void run(Handler handler, Protocol protocol) throws IOException, ClassNotFoundException {
        for (; ; ) {
            int enter = getCommand();
            switch (enter) {
                case Protocol.TYPE1_LOGIN_REQUEST:
                    //handler.sendLoginRequest(protocol); // �α��� ��û�� ����
                    //protocol = (Protocol) objectInputStream.readObject(); // �α��� ������ ����
                    //if (protocol.getProtocolType() == Protocol.TYPE2_LOGIN_RESPONSE) {
                    LoginView loginView = new LoginView(); // ���� ������ �α��� �� ����
                    loginView.showView(handler, protocol);
                    //}
                    break;
                case 2:
                    //     new SignHandler().run();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Please Enter (1) ~ (3)");
                    break;
            }
        }
    }

    private int getCommand() {
        System.out.println("1. Login"); // �α���
        System.out.println("2. Sign in"); // ȸ�� ����
        System.out.println("3. Terminate"); // ����
        System.out.print("enter : ");

        return sc.nextInt();
    }
}
