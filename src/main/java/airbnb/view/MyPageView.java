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
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class MyPageView {
    private final UserDTO userDTO;
    private final int firstColWidth = 70; // ù ��° ���� ��
    private final int secondColWidth = 105; // �� ��° ���� ��
    private final String leftAlignFormat = "�� %-" + firstColWidth + "s �� %-" + secondColWidth + "s ��%n";

    public MyPageView(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public void showView() throws IOException, ClassNotFoundException {
        for (; ; ) {
            try {
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
            } catch (InputMismatchException e) {
                System.out.println("Wrong Input..");
            }
        }
    }

    private void showCompletedStays() throws IOException, ClassNotFoundException {
        int firstColWidth1 = 70; // ù ��° ���� ��
        int secondColWidth1 = 105; // �� ��° ���� ��
        String leftAlignFormat1 = "�� %-" + firstColWidth1 + "s �� %-" + secondColWidth1 + "s ��%n";
        StayedHouseController stayedHouseController = new StayedHouseController();
        Protocol protocol = stayedHouseController.completedStayRequest(userDTO);
        if (protocol.getProtocolCode() == Protocol.CODE_ERROR) {
            System.out.println(protocol.getObject());
        } else {
            List<CompletedStayDTO> list = (List<CompletedStayDTO>) protocol.getObject();
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
            System.out.format("��                                                                               Completed Stays                                                                                      ��%n");
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

            if (list.isEmpty()) {
                System.out.format("�� No completed stays found                                                                                                                                                           ��%n");
            } else {
                for (int i = 0; i < list.size(); i++) {
                    CompletedStayDTO completedStayDTO = list.get(i);
                    String reviewStatus = completedStayDTO.isHasReview() ? "Reviewed" : "Not Reviewed";
                    System.out.format(leftAlignFormat1, (i + 1) + ". " + completedStayDTO.getHouseName(), "Check-in: " + completedStayDTO.getCheckIn());
                    System.out.format(leftAlignFormat1, "", "Check-out: " + completedStayDTO.getCheckOut());
                    System.out.format(leftAlignFormat1, "", "Cost: " + completedStayDTO.getCost());
                    System.out.format(leftAlignFormat1, "", reviewStatus);
                    System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
                }
            }
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
            handleReviewOption(list);
        }
    }

    private void handleReviewOption(List<CompletedStayDTO> list) {
        if (!list.isEmpty()) {
            System.out.println("                                                                             [        Write a review?       ]");
            System.out.println("                                                                             ������                          ������");
            System.out.println("                                                                             ��       (1) Yes   (2) No       ��");
            System.out.println("                                                                             ������                          ������");
            System.out.print("                                                                                       Selection : ");
            int choice = MyIOStream.sc.nextInt();
            MyIOStream.sc.nextLine(); // Clear buffer
            if (choice == 1) {
                System.out.format("                                         ��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
                System.out.format("                                         ��             Enter House ID, Star Rating, Review Text (Format: houseId,star,review)            ��%n");
                System.out.format("                                         ��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
                System.out.print("                                            Enter : ");
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
                            System.out.println("                                                                             ����������������������������������������������������������������");
                            System.out.println("                                                                             ��   Success To Write Review!   ��");
                            System.out.println("                                                                             ����������������������������������������������������������������");
                        }
                    } else {
                        System.out.println("                                                                             ����������������������������������������������������������������");
                        System.out.println("                                                                             ��   Error: Invalid House ID    ��");
                        System.out.println("                                                                             ����������������������������������������������������������������");
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException | IOException |
                         ClassNotFoundException e) {
                    System.out.println("                                                                             ����������������������������������������������������������������");
                    System.out.println("                                                                             ��  Error: Invalid Input Format ��");
                    System.out.println("                                                                             ����������������������������������������������������������������");
                }
            }
        }
    }


    private void showReservations() throws IOException, ClassNotFoundException {
        int firstColWidth1 = 70; // ù ��° ���� ��
        int secondColWidth1 = 105; // �� ��° ���� ��
        String leftAlignFormat1 = "�� %-" + firstColWidth1 + "s �� %-" + secondColWidth1 + "s ��%n";
        SearchGuestReservationController searchGuestReservationController = new SearchGuestReservationController();
        Protocol protocol = searchGuestReservationController.reservationStayRequest(userDTO);
        if (protocol.getProtocolCode() == Protocol.CODE_ERROR) {
            System.out.println(protocol.getObject());
        } else if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
            List<CompletedStayDTO> list = (List<CompletedStayDTO>) protocol.getObject();
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
            System.out.format("��                                                                              Accommodation Reservation                                                                             ��%n");
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

            if (list.isEmpty()) {
                System.out.format("�� No reservations found                                                                                                                                                              ��%n");
            } else {
                for (int i = 0; i < list.size(); i++) {
                    CompletedStayDTO completedStayDTO = list.get(i);
                    System.out.format(leftAlignFormat1, (i + 1) + ". " + completedStayDTO.getHouseName(), "Check-in: " + completedStayDTO.getCheckIn());
                    System.out.format(leftAlignFormat1, "", "Check-out: " + completedStayDTO.getCheckOut());
                    System.out.format(leftAlignFormat1, "", "Cost: " + completedStayDTO.getCost());
                    System.out.format(leftAlignFormat1, "", "Status: " + completedStayDTO.getReservationStatus().name());
                    System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
                }
            }

            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
            handleReservationCancellation(list, searchGuestReservationController);
        }
    }

    private void handleReservationCancellation(List<CompletedStayDTO> list, SearchGuestReservationController controller) throws IOException, ClassNotFoundException {
        if (!list.isEmpty()) {
            System.out.println("                                                                             [     Cancel a reservation?    ]");
            System.out.println("                                                                             ������                          ������");
            System.out.println("                                                                             ��       (1) Yes   (2) No       ��");
            System.out.println("                                                                             ������                          ������");
            System.out.print("                                                                                       Selection : ");
            int choice = MyIOStream.sc.nextInt();
            if (choice == 1) {
                System.out.format("                                             ��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
                System.out.format("                                             ��                    Enter the number of the accommodation to cancel (exit: -1)                 ��%n");
                System.out.format("                                             ��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
                System.out.print("                                                                                      Enter : ");
                int enter = MyIOStream.sc.nextInt();
                if (enter != -1 && enter > 0 && enter <= list.size()) {
                    Protocol protocol = controller.cancelReservationRequest(list.get(enter - 1).getReservationId(), list.get(enter - 1).getReservationStatus(), list.get(enter - 1).getCheckIn(), list.get(enter - 1).getCheckOut());
                    if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                        System.out.println("                                                                         ��������������������������������������������������������������������������������");
                        System.out.println("                                                                         ��  Reservation successfully cancelled. ��");
                        System.out.println("                                                                         ��������������������������������������������������������������������������������");
                    } else if (protocol.getProtocolCode() == Protocol.CODE_ERROR) {
                        System.out.println(protocol.getObject());
                    }
                } else if (enter != -1) {
                    System.out.println("                                                                         ����������������������������������������������������������������");
                    System.out.println("                                                                         ��   Error: Invalid selection.  ��");
                    System.out.println("                                                                         ����������������������������������������������������������������");
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
        int labelWidth = 70; // ���� ��
        int textWidth = 105; // �ؽ�Ʈ�� ��
        String format = "| %-" + labelWidth + "s | %-" + textWidth + "s |%n";

        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("��                                                                           Current User Information                                                                                 ��%n");
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        printFormatted("Name", userDTO.getUserName(), labelWidth, textWidth);
        printFormatted("Phone Number", userDTO.getUserPhone(), labelWidth, textWidth);
        printFormatted("Birthday", userDTO.getUserBirthday(), labelWidth, textWidth);
        printFormatted("Role", String.valueOf(userDTO.getRole()), labelWidth, textWidth);
        printFormatted("ID", userDTO.getLoginId(), labelWidth, textWidth);
        printFormatted("Password", userDTO.getLoginPwd(), labelWidth, textWidth);
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("��                                                                           Modify User Information                                                                                  ��%n");
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("��                                                                            1. Modify Name                                                                                          ��%n");
        System.out.format("��                                                                            2. Modify Phone Number                                                                                  ��%n");
        System.out.format("��                                                                            3. Modify Birthday                                                                                      ��%n");
        System.out.format("��                                                                            4. Exit                                                                                                 ��%n");
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.print("Choose an option: ");
        return MyIOStream.sc.nextInt();
    }


    private void modifyUserName(ModifyUserInfoController controller) throws IOException, ClassNotFoundException {
        System.out.print("Enter the new name: ");
        String newName = MyIOStream.sc.next();
        Protocol protocol = controller.modifyNameRequest(userDTO.getUserId(), newName);
        if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
            System.out.println("                                                                             ����������������������������������������������������������������");
            System.out.println("                                                                             ��  Name successfully updated.  ��");
            System.out.println("                                                                             ����������������������������������������������������������������");
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
            System.out.println("                                                                           ����������������������������������������������������������������������������");
            System.out.println("                                                                           �� Phone number successfully updated. ��");
            System.out.println("                                                                           ����������������������������������������������������������������������������");
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
            System.out.println("                                                                           ��������������������������������������������������������������������");
            System.out.println("                                                                           �� Birthday successfully updated. ��");
            System.out.println("                                                                           ��������������������������������������������������������������������");
            userDTO.setUserBirthday(newBirthday);
        } else {
            System.out.println(protocol.getObject());
        }
    }


    private int getCommand() {
        // ��� �Է��� ���� �޼ҵ�
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("��                                                                                          <My Page>                                                                                 ��%n");
        printUserInfo();
        System.out.format("��                                                                                   1. View Completed Stays                                                                          ��%n");
        System.out.format("��                                                                                   2. View Reservations                                                                             ��%n");
        System.out.format("��                                                                                   3. Modify User Info                                                                              ��%n");
        System.out.format("��                                                                                   4. Back                                                                                          ��%n");
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.print("Enter Command: ");
        return MyIOStream.sc.nextInt();
    }

    private void printUserInfo() {
        int labelWidth = 70; // ���� ��
        int textWidth = 105; // �ؽ�Ʈ�� ��
        String format = "| %-" + labelWidth + "s | %-" + textWidth + "s |%n";

        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        printFormatted("Name", userDTO.getUserName(), labelWidth, textWidth);
        printFormatted("Role", String.valueOf(userDTO.getRole()), labelWidth, textWidth);
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
    }

    private static void printFormatted(String label, String text, int labelWidth, int textWidth) {
        String[] labelWords = label.split(" ");
        String[] textWords = text.split(" ");
        ArrayList<String> formattedLabel = new ArrayList<>();
        StringBuilder labelLine = new StringBuilder();
        StringBuilder textLine = new StringBuilder();

        // �� �ٹٲ� ó��
        for (String word : labelWords) {
            if (labelLine.length() + word.length() + 1 > labelWidth) { // ���� ����
                formattedLabel.add(labelLine.toString().trim());
                labelLine.setLength(0);
            }
            labelLine.append(word).append(" ");
        }
        formattedLabel.add(labelLine.toString().trim()); // ������ �� �� �߰�

        // �ؽ�Ʈ �ٹٲ� �� �󺧰� �Բ� ���
        int labelIndex = 0;
        for (String word : textWords) {
            if (textLine.length() + word.length() + 1 > textWidth) { // ���� ����
                String currentLabel = labelIndex < formattedLabel.size() ? formattedLabel.get(labelIndex) : "";
                System.out.printf("�� %-" + labelWidth + "s �� %-" + textWidth + "s ��%n", currentLabel, textLine.toString().trim());
                textLine.setLength(0);
                labelIndex++;
            }
            textLine.append(word).append(" ");
        }
        // �ؽ�Ʈ�� ������ �ٰ� ���� �� �� ���
        while (labelIndex < formattedLabel.size() || textLine.length() > 0) {
            String currentLabel = labelIndex < formattedLabel.size() ? formattedLabel.get(labelIndex) : "";
            String currentText = textLine.toString().trim();
            System.out.printf("�� %-" + labelWidth + "s �� %-" + textWidth + "s ��%n", currentLabel, currentText);
            textLine.setLength(0);
            labelIndex++;
        }
    }
}
