package airbnb.view;

import airbnb.controller.ModifyUserInfoController;
import airbnb.controller.SearchGuestReservationController;
import airbnb.controller.StayedHouseController;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;

public class MyPageView {
    UserDTO userDTO;

    public MyPageView(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public void showView() throws IOException, ClassNotFoundException {
        Protocol protocol;
        int menu = selectMenu();
        switch (menu) {
            case 1:
                StayedHouseController stayedHouseController = new StayedHouseController();
                protocol = stayedHouseController.completedStayRequest();
                if (protocol.getProtocolCode() == Protocol.CODE_ERROR) {
                    System.out.println(protocol.getObject());
                }else {

                }
                // 작업해야함
                break;

            case 2:
                SearchGuestReservationController searchGuestReservationController = new SearchGuestReservationController();
                protocol = searchGuestReservationController.reservationStayRequest();
                break;

            case 3:
                int enter = printInfo();
                ModifyUserInfoController modifyUserInfoController = new ModifyUserInfoController();
                switch (enter) {
                    case 1:
                        System.out.print("Enter the new name : ");
                        String newName = MyIOStream.sc.next();
                        protocol = modifyUserInfoController.modifyNameRequest(userDTO.getUserId(), newName);
                        if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                            System.out.println("Success!");
                            userDTO.setUserName(newName);
                        } else {
                            System.out.println(protocol.getObject());
                        }
                        break;
                    case 2:
                        System.out.print("Enter the new phoneNumber : ");
                        String newPhoneNumber = MyIOStream.sc.next();
                        protocol = modifyUserInfoController.modifyPhoneNumberRequest(userDTO.getUserId(), newPhoneNumber);
                        if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                            System.out.println("Success!");
                            userDTO.setUserPhone(newPhoneNumber);
                        } else {
                            System.out.println(protocol.getObject());
                        }
                        break;
                    case 3:
                        System.out.print("Enter the new Birthday : ");
                        String newBirthday = MyIOStream.sc.next();
                        protocol = modifyUserInfoController.modifyBirthDayRequest(userDTO.getUserId(), newBirthday);
                        if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                            System.out.println("Success!");
                            userDTO.setUserBirthday(newBirthday);
                        } else {
                            System.out.println(protocol.getObject());
                        }
                        break;
                    case 4:
                        System.out.println("Exit Change Info...");
                        break;
                    default:
                        System.out.println("Unknown input");
                        break;
                }
                break;
            default:
                System.out.println("The wrong approach");
        }
    }

    private int selectMenu() {
        System.out.println("\t\t<MyPage Menu>");
        System.out.println("\t\t(1) : View Completed Stays");
        System.out.println("\t\t(2) : View accommodation reservations");
        System.out.println("\t\t(3) : Edit Personal Information");
        System.out.println("\t\t(4) : Back");
        return MyIOStream.sc.nextInt();
    }

    private int printInfo() {
        System.out.println("\t\t<Change User Info>");
        System.out.println("\t\tName : " + userDTO.getUserName());
        System.out.println("\t\tPhoneNumber : " + userDTO.getUserPhone());
        System.out.println("\t\tBirthDay : " + userDTO.getUserBirthday());
        System.out.println("\t\tRole : " + userDTO.getRole());
        System.out.println("\t\tId : " + userDTO.getLoginId());
        System.out.println("\t\tPassword : " + userDTO.getLoginPwd());
        System.out.println("\nPlease enter the number you want to change");
        System.out.print("(1) Name, (2) PhoneNumber, (3) BirthDay, (4) Exit : ");
        return MyIOStream.sc.nextInt();
    }
}
