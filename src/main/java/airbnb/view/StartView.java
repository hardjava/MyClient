package airbnb.view;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class StartView {
    Scanner sc = new Scanner(System.in);

    public void showView() throws IOException, ClassNotFoundException, ParseException {
        for (; ; ) {
            int enter = getCommand();

            if (enter == 3) {
                break;
            }

            switch (enter) {
                case 1:
                    LoginView loginView = new LoginView(); // ���� ������ �α��� �� ����
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
        System.out.println("\t\t<Initial Menu>");
        System.out.println("\t\t1. Login"); // �α���
        System.out.println("\t\t2. Sign in"); // ȸ�� ����
        System.out.println("\t\t3. Terminate"); // ����
        System.out.print("enter : ");

        return sc.nextInt();
    }
}
