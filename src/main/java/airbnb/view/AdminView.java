package airbnb.view;

import airbnb.controller.AccommodationSituationController;
import airbnb.controller.RequestHouseRegistrationController;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class AdminView {
    private final UserDTO userDTO;
    private final int firstColWidth = 70; // First column width
    private final int secondColWidth = 105; // Second column width
    private final String leftAlignFormat = "| %-" + firstColWidth + "s | %-" + secondColWidth + "s |%n";

    public AdminView(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public void showView() throws Exception {
        for (; ; ) {
            try {
                System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
                System.out.format("��                            <ADMIN PAGE>                                ��                                                                                                           ��%n");
                System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
                System.out.format("�� 1. Approval/Reject Host's Accommodation registration                   ��                                                                                                           ��%n");
                System.out.format("�� 2. Check current accommodation situation                               ��                                                                                                           ��%n");
                System.out.format("�� 0. Logout                                                              ��                                                                                                           ��%n");
                System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

                System.out.print("Enter command: ");

                int choice = MyIOStream.sc.nextInt();

                if (choice == 0) {
                    System.out.println("Log out..");
                    break;
                }

                switch (choice) {
                    case 1:
                        manageAccommodationRequests();
                        break;
                    case 2:
                        checkAccommodationSituation();
                        break;
                    default:
                        System.out.format("                                             ��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
                        System.out.format("                                             ��                                 Invalid input. Please try again.                              ��%n");
                        System.out.format("                                             ��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong Input..");
            }
        }
    }

    private void checkAccommodationSituation() throws Exception {
        //���� ����Ʈ ���
        AccommodationSituationController accommodationSituationController = new AccommodationSituationController();
        Protocol protocol = accommodationSituationController.listRequest();
        List<HouseDTO> houseDTOList = (List<HouseDTO>) protocol.getObject();
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("��                             <HOUSE LIST>                               ��                                                                                                           ��%n");
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("��                             [HOUSE NAME]                               ��                                                [HOUSE ADDRESS]                                            ��%n");

        int i = 0;
        if (houseDTOList != null) {
            for (HouseDTO houseDTO : houseDTOList) {
                printFormatted(++i + ". " + houseDTO.getHouseName(), houseDTO.getHouseAddress(), firstColWidth, secondColWidth);
            }
        }
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");


        System.out.println("                                                           ��������������������������������������������������������������������������������������������������������������������������������������");
        System.out.println("                                                           ��                   Select Accommodation number (back : -1)       ��");
        System.out.println("                                                           ��������������������������������������������������������������������������������������������������������������������������������������");
        System.out.print("                                                                                     Selection : ");
        int accommodationNum = MyIOStream.sc.nextInt();

        if (accommodationNum > 0 && accommodationNum <= i) {
            //�߰� ����
            System.out.println("                                                           ��������������������������������������������������������������������������������������������������������������������������������������");
            System.out.println("                                                           ��   1. Check monthly reservation status for each accommodation    ��");
            System.out.println("                                                           ��   2. Check total monthly sales for each accommodation           ��");
            System.out.println("                                                           ��������������������������������������������������������������������������������������������������������������������������������������");
            System.out.print("                                                                                     Selection : ");
            int num = MyIOStream.sc.nextInt();

            if (num == 1) { // ��� reservationDTOList �����ͼ� ���¿� ���� �޷¿� ǥ���ؼ� �����
                protocol = accommodationSituationController.monthlyReservationRequest(houseDTOList.get(accommodationNum - 1).getHouseId());
                MoreHouseInfoDTO moreHouseInfoDTO = (MoreHouseInfoDTO) protocol.getObject();
                List<ReservationDTO> reservationDTOList = moreHouseInfoDTO.getReservationDTOList();
                System.out.println("                                                                         ��������������������������������������������������������������������������������");
                System.out.println("                                                                         ��        Select Month (1 ~ 12)         ��");
                System.out.println("                                                                         ��������������������������������������������������������������������������������");
                System.out.print("                                                                                      Select : ");

                int enterMonth = MyIOStream.sc.nextInt();

                if (enterMonth >= 1 && enterMonth <= 12) {
                    CalendarViewerForAdmin_test.selectMonth(enterMonth, reservationDTOList, houseDTOList.get(accommodationNum - 1));
                } else {
                    System.out.println("Wrong Input..");
                }
            } else if (num == 2) {
                protocol = accommodationSituationController.afterStayReservationRequest(houseDTOList.get(accommodationNum - 1));
                MoreHouseInfoDTO moreHouseInfoDTO = (MoreHouseInfoDTO) protocol.getObject();
                List<ReservationDTO> reservationDTOList = moreHouseInfoDTO.getReservationDTOList();
                System.out.println("                                                                         ��������������������������������������������������������������������������������");
                System.out.println("                                                                         ��        Select Month (1 ~ 12)         ��");
                System.out.println("                                                                         ��������������������������������������������������������������������������������");
                System.out.print("                                                                                      Select : ");

                int enterMonth = MyIOStream.sc.nextInt();
                if (enterMonth >= 1 && enterMonth <= 12) {
                    CalendarViewerForAdmin_test.selectMonth(enterMonth, reservationDTOList, houseDTOList.get(accommodationNum - 1));
                    TotalCost.totalCost(enterMonth, reservationDTOList, moreHouseInfoDTO.getDiscountPolicyDTO(), moreHouseInfoDTO.getFeePolicyDTO(), houseDTOList.get(accommodationNum - 1));
                } else {
                    System.out.println("Wrong Input..");
                }
            } else {
                System.out.println("Wrong Input..");
            }
        } else if (accommodationNum == -1) {
            System.out.println("back..");
        } else {
            System.out.println("Wrong Input..");
        }
    }

    private void manageAccommodationRequests() throws IOException, ClassNotFoundException {
        // ȣ��Ʈ�� ���� ��� ��û�� ����Ʈ �����ֱ�
        RequestHouseRegistrationController requestHouseRegistrationController = new RequestHouseRegistrationController();
        Protocol protocol = requestHouseRegistrationController.listRequest();
        List<HouseAndHostDTO> list = (List<HouseAndHostDTO>) protocol.getObject();
        System.out.format("��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("��                                                                               [ Discount Policy ]                                                                                       ��%n");
        System.out.format("��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("��%-60s��%-80s��%-20s��%-15s       ��%n", "[House Name]", "[HouseAddress]", "[Host Name]", "[Host ID]");
        System.out.format("��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

        int i = 0;
        if (list != null) {
            for (HouseAndHostDTO houseAndHostDTO : list) {
                printFormattedForAccommodationRequests(++i + ". " + houseAndHostDTO.getHouseName(), houseAndHostDTO.getHouseAddress(), houseAndHostDTO.getHostName(), String.valueOf(houseAndHostDTO.getHostId()), 58, 78, 18, 20);
            }
        }
        System.out.format("��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.println("                                                                         ��������������������������������������������������������������������������������");
        System.out.println("                                                                         ��  See More Detail Number [Back (-1)]  ��");
        System.out.println("                                                                         ��������������������������������������������������������������������������������");
        System.out.print("                                                                                      Select : ");

        int enter = MyIOStream.sc.nextInt();

        if (enter == -1) {
            System.out.println("Back..");
        } else if (enter > 0 && enter <= i) {
            // ������ �� ���� ����
            protocol = requestHouseRegistrationController.detailInfoRequest(list.get(enter - 1).getHouseId());

            List<AmenitiesDTO> amenitiesDTOList = (List<AmenitiesDTO>) protocol.getObject();
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
            System.out.format("��                                                                                    HOUSE DETAIL                                                                                    ��%n");
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
            printFormatted("[Host Name]", list.get(enter - 1).getHostName(), firstColWidth, secondColWidth);
            printFormatted("[Host ID]", list.get(enter - 1).getLoginId(), firstColWidth, secondColWidth);
            printFormatted("[House Name]", list.get(enter - 1).getHouseName(), firstColWidth, secondColWidth);
            printFormatted("[House Address]", list.get(enter - 1).getHouseAddress(), firstColWidth, secondColWidth);
            printFormatted("[Bedroom Count]", String.valueOf(list.get(enter - 1).getBedroom()), firstColWidth, secondColWidth);
            printFormatted("[Bathroom Count]", String.valueOf(list.get(enter - 1).getBathroom()), firstColWidth, secondColWidth);
            printFormatted("[Capacity]", String.valueOf(list.get(enter - 1).getBedroom()), firstColWidth, secondColWidth);
            printFormatted("[House Type]", list.get(enter - 1).getHouseType().toString(), firstColWidth, secondColWidth);
            printFormatted("[House Info]", list.get(enter - 1).getHouseIntroduce(), firstColWidth, secondColWidth);


            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
            System.out.format("��                           Basic Amenities                              ��                                                                                                           ��%n");
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

            if (list != null) {
                for (AmenitiesDTO amenitiesDTO : amenitiesDTOList) {
                    if (amenitiesDTO.getTypeId() == 1) {
                        printFormatted(amenitiesDTO.getAmenities(), "", firstColWidth, secondColWidth);
                    }
                }
            }
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

            System.out.format("��                          Safety Amenities                              ��                                                                                                           ��%n");
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

            if (list != null) {
                for (AmenitiesDTO amenitiesDTO : amenitiesDTOList) {
                    if (amenitiesDTO.getTypeId() == 2) {
                        printFormatted(amenitiesDTO.getAmenities(), "", firstColWidth, secondColWidth);
                    }
                }
            }
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

            System.out.format("��                       Accessibility Amenities                          ��                                                                                                           ��%n");
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
            if (list != null) {
                for (AmenitiesDTO amenitiesDTO : amenitiesDTOList) {
                    if (amenitiesDTO.getTypeId() == 3) {
                        printFormatted(amenitiesDTO.getAmenities(), "", firstColWidth, secondColWidth);
                    }
                }
            }
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

            System.out.println("                                                                          ������                                ������");
            System.out.println("                                                                          ��  (1) Approval (2) Reject (3) Back  ��");
            System.out.println("                                                                          ������                                ������");
            System.out.print("                                                                                     Selection : ");

            int command = MyIOStream.sc.nextInt();

            if (command == 3) {
                System.out.println("Back..");
            } else if (command == 1) {
                // ����
                protocol = requestHouseRegistrationController.approvalRequest(list.get(enter - 1).getHouseId());

                if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                    System.out.println("                                                                             ����������������������������������������������������������������");
                    System.out.println("                                                                             ��     Success to Approval      ��");
                    System.out.println("                                                                             ����������������������������������������������������������������");

                }

            } else if (command == 2) {
                protocol = requestHouseRegistrationController.rejectRequest(list.get(enter - 1).getHouseId());

                if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                    System.out.println("                                                                             ����������������������������������������������������������������");
                    System.out.println("                                                                             ��      Success to Reject       ��");
                    System.out.println("                                                                             ����������������������������������������������������������������");

                }
                // ����
            } else {
                System.out.println("Wrong Input..");
            }
        } else {
            System.out.println("Wrong Input..");
        }
    }

    private void viewMonthlyReservationStatus() {
        // �����ϸ� ��ϵ� ���ҵ��� ����Ʈ�� �ǰ� ���Ҹ� �����ϸ� �� ���ҿ� ���� ���� ��Ȳ�� �޷����� ��� calender() ����
        System.out.format("��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("�� Registered Accommodations List ��                                                              ��%n");
        System.out.format("��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        printFormatted("Line", "", firstColWidth, secondColWidth);
        // �� printFormatted�� �̿��ؼ� Line�� DTO���� �����°� �ְ� �ι�°���ʹ� ����
        System.out.format("��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.println("Please select the accommodation : ");

        System.out.println("Enter the year and month to check(YYYY MM): ");
    }

    private void viewMonthlyRevenue() {
        System.out.format("��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("�� Registered Accommodations List ��                                                              ��%n");
        System.out.format("��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        printFormatted("Line", "", firstColWidth, secondColWidth);
        // �� printFormatted�� �̿��ؼ� Line�� DTO���� �����°� �ְ� �ι�°���ʹ� ����
        System.out.format("��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

        System.out.println("Please select the accommodation : ");

        System.out.println("Enter the year and month to check(YYYY MM): ");
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

    private static void printFormattedForAccommodationRequests(String houseName, String houseAddress, String hostName, String hostID,
                                                               int firstWidth, int secondWidth, int thirdWidth, int fourthWidth) {
        ArrayList<String> houseNameLines = splitAndFormat(houseName, firstWidth);
        ArrayList<String> houseAddressLines = splitAndFormat(houseAddress, secondWidth);
        ArrayList<String> hostNameLines = splitAndFormat(hostName, thirdWidth);
        ArrayList<String> hostIDLines = splitAndFormat(hostID, fourthWidth);

        int maxLines = Math.max(Math.max(houseNameLines.size(), houseAddressLines.size()),
                Math.max(hostNameLines.size(), hostIDLines.size()));

        for (int i = 0; i < maxLines; i++) {
            String houseNameLine = i < houseNameLines.size() ? houseNameLines.get(i) : "";
            String houseAddressLine = i < houseAddressLines.size() ? houseAddressLines.get(i) : "";
            String hostNameLine = i < hostNameLines.size() ? hostNameLines.get(i) : "";
            String hostIDLine = i < hostIDLines.size() ? hostIDLines.get(i) : "";

            System.out.format("�� %-" + firstWidth + "s �� %-" + secondWidth + "s �� %-" + thirdWidth + "s �� %-" + fourthWidth + "s ��%n",
                    houseNameLine, houseAddressLine, hostNameLine, hostIDLine);
        }
    }

    private static ArrayList<String> splitAndFormat(String text, int width) {
        String[] words = text.split(" ");
        ArrayList<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            if (currentLine.length() + word.length() + 1 > width) {
                lines.add(currentLine.toString().trim());
                currentLine = new StringBuilder();
            }
            currentLine.append(word).append(" ");
        }

        if (currentLine.length() > 0) {
            lines.add(currentLine.toString().trim());
        }

        return lines;
    }
}
