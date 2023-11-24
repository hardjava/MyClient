package airbnb.view;

import airbnb.controller.SignController;
import airbnb.network.Protocol;
import airbnb.network.RoleType;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class SignView {

    Scanner sc = new Scanner(System.in);

    public void showView() throws IOException, ClassNotFoundException, ParseException {
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("��                              <Sign Up>                                    ��%n");
        System.out.format("                                                                             %n");
        System.out.print("\t\t\t\t1. Host 2. Guest : ");
        int roleNum = sc.nextInt();
        String roleType;
        if (roleNum == 1)
            roleType = "HOST";
        else
            roleType = "GUEST";

        System.out.print("\t\t\t\tEnter New ID : ");
        String newID = sc.next();
        System.out.print("\t\t\t\tEnter New Passwd : ");
        String newPwd = sc.next();
        System.out.print("\t\t\t\tEnter Name : ");
        String newName = sc.next();
        System.out.print("\t\t\t\tEnter Birthday (YYYYMMDD) : ");
        String newBirthDay = sc.next();
        System.out.print("\t\t\t\tEnter User PhoneNumber : ");
        String newPhoneNumber = sc.next();

        SignController signController = new SignController();
        Protocol protocol = signController.signRequest(newName, newPhoneNumber, newID, newPwd, newBirthDay, RoleType.valueOf(roleType));

        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");

        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SUCCESS:
                System.out.println("����������������������������������������������������������������");
                System.out.println("��     Sueccess To Sign up!     ��");
                System.out.println("����������������������������������������������������������������");
                break;
            case Protocol.CODE_ERROR:
                System.out.println("����������������������������������������������������������������");
                System.out.format("          Error: %s              \n", protocol.getObject());
                System.out.println("����������������������������������������������������������������");
                break;
        }
//        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
    }
}
