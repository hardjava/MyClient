package airbnb.view;

import java.io.IOException;
import java.util.Scanner;

public class StartView {
    Scanner sc = new Scanner(System.in);

    public void showView() throws IOException, ClassNotFoundException {
        for (; ; ) {
            int enter = getCommand();
            switch (enter) {
                case 1:
                    LoginView loginView = new LoginView(); // 응답 받으면 로그인 뷰 실행
                    loginView.showView();
                    break;
                case 2:
                    SignView signView = new SignView();
                    signView.showView();
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
