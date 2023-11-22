package airbnb.view;

import airbnb.network.MyIOStream;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;

public class GuestView {
    UserDTO userDTO;

    public GuestView(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public void showView() throws IOException, ClassNotFoundException {
        for (; ; ) {
            int command = getCommand();
            if (command == 4) {
                break;
            }

            switch (command) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    MyPageView myPageView = new MyPageView(userDTO);
                    myPageView.showView();
                    break;
                default:
                    System.out.println("Please Select (1) ~ (4)");
                    break;
            }
        }
    }

    private int getCommand() {
        System.out.println("\t\t<Guset Page>");
        printUserInfo();
        System.out.println("\t\t1. ���� �˻�");
        System.out.println("\t\t2. ���� ���");
        System.out.println("\t\t3. ���� ������");
        System.out.println("\t\t4. �α׾ƿ�");
        System.out.print("enter : ");
        return MyIOStream.sc.nextInt();
    }

    private void printUserInfo() {
        System.out.println("Name :  " + userDTO.getUserName() + "\nRole :  " + userDTO.getRole());
    }
}
