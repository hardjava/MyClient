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
                    //handler.sendLoginRequest(protocol); // 로그인 요청을 보냄
                    //protocol = (Protocol) objectInputStream.readObject(); // 로그인 응답을 받음
                    //if (protocol.getProtocolType() == Protocol.TYPE2_LOGIN_RESPONSE) {
                    LoginView loginView = new LoginView(); // 응답 받으면 로그인 뷰 실행
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
        System.out.println("1. Login"); // 로그인
        System.out.println("2. Sign in"); // 회원 가입
        System.out.println("3. Terminate"); // 종료
        System.out.print("enter : ");

        return sc.nextInt();
    }
}
