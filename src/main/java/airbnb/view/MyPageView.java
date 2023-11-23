package airbnb.view;

import airbnb.controller.ModifyUserInfoController;
import airbnb.controller.SearchGuestReservationController;
import airbnb.controller.SendReviewController;
import airbnb.controller.StayedHouseController;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.CompletedStayDTO;
import airbnb.persistence.dto.ReservationStayDTO;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;
import java.util.List;

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
                protocol = stayedHouseController.completedStayRequest(userDTO);
                if (protocol.getProtocolCode() == Protocol.CODE_ERROR) {
                    System.out.println(protocol.getObject());
                } else {
                    System.out.println("[Completed Stayed List]");
                    List<CompletedStayDTO> list = (List<CompletedStayDTO>) protocol.getObject();
                    int i = 0;
                    for (CompletedStayDTO completedStayDTO : list) {
                        System.out.printf("%d. %-30s %-12s %-12s\n", ++i, completedStayDTO.getHouseName(), completedStayDTO.getCheckIn(), completedStayDTO.getCheckOut() + "  " + completedStayDTO.getCost() + " " + (completedStayDTO.isHasReview() ? "O" : "X"));
                    }

                    System.out.print("\n1. Write a review, 2. Exit : ");
                    MyIOStream.sc.nextLine(); // 버퍼 비워
                    String str = MyIOStream.sc.nextLine();
                    if (!str.equals("2")) {
                        String[] arr = str.split(",");
                        SendReviewController sendReviewController = new SendReviewController();
                        if (Integer.parseInt(arr[1]) > 0 && Integer.parseInt(arr[1]) <= i) {
                            protocol = sendReviewController.sendReviewRequest(list.get(Integer.parseInt(arr[1]) - 1).getReservationId(), Integer.parseInt(arr[2]), arr[3]);

                            if (protocol.getProtocolCode() == Protocol.CODE_ERROR) {
                                System.out.println(protocol.getObject());
                            } else if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                                System.out.println("Success To Write Review");
                            }

                        } else {
                            System.out.println("Error..");
                        }
                    }
                }
                // 작업해야함
                break;

            case 2:
                SearchGuestReservationController searchGuestReservationController = new SearchGuestReservationController();
                protocol = searchGuestReservationController.reservationStayRequest();
                if (protocol.getProtocolCode() == Protocol.CODE_ERROR) {
                    System.out.println(protocol.getObject());
                } else {
                    List<ReservationStayDTO> list = (List<ReservationStayDTO>) protocol.getObject();
                    for (ReservationStayDTO reservationStayDTO : list) {
                        System.out.println(reservationStayDTO);
                    }
                }

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
        System.out.print("\t\t(4) : Back");
        System.out.print("\nPlease enter the number : ");
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
