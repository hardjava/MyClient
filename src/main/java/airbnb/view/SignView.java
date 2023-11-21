package airbnb.view;

import airbnb.controller.SignController;
import airbnb.network.Protocol;
import airbnb.network.RoleType;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SignView {

    Scanner sc = new Scanner(System.in);

    public void showView() throws IOException, ClassNotFoundException, ParseException {
        System.out.print("1. Host 2. Guest : ");
        int roleNum = sc.nextInt();
        String roleType;
        if (roleNum == 1)
            roleType = "HOST";
        else
            roleType = "GUEST";

        System.out.print("Enter New ID : ");
        String newID = sc.next();
        System.out.print("Enter New Passwd : ");
        String newPwd = sc.next();
        System.out.print("Enter Name : ");
        String newName = sc.next();
        System.out.print("Enter Birthday (YYYYMMDD) : ");
        String newBirthDay = sc.next();
        System.out.print("Enter User PhoneNumber : ");
        String newPhoneNumber = sc.next();

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//        LocalDate localDate = LocalDate.parse(newBirthDay, formatter);

        SignController signController = new SignController();
        Protocol protocol = signController.signRequest(newName, newPhoneNumber, newID, newPwd, newBirthDay, RoleType.valueOf(roleType));

        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SIGN_UP_SUCCESS:
                System.out.println("성공 ! ");
                break;
            case Protocol.CODE_SIGN_UP_FAIL:
                System.out.println(protocol.getObject());
                break;
        }
    }
}