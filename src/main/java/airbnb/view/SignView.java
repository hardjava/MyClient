package airbnb.view;

import airbnb.controller.SignController;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.network.RoleType;

import java.io.IOException;
import java.text.ParseException;

public class SignView {


    public void showView() throws IOException, ClassNotFoundException, ParseException {
        System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
        System.out.format("弛                              <Sign Up>                                    弛%n");
        System.out.format("                                                                             %n");
        System.out.print("\t\t\t\t1. Host 2. Guest : ");
        int roleNum = MyIOStream.sc.nextInt();
        String roleType;
        if (roleNum == 1)
            roleType = "HOST";
        else
            roleType = "GUEST";

        System.out.print("\t\t\t\tEnter New ID : ");
        String newID = MyIOStream.sc.next();
        System.out.print("\t\t\t\tEnter New Passwd : ");
        String newPwd = MyIOStream.sc.next();
        System.out.print("\t\t\t\tEnter Name : ");
        String newName = MyIOStream.sc.next();
        System.out.print("\t\t\t\tEnter Birthday (YYYYMMDD) : ");
        String newBirthDay = MyIOStream.sc.next();
        System.out.print("\t\t\t\tEnter User PhoneNumber : ");
        String newPhoneNumber = MyIOStream.sc.next();

        SignController signController = new SignController();
        Protocol protocol = signController.signRequest(newName, newPhoneNumber, newID, newPwd, newBirthDay, RoleType.valueOf(roleType));

        System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");

        switch (protocol.getProtocolCode()) {
            case Protocol.CODE_SUCCESS:
                System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                System.out.println("弛     Sueccess To Sign up!     弛");
                System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
                break;
            case Protocol.CODE_ERROR:
                System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                System.out.format("          Error: %s              \n", protocol.getObject());
                System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
                break;
        }
//        System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");
    }
}
