package airbnb.view;

import airbnb.controller.SignController;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.network.RoleType;

import java.io.IOException;
import java.text.ParseException;

public class SignView {


    public void showView() throws IOException, ClassNotFoundException, ParseException {
        System.out.format("                                                      ����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("                                                      ��                                 <Sign Up>                                 ��%n");
        System.out.format("                                                                                                                                   %n");
        System.out.println("                                                                            ������                          ������");
        System.out.println("                                                                            ��     (1) HOST   (2) GUEST     ��");
        System.out.print("                                                                                      Selection : ");
        int roleNum = MyIOStream.sc.nextInt();
        String roleType;
        System.out.println("                                                                            ����������������������������������������������������������������");

        if (roleNum == 1)
            roleType = "HOST";
        else
            roleType = "GUEST";

        System.out.print("                                                                                    Enter New ID : ");
        String newID = MyIOStream.sc.next();
        System.out.print("                                                                                    Enter New Passwd : ");
        String newPwd = MyIOStream.sc.next();
        System.out.print("                                                                                    Enter Name : ");
        MyIOStream.sc.nextLine();
        String newName = MyIOStream.sc.nextLine();
        System.out.print("                                                                                    Enter Birthday (YYYYMMDD) : ");
        String newBirthDay = MyIOStream.sc.nextLine();
        System.out.print("                                                                                    Enter User PhoneNumber : ");
        String newPhoneNumber = MyIOStream.sc.nextLine();

        SignController signController = new SignController();
        Protocol protocol = signController.signRequest(newName, newPhoneNumber, newID, newPwd, newBirthDay, RoleType.valueOf(roleType));

        System.out.format("                                                      ����������������������������������������������������������������������������������������������������������������������������������������������������������%n");

        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SUCCESS:
                System.out.println("                                                                             ����������������������������������������������������������������");
                System.out.println("                                                                             ��     Sueccess To Sign up!     ��");
                System.out.println("                                                                             ����������������������������������������������������������������");
                break;
            case Protocol.CODE_ERROR:
                System.out.println("                                                                             ����������������������������������������������������������������");
                System.out.format("                                                                                       Error: %s              \n", protocol.getObject());
                System.out.println("                                                                             ����������������������������������������������������������������");
                break;
        }
//        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
    }
}
