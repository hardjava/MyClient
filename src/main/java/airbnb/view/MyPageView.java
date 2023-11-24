package airbnb.view;

import airbnb.controller.ModifyUserInfoController;
import airbnb.controller.SearchGuestReservationController;
import airbnb.controller.SendReviewController;
import airbnb.controller.StayedHouseController;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.CompletedStayDTO;
import airbnb.persistence.dto.UserDTO;

import java.io.IOException;
import java.util.List;

public class MyPageView {
    UserDTO userDTO;
    int firstColWidth = 30; // 羅 廓簞 翮曖 ァ
    int secondColWidth = 50; // 舒 廓簞 翮曖 ァ
    String leftAlignFormat = "弛 %-" + firstColWidth + "s 弛 %-" + secondColWidth + "s 弛%n";

    public MyPageView(UserDTO userDTO) {
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
                    // 蕨: 熨夢 諫猿 跡煙 褻��
                    showCompletedStays();
                    break;
                case 2:
                    // 蕨: 蕨擒 褻��
                    showReservations();
                    break;
                case 3:
                    // 蕨: 餌辨濠 薑爾 熱薑
                    modifyUserInfo();
                    break;
                default:
                    System.out.println("Invalid Command");
                    break;
            }
        }
    }

    private void showCompletedStays() throws IOException, ClassNotFoundException {
        int firstColWidth1 = 20; // 羅 廓簞 翮曖 ァ
        int secondColWidth1 = 50; // 舒 廓簞 翮曖 ァ
        String leftAlignFormat1 = "弛 %-" + firstColWidth1 + "s 弛 %-" + secondColWidth1 + "s 弛%n";
        StayedHouseController stayedHouseController = new StayedHouseController();
        Protocol protocol = stayedHouseController.completedStayRequest(userDTO);
        if (protocol.getProtocolCode() == Protocol.CODE_ERROR) {
            System.out.println(protocol.getObject());
        } else {
            List<CompletedStayDTO> list = (List<CompletedStayDTO>) protocol.getObject();
            System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
            System.out.format("弛                               Completed Stays                             弛%n");
            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");

            if (list.isEmpty()) {
                System.out.format("弛 No completed stays found                                                  弛%n");
            } else {
                for (int i = 0; i < list.size(); i++) {
                    CompletedStayDTO completedStayDTO = list.get(i);
                    String reviewStatus = completedStayDTO.isHasReview() ? "Reviewed" : "Not Reviewed";
                    System.out.format(leftAlignFormat1, (i + 1) + ". " + completedStayDTO.getHouseName(), "Check-in: " + completedStayDTO.getCheckIn());
                    System.out.format(leftAlignFormat1, "", "Check-out: " + completedStayDTO.getCheckOut());
                    System.out.format(leftAlignFormat1, "", "Cost: " + completedStayDTO.getCost());
                    System.out.format(leftAlignFormat1, "", reviewStatus);
                }
            }
            System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");
            handleReviewOption(list);
        }
    }

    private void handleReviewOption(List<CompletedStayDTO> list) {
        if (!list.isEmpty()) {
            System.out.print("Write a review?\n(1) Yes, (2) No: ");
            int choice = MyIOStream.sc.nextInt();
            MyIOStream.sc.nextLine(); // Clear buffer
            if (choice == 1) {
                System.out.print("Enter House ID, Star Rating, Review Text (Format: houseId,star,review): ");
                String input = MyIOStream.sc.nextLine();
                String[] arr = input.split(",");
                try {
                    int houseId = Integer.parseInt(arr[0]);
                    int star = Integer.parseInt(arr[1]);
                    String reviewText = arr[2];
                    if (houseId > 0 && houseId <= list.size()) {
                        SendReviewController sendReviewController = new SendReviewController();
                        Protocol protocol = sendReviewController.sendReviewRequest(list.get(houseId - 1).getReservationId(), star, reviewText);
                        if (protocol.getProtocolCode() == Protocol.CODE_ERROR) {
                            System.out.println(protocol.getObject());
                        } else if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                            System.out.println("Success To Write Review");
                        }
                    } else {
                        System.out.println("Error: Invalid House ID");
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException | IOException |
                         ClassNotFoundException e) {
                    System.out.println("Error: Invalid Input Format");
                }
            }
        }
    }


    private void showReservations() throws IOException, ClassNotFoundException {
        int firstColWidth1 = 20; // 羅 廓簞 翮曖 ァ
        int secondColWidth1 = 50; // 舒 廓簞 翮曖 ァ
        String leftAlignFormat1 = "弛 %-" + firstColWidth1 + "s 弛 %-" + secondColWidth1 + "s 弛%n";
        SearchGuestReservationController searchGuestReservationController = new SearchGuestReservationController();
        Protocol protocol = searchGuestReservationController.reservationStayRequest(userDTO);
        if (protocol.getProtocolCode() == Protocol.CODE_ERROR) {
            System.out.println(protocol.getObject());
        } else if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
            List<CompletedStayDTO> list = (List<CompletedStayDTO>) protocol.getObject();
            System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
            System.out.format("弛                         Accommodation Reservation                         弛%n");
            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");

            if (list.isEmpty()) {
                System.out.format("弛 No reservations found                                                      弛%n");
            } else {
                for (int i = 0; i < list.size(); i++) {
                    CompletedStayDTO completedStayDTO = list.get(i);
                    System.out.format(leftAlignFormat1, (i + 1) + ". " + completedStayDTO.getHouseName(), "Check-in: " + completedStayDTO.getCheckIn());
                    System.out.format(leftAlignFormat1, "", "Check-out: " + completedStayDTO.getCheckOut());
                    System.out.format(leftAlignFormat1, "", "Cost: " + completedStayDTO.getCost());
                    System.out.format(leftAlignFormat1, "", "Status: " + completedStayDTO.getReservationStatus().name());
                }
            }

            System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");
            handleReservationCancellation(list, searchGuestReservationController);
        }
    }

    private void handleReservationCancellation(List<CompletedStayDTO> list, SearchGuestReservationController controller) throws IOException, ClassNotFoundException {
        if (!list.isEmpty()) {
            System.out.print("Cancel a reservation? (1) Yes, (2) No: ");
            int choice = MyIOStream.sc.nextInt();
            if (choice == 1) {
                System.out.print("Enter the number of the accommodation to cancel (exit: -1): ");
                int enter = MyIOStream.sc.nextInt();
                if (enter != -1 && enter > 0 && enter <= list.size()) {
                    Protocol protocol = controller.cancelReservationRequest(list.get(enter - 1).getReservationId(), list.get(enter - 1).getReservationStatus());
                    if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                        System.out.println("Reservation successfully cancelled.");
                    } else if (protocol.getProtocolCode() == Protocol.CODE_ERROR) {
                        System.out.println(protocol.getObject());
                    }
                } else if (enter != -1) {
                    System.out.println("Error: Invalid selection.");
                }
            }
        }
    }


    private void modifyUserInfo() throws IOException, ClassNotFoundException {
        ModifyUserInfoController modifyUserInfoController = new ModifyUserInfoController();
        int enter = displayUserInfoOptions();

        switch (enter) {
            case 1:
                modifyUserName(modifyUserInfoController);
                break;
            case 2:
                modifyUserPhoneNumber(modifyUserInfoController);
                break;
            case 3:
                modifyUserBirthday(modifyUserInfoController);
                break;
            case 4:
                System.out.println("Exiting User Info Modification...");
                break;
            default:
                System.out.println("Unknown input");
                break;
        }
    }

    private int displayUserInfoOptions() {
        int labelWidth = 20; // 塭漣曖 ァ
        int textWidth = 50; // 臢蝶お曖 ァ
        String format = "| %-" + labelWidth + "s | %-" + textWidth + "s |%n";

        System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
        System.out.format("弛                           Current User Information                        弛%n");
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        printFormatted("Name", userDTO.getUserName(), labelWidth, textWidth);
        printFormatted("Phone Number", userDTO.getUserPhone(), labelWidth, textWidth);
        printFormatted("Birthday", userDTO.getUserBirthday(), labelWidth, textWidth);
        printFormatted("Role", String.valueOf(userDTO.getRole()), labelWidth, textWidth);
        printFormatted("ID", userDTO.getLoginId(), labelWidth, textWidth);
        printFormatted("Password", userDTO.getLoginPwd(), labelWidth, textWidth);
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        System.out.format("弛                            Modify User Information                        弛%n");
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        System.out.format("弛 1. Modify Name                                                            弛%n");
        System.out.format("弛 2. Modify Phone Number                                                    弛%n");
        System.out.format("弛 3. Modify Birthday                                                        弛%n");
        System.out.format("弛 4. Exit                                                                   弛%n");
        System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");
        System.out.print("Choose an option: ");
        return MyIOStream.sc.nextInt();
    }


    private void modifyUserName(ModifyUserInfoController controller) throws IOException, ClassNotFoundException {
        System.out.print("Enter the new name: ");
        String newName = MyIOStream.sc.next();
        Protocol protocol = controller.modifyNameRequest(userDTO.getUserId(), newName);
        if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
            System.out.println("Name successfully updated.");
            userDTO.setUserName(newName);
        } else {
            System.out.println(protocol.getObject());
        }
    }

    private void modifyUserPhoneNumber(ModifyUserInfoController controller) throws IOException, ClassNotFoundException {
        System.out.print("Enter the new phone number: ");
        String newPhoneNumber = MyIOStream.sc.next();
        Protocol protocol = controller.modifyPhoneNumberRequest(userDTO.getUserId(), newPhoneNumber);
        if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
            System.out.println("Phone number successfully updated.");
            userDTO.setUserPhone(newPhoneNumber);
        } else {
            System.out.println(protocol.getObject());
        }
    }

    private void modifyUserBirthday(ModifyUserInfoController controller) throws IOException, ClassNotFoundException {
        System.out.print("Enter the new birthday (YYYY-MM-DD): ");
        String newBirthday = MyIOStream.sc.next();
        Protocol protocol = controller.modifyBirthDayRequest(userDTO.getUserId(), newBirthday);
        if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
            System.out.println("Birthday successfully updated.");
            userDTO.setUserBirthday(newBirthday);
        } else {
            System.out.println(protocol.getObject());
        }
    }


    private int getCommand() {
        // 貲滄 殮溘擊 嬪и 詭模萄
        System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
        System.out.format("弛                                  <My Page>                                弛%n");
        printUserInfo();
        System.out.format("弛 1. View Completed Stays                                                   弛%n");
        System.out.format("弛 2. View Reservations                                                      弛%n");
        System.out.format("弛 3. Modify User Info                                                       弛%n");
        System.out.format("弛 4. Back                                                                   弛%n");
        System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");
        System.out.print("Enter Command: ");
        return MyIOStream.sc.nextInt();
    }

    private void printUserInfo() {
        int labelWidth = 20; // 塭漣曖 ァ
        int textWidth = 50; // 臢蝶お曖 ァ
        String format = "| %-" + labelWidth + "s | %-" + textWidth + "s |%n";

        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        printFormatted("Name", userDTO.getUserName(), labelWidth, textWidth);
        printFormatted("Role", String.valueOf(userDTO.getRole()), labelWidth, textWidth);
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
    }

    private static void printFormatted(String label, String text, int labelWidth, int textWidth) {
        // 塭漣婁 臢蝶お蒂 嫡嬴 ん裝縑 蜃啪 轎溘ж朝 詭模萄
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        System.out.printf("弛 %-" + labelWidth + "s 弛 ", label);

        for (String word : words) {
            if (line.length() + word.length() > textWidth) {
                System.out.printf("%-" + textWidth + "s 弛%n弛 %" + labelWidth + "s 弛 ", line.toString(), "");
                line.setLength(0);
            }
            line.append(word).append(" ");
        }

        System.out.printf("%-" + textWidth + "s 弛%n", line.toString());
    }
}
