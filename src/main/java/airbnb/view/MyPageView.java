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
    int firstColWidth = 30; // ù ��° ���� ��
    int secondColWidth = 50; // �� ��° ���� ��
    String leftAlignFormat = "�� %-" + firstColWidth + "s �� %-" + secondColWidth + "s ��%n";

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
                    // ��: ���� �Ϸ� ��� ��ȸ
                    showCompletedStays();
                    break;
                case 2:
                    // ��: ���� ��ȸ
                    showReservations();
                    break;
                case 3:
                    // ��: ����� ���� ����
                    modifyUserInfo();
                    break;
                default:
                    System.out.println("Invalid Command");
                    break;
            }
        }
    }

    private void showCompletedStays() throws IOException, ClassNotFoundException {
        int firstColWidth1 = 20; // ù ��° ���� ��
        int secondColWidth1 = 50; // �� ��° ���� ��
        String leftAlignFormat1 = "�� %-" + firstColWidth1 + "s �� %-" + secondColWidth1 + "s ��%n";
        StayedHouseController stayedHouseController = new StayedHouseController();
        Protocol protocol = stayedHouseController.completedStayRequest(userDTO);
        if (protocol.getProtocolCode() == Protocol.CODE_ERROR) {
            System.out.println(protocol.getObject());
        } else {
            List<CompletedStayDTO> list = (List<CompletedStayDTO>) protocol.getObject();
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
            System.out.format("��                               Completed Stays                             ��%n");
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");

            if (list.isEmpty()) {
                System.out.format("�� No completed stays found                                                  ��%n");
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
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
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
        int firstColWidth1 = 20; // ù ��° ���� ��
        int secondColWidth1 = 50; // �� ��° ���� ��
        String leftAlignFormat1 = "�� %-" + firstColWidth1 + "s �� %-" + secondColWidth1 + "s ��%n";
        SearchGuestReservationController searchGuestReservationController = new SearchGuestReservationController();
        Protocol protocol = searchGuestReservationController.reservationStayRequest(userDTO);
        if (protocol.getProtocolCode() == Protocol.CODE_ERROR) {
            System.out.println(protocol.getObject());
        } else if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
            List<CompletedStayDTO> list = (List<CompletedStayDTO>) protocol.getObject();
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
            System.out.format("��                         Accommodation Reservation                         ��%n");
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");

            if (list.isEmpty()) {
                System.out.format("�� No reservations found                                                      ��%n");
            } else {
                for (int i = 0; i < list.size(); i++) {
                    CompletedStayDTO completedStayDTO = list.get(i);
                    System.out.format(leftAlignFormat1, (i + 1) + ". " + completedStayDTO.getHouseName(), "Check-in: " + completedStayDTO.getCheckIn());
                    System.out.format(leftAlignFormat1, "", "Check-out: " + completedStayDTO.getCheckOut());
                    System.out.format(leftAlignFormat1, "", "Cost: " + completedStayDTO.getCost());
                    System.out.format(leftAlignFormat1, "", "Status: " + completedStayDTO.getReservationStatus().name());
                }
            }

            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
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
        int labelWidth = 20; // ���� ��
        int textWidth = 50; // �ؽ�Ʈ�� ��
        String format = "| %-" + labelWidth + "s | %-" + textWidth + "s |%n";

        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("��                           Current User Information                        ��%n");
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        printFormatted("Name", userDTO.getUserName(), labelWidth, textWidth);
        printFormatted("Phone Number", userDTO.getUserPhone(), labelWidth, textWidth);
        printFormatted("Birthday", userDTO.getUserBirthday(), labelWidth, textWidth);
        printFormatted("Role", String.valueOf(userDTO.getRole()), labelWidth, textWidth);
        printFormatted("ID", userDTO.getLoginId(), labelWidth, textWidth);
        printFormatted("Password", userDTO.getLoginPwd(), labelWidth, textWidth);
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("��                            Modify User Information                        ��%n");
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("�� 1. Modify Name                                                            ��%n");
        System.out.format("�� 2. Modify Phone Number                                                    ��%n");
        System.out.format("�� 3. Modify Birthday                                                        ��%n");
        System.out.format("�� 4. Exit                                                                   ��%n");
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
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
        // ��� �Է��� ���� �޼ҵ�
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("��                                  <My Page>                                ��%n");
        printUserInfo();
        System.out.format("�� 1. View Completed Stays                                                   ��%n");
        System.out.format("�� 2. View Reservations                                                      ��%n");
        System.out.format("�� 3. Modify User Info                                                       ��%n");
        System.out.format("�� 4. Back                                                                   ��%n");
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.print("Enter Command: ");
        return MyIOStream.sc.nextInt();
    }

    private void printUserInfo() {
        int labelWidth = 20; // ���� ��
        int textWidth = 50; // �ؽ�Ʈ�� ��
        String format = "| %-" + labelWidth + "s | %-" + textWidth + "s |%n";

        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        printFormatted("Name", userDTO.getUserName(), labelWidth, textWidth);
        printFormatted("Role", String.valueOf(userDTO.getRole()), labelWidth, textWidth);
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
    }

    private static void printFormatted(String label, String text, int labelWidth, int textWidth) {
        // �󺧰� �ؽ�Ʈ�� �޾� ���˿� �°� ����ϴ� �޼ҵ�
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        System.out.printf("�� %-" + labelWidth + "s �� ", label);

        for (String word : words) {
            if (line.length() + word.length() > textWidth) {
                System.out.printf("%-" + textWidth + "s ��%n�� %" + labelWidth + "s �� ", line.toString(), "");
                line.setLength(0);
            }
            line.append(word).append(" ");
        }

        System.out.printf("%-" + textWidth + "s ��%n", line.toString());
    }
}
