package airbnb.view;

import airbnb.controller.*;
import airbnb.network.HouseType;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class GuestView {
    private final UserDTO userDTO;
    private final int firstColWidth = 70; // ù ��° ���� ��
    private final int secondColWidth = 105; // �� ��° ���� ��
    private final String leftAlignFormat = "| %-" + firstColWidth + "s | %-" + secondColWidth + "s |%n";

    public GuestView(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public void showView() throws Exception {
        for (; ; ) {
            try {
                int command = getCommand();
                if (command == 4) {
                    break;
                }

                switch (command) {
                    case 1:
                        houseFiltering();
                        break;
                    case 2:
                        houseAllList();
                        break;
                    case 3:
                        MyPageView myPageView = new MyPageView(userDTO);
                        myPageView.showView();
                        break;
                    default:
                        System.out.println("����������������������������������������������������������������");
                        System.out.println("��   Please Select (1) ~ (4)    ��");
                        System.out.println("����������������������������������������������������������������");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong Input..");
                MyIOStream.sc.nextLine();
            }
        }
    }

    private void houseFiltering() throws Exception {
        String houseName, checkIn, checkOut = "";
        int guestNum = 0;
        MyIOStream.sc.nextLine(); // Buffer Clear
        System.out.format("                                                    ��������                                                                     ��������%n");
        System.out.format("                                                    ��                                                                           ��%n");
        System.out.format("                                                                                                                                 %n");
        System.out.print("                                                               Enter House Name (If not, Press 'Enter'): ");

        houseName = MyIOStream.sc.nextLine();
        System.out.print("                                                               Enter CheckIn (YYYY-MM-DD) (If not, Press 'Enter'): ");
        checkIn = MyIOStream.sc.nextLine();
        if (!checkIn.equals("")) {
            System.out.print("                                                               Enter CheckOut (YYYY-MM-DD) (If not, Press 'Enter'): ");
            checkOut = MyIOStream.sc.nextLine();
        }
        System.out.print("                                                               Enter Guest Num : ");
        guestNum = MyIOStream.sc.nextInt();

        System.out.println("                                                                     ������������������������������������������������������������������������������������������");
        System.out.println("                                                                     ��              Enter House Type             ��");
        System.out.println("                                                                     ������������������������������������������������������������������������������������������");
        System.out.println("                                                                     ��          (1) private   (2) public         ��");
        System.out.println("                                                                     ������                                       ������");
        System.out.print("                                                                                     Selection : ");
        int houseType = MyIOStream.sc.nextInt();
        System.out.format("                                                    ����������������������������������������������������������������������������������������������������������������������������������������������������������%n");

        if (houseType == 1 || houseType == 2) {
            // Amenities List ���
            List<String> amenitiesList = new ArrayList<>();
            AmenitiesRequestController amenitiesRequestController = new AmenitiesRequestController();
            Protocol protocol = amenitiesRequestController.basicAmenitiesListRequest();
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
            System.out.format("��                       Basic Amenities List                             ��                                                                                                           ��%n");
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");


            if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                int basicNum = 0;
                List<AmenitiesDTO> list = (List<AmenitiesDTO>) protocol.getObject();
                for (AmenitiesDTO amenitiesDTO : list) {
                    String BasicList = String.format("%d. %s", ++basicNum, amenitiesDTO.getAmenities());
                    printFormatted(BasicList, "", firstColWidth, secondColWidth);
                }
                System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

                System.out.print("Enter (separated by commas) (If not, Press 'Enter') : ");
                MyIOStream.sc.nextLine(); // Buffer Clear
                String basicAmenities = MyIOStream.sc.nextLine();
                if (!basicAmenities.equals("")) {
                    String[] basicArr = basicAmenities.split(",");
                    for (String s : basicArr) {
                        int n = Integer.parseInt(s);
                        if (n > 0 && n <= list.size()) {
                            amenitiesList.add(list.get(n - 1).getAmenities());
                        }
                    }
                }
            }
            protocol = amenitiesRequestController.safetyAmenitiesListRequest();
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
            System.out.format("��                        Safety Amenities List                           ��                                                                                                           ��%n");
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");


            if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                List<AmenitiesDTO> list = (List<AmenitiesDTO>) protocol.getObject();
                int safetyNum = 0;
                for (AmenitiesDTO amenitiesDTO : list) {
                    String SafetyList = String.format("%d. %s", ++safetyNum, amenitiesDTO.getAmenities());
                    printFormatted(SafetyList, "", firstColWidth, secondColWidth);
                }
                System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

                System.out.print("Enter (separated by commas) (If not, Press 'Enter') : ");
                String safetyAmenities = MyIOStream.sc.nextLine();
                if (!safetyAmenities.equals("")) {
                    String[] safetyArr = safetyAmenities.split(",");
                    for (String s : safetyArr) {
                        int n = Integer.parseInt(s);
                        if (n > 0 && n <= list.size()) {
                            amenitiesList.add(list.get(n - 1).getAmenities());
                        }
                    }
                }
            }
            protocol = amenitiesRequestController.accessAmenitiesListRequest();
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
            System.out.format("��                    Accessibility Amenities List                        ��                                                                                                           ��%n");
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

            if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                List<AmenitiesDTO> list = (List<AmenitiesDTO>) protocol.getObject();
                int accessNum = 0;
                for (AmenitiesDTO amenitiesDTO : list) {
                    String AccessibilityList = String.format("%d. %s", ++accessNum, amenitiesDTO.getAmenities());
                    printFormatted(AccessibilityList, "", firstColWidth, secondColWidth);
                }
                System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

                System.out.print("Enter (separated by commas) (If not, Press 'Enter') : ");
                String accessibilityAmenities = MyIOStream.sc.nextLine();
                if (!accessibilityAmenities.equals("")) {
                    String[] accessArr = accessibilityAmenities.split(",");
                    for (String s : accessArr) {
                        int n = Integer.parseInt(s);
                        if (n > 0 && n <= list.size()) {
                            amenitiesList.add(list.get(n - 1).getAmenities());
                        }
                    }
                }
            }

            SearchHouseController searchHouseController = new SearchHouseController();
            if (houseType == 1) {
                protocol = searchHouseController.filteringHouseRequest(houseName, checkIn, checkOut, guestNum, HouseType.PRIVATE, amenitiesList);
            } else {
                protocol = searchHouseController.filteringHouseRequest(houseName, checkIn, checkOut, guestNum, HouseType.PUBLIC, amenitiesList);
            }

            if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                List<HouseAndFeeDTO> houseAndFeeDTOS = (List<HouseAndFeeDTO>) protocol.getObject();
                printHouseList(houseAndFeeDTOS);
                System.out.format("                                             ��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
                System.out.format("                                             ��                       Which accommodation would you like to see more INFO? (back : -1)        ��%n");
                System.out.format("                                             ��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
                System.out.print("                                                                                      Enter : ");

                int index = MyIOStream.sc.nextInt();

                if (index == -1) {
                    System.out.println("back..");
                } else if (index > 0 && index <= houseAndFeeDTOS.size()) {
                    seeMoreInfo(houseAndFeeDTOS.get(index - 1));
                } else {
                    System.out.println("Wrong Input..");
                }
            }

        } else {
            System.out.println("Wrong Input..");
        }
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

    private int getCommand() {
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("��                             <Guest Page>                               ��                                                                                                           ��%n");
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        printUserInfo();
        System.out.format("��                            1. FIND HOUSE                               ��                                                                                                           ��%n");
        System.out.format("��                            2. HOUSE LIST                               ��                                                                                                           ��%n");
        System.out.format("��                            3. MYPAGE                                   ��                                                                                                           ��%n");
        System.out.format("��                            4. LOGOUT                                   ��                                                                                                           ��%n");
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

        System.out.print("enter : ");
        return MyIOStream.sc.nextInt();
    }

    private void printUserInfo() {
        System.out.format("��                          Name : %-13s                          ��                                                                                                           ��%n", userDTO.getUserName());
        System.out.format("��                          Role : %-13s                          ��                                                                                                           ��%n", userDTO.getRole());
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
    }

    private int getHouseListCommand() {
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("��                             <HOUSE LIST>                               ��                                                                                                           ��%n");
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("��                            1. Ascending                                ��                                                                                                           ��%n");
        System.out.format("��                            2. Descending                               ��                                                                                                           ��%n");
        System.out.format("��                            3. Reservation                              ��                                                                                                           ��%n");
        System.out.format("��                            4. Back                                     ��                                                                                                           ��%n");
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.print(" Enter : ");
        return MyIOStream.sc.nextInt();
    }

    private void houseAllList() throws Exception {
        SearchAllHouseController searchAllHouseController = new SearchAllHouseController();
        Protocol protocol = searchAllHouseController.allHouseRequest();

        List<HouseAndFeeDTO> allList = (List<HouseAndFeeDTO>) protocol.getObject();
        System.out.format("                                             ��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("                                             ��                                              ALL LIST                                         ��%n");
        System.out.format("                                             ��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

        if (allList != null) {
            printHouseList(allList);
            int listCommand = getHouseListCommand();

            if (listCommand == 4) {
                System.out.println("Back..");
            } else {
                switch (listCommand) {
                    case 1:
                        // ��������
                        printAscendingList(searchAllHouseController);
                        break;
                    case 2:
                        // ��������
                        printDescendingList(searchAllHouseController);
                        break;
                    case 3:
                        System.out.format("                                             ��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
                        System.out.format("                                             ��                                          ALL LIST                                             ��%n");
                        System.out.format("                                             ��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

                        System.out.format("                                             ��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
                        System.out.format("                                             ��                                 Enter Num to Reservation (Back : -1)                          ��%n");
                        System.out.format("                                             ��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
                        System.out.print("                                                                                      Enter : ");

                        int enter = MyIOStream.sc.nextInt();
                        if (enter == -1) {
                            System.out.println("Back..");
                        } else if (enter > 0 && enter <= allList.size()) {
                            seeMoreInfo(allList.get(enter - 1));
                        } else {
                            System.out.println("Wrong Input..");
                        }
                        break;
                    default:
                        System.out.println("Wrong Input..");
                        break;
                }
            }
        }
    }

    private void seeMoreInfo(HouseAndFeeDTO houseAndFeeDTO) throws Exception {
        SearchMoreHouseInfoController searchMoreHouseInfoController = new SearchMoreHouseInfoController();
        Protocol protocol = searchMoreHouseInfoController.printMoreInfo(houseAndFeeDTO.getHouseId());
        MoreHouseInfoDTO moreHouseInfoDTO = (MoreHouseInfoDTO) protocol.getObject();
        List<ReplyDTO> replyDTOList = moreHouseInfoDTO.getReplyDTOList();
        // ������ �����ֱ�
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("��                         DETAIL INFORMATION                             ��                                                                                                           ��%n");
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        printFormatted("[House Name]", houseAndFeeDTO.getHouseName(), firstColWidth, secondColWidth);
        printFormatted("[House Address]", houseAndFeeDTO.getHouseAddress(), firstColWidth, secondColWidth);
        printFormatted("[Capacity]", String.valueOf(houseAndFeeDTO.getBedroom()), firstColWidth, secondColWidth);
        printFormatted("[Bedroom Count]", String.valueOf(houseAndFeeDTO.getBedroom()), firstColWidth, secondColWidth);
        printFormatted("[Bathroom Count]", String.valueOf(houseAndFeeDTO.getBathroom()), firstColWidth, secondColWidth);
        printFormatted("[House Type]", houseAndFeeDTO.getHouseType().toString(), firstColWidth, secondColWidth);
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        printFormatted("[Weekday Cost]", String.valueOf(houseAndFeeDTO.getWeekday()), firstColWidth, secondColWidth);
        printFormatted("[Weekend Cost]", String.valueOf(houseAndFeeDTO.getWeekend()), firstColWidth, secondColWidth);
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        printFormatted("[House Info]", houseAndFeeDTO.getHouseIntroduce(), firstColWidth, secondColWidth);

        List<AmenitiesDTO> amenitiesList = moreHouseInfoDTO.getAmenitiesDTOList();
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("��                           Basic Amenities                              ��                                                                                                           ��%n");
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        if (amenitiesList != null) {
            for (AmenitiesDTO amenitiesDTO : amenitiesList) {
                if (amenitiesDTO.getTypeId() == 1) {
                    printFormatted(amenitiesDTO.getAmenities(), "", firstColWidth, secondColWidth);
                }
            }
        }
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

        System.out.format("��                          Safety Amenities                              ��                                                                                                           ��%n");
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        if (amenitiesList != null) {
            for (AmenitiesDTO amenitiesDTO : amenitiesList) {
                if (amenitiesDTO.getTypeId() == 2) {
                    printFormatted(amenitiesDTO.getAmenities(), "", firstColWidth, secondColWidth);
                }
            }
        }
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

        System.out.format("��                       Accessibility Amenities                          ��                                                                                                           ��%n");
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        if (amenitiesList != null) {
            for (AmenitiesDTO amenitiesDTO : amenitiesList) {
                if (amenitiesDTO.getTypeId() == 3) {
                    printFormatted(amenitiesDTO.getAmenities(), "", firstColWidth, secondColWidth);
                }
            }
        }
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

        List<UserReviewDTO> userReviewDTOS = moreHouseInfoDTO.getReviewDTOList();
        System.out.format("��                                Review                                  ��                                                                                                           ��%n");
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

        int reviewWidth1 = 65;
        int reviewWidth2 = 105;
        if (userReviewDTOS != null) {
            for (UserReviewDTO userReviewDTO : userReviewDTOS) {
                printFormatted("User Name: " + userReviewDTO.getUserName(), "", firstColWidth, secondColWidth);
                printFormatted("STAR: " + userReviewDTO.toString(), "", firstColWidth, secondColWidth);
                printFormatted("REVIEW: " + userReviewDTO.getReview(), "", firstColWidth, secondColWidth);

                if (replyDTOList != null) {
                    printFormatted("���������������������������������������������������� <HOST REPLY> ��������������������������������������������������������", "", firstColWidth, secondColWidth);
                    for (ReplyDTO replyDTO : replyDTOList) {
                        if (replyDTO.getReservationId() == userReviewDTO.getReservationId()) {
                            printFormattedReview(replyDTO.toString(), "", reviewWidth1, reviewWidth2);
                        }
                    }
                    printFormatted("����������������������������������������������������������������������������������������������������������������������������������������", "", firstColWidth, secondColWidth);
                }
            }
        }
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");


        System.out.println("                                                                             ������                          ������");
        System.out.println("                                                                             ��  (1) Reservation  (2) Back   ��");
        System.out.println("                                                                             ������                          ������");
        System.out.print("                                                                                       Selection : ");
        int command = MyIOStream.sc.nextInt();
        if (command == 1) {
            reservation(houseAndFeeDTO, moreHouseInfoDTO);
        } else if (command == 2) {
            System.out.println("Back..");
        } else {
            System.out.println("Wrong Input..");
        }

    }

    private void reservation(HouseAndFeeDTO houseAndFeeDTO, MoreHouseInfoDTO moreHouseInfoDTO) throws Exception {
        List<ReservationDTO> reservationDTOList = moreHouseInfoDTO.getReservationDTOList();
        DiscountPolicyDTO discountPolicyDTO = moreHouseInfoDTO.getDiscountPolicyDTO();
        FeePolicyDTO feePolicyDTO = moreHouseInfoDTO.getFeePolicyDTO();

        if (discountPolicyDTO == null) {
            discountPolicyDTO = new DiscountPolicyDTO();
        }

        int discountAmount = discountPolicyDTO.getDiscount_amount();
        int discountRate = discountPolicyDTO.getDiscount_rate();
        System.out.println("[Show Reservation available dates]");//////////�޷� ���� �� Ȯ���ϰ� ����ϱ�

        if (reservationDTOList != null) {
//           MyCalender.print(reservationDTOList);
            CalendarViewerForAdmin.run(reservationDTOList, houseAndFeeDTO);
        } else {
            System.out.println("                                                                         ��������������������������������������������������������������������������������");
            System.out.println("                                                                         �� All Date is Possible to Reservation  ��");
            System.out.println("                                                                         ��������������������������������������������������������������������������������");
        }

        System.out.format("                                                      ����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("                                                      ��                                                                           ��%n");
        System.out.format("                                                                                                                                   %n");

        System.out.print("                                                                               Adult Num : ");
        int adultNum = MyIOStream.sc.nextInt();
        System.out.print("                                                                                Child Num : ");
        int childNum = MyIOStream.sc.nextInt();
        System.out.print("                                                                       Enter CheckIn Day (YYYY-MM-DD) : ");
        MyIOStream.sc.nextLine();
        String checkIn = MyIOStream.sc.next();
        System.out.print("                                                                       Enter CheckOut Day (YYYY-MM-DD) : ");
        String checkOut = MyIOStream.sc.next();
        int totalNum = adultNum + childNum;

        int cost = 0;

        if (houseAndFeeDTO.getHouseType() == HouseType.PRIVATE) {
            if (discountRate > 0) {
                cost = SaleCalculator.CalculateRate(checkIn, checkOut, discountPolicyDTO, feePolicyDTO, totalNum);
            } else {
                cost = SaleCalculator.CalculateAmount(checkIn, checkOut, discountPolicyDTO, feePolicyDTO, totalNum);
            }
        } else {
            if (discountRate > 0) {
                cost = SaleCalculator_2.CalculateRate(checkIn, checkOut, discountPolicyDTO, feePolicyDTO, totalNum);
            } else {
                cost = SaleCalculator_2.CalculateAmount(checkIn, checkOut, discountPolicyDTO, feePolicyDTO, totalNum);
            }
        }
        System.out.println("                                                                             ����������������������������������������������������������������");
        System.out.format("                                                                                    Cost = %s $             \n", cost);
        System.out.println("                                                                             ����������������������������������������������������������������");

        System.out.println("                                                                     ������������������������������������������������������������������������������������������");
        System.out.println("                                                                     ��   Would you like to make a reservation?   ��");
        System.out.println("                                                                     ������������������������������������������������������������������������������������������");
        System.out.println("                                                                     ��             (1) YES!   (2) NO!            ��");
        System.out.println("                                                                     ������                                       ������");
        System.out.print("                                                                                      Selection : ");

        int enter = MyIOStream.sc.nextInt();

        if (enter == 1) {
            ReservationRequestController reservationRequestController = new ReservationRequestController();
            Protocol protocol = reservationRequestController.reservationRequest(houseAndFeeDTO.getHouseId(), userDTO.getUserId(), totalNum, checkIn, checkOut, cost);

            if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                System.out.println("                                                                             ����������������������������������������������������������������");
                System.out.println("                                                                             ��    Success to Reservation    ��");
                System.out.println("                                                                             ����������������������������������������������������������������");
            } else {
                System.out.println(protocol.getObject());
            }
        } else if (enter == 2) {
            System.out.println("                                                                             ����������������������������������������������������������������");
            System.out.println("                                                                             ��           CANCEL!!           ��");
            System.out.println("                                                                             ����������������������������������������������������������������");
        } else {
            System.out.println("Wrong Input..");
        }
    }

    private void printDescendingList(SearchAllHouseController searchAllHouseController) throws
            Exception {
        Protocol protocol = searchAllHouseController.descendingHouseRequest();
        List<HouseAndFeeDTO> descendingList = (List<HouseAndFeeDTO>) protocol.getObject();
        System.out.format("                                             ��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("                                             ��                                         DESCENDING LIST                                       ��%n");
        System.out.format("                                             ��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

        if (descendingList != null) {
            printHouseList(descendingList);

            int listCommand = getHouseListCommand();

            if (listCommand == 4) {
                System.out.println("Back..");
            } else {
                switch (listCommand) {
                    case 1:
                        // ��������
                        printAscendingList(searchAllHouseController);
                        break;
                    case 2:
                        // ��������
                        printDescendingList(searchAllHouseController);
                        break;
                    case 3:
                        System.out.println("                                                                         ��������������������������������������������������������������������������������");
                        System.out.println("                                                                         �� Enter Num to Reservation (Back : -1) ��");
                        System.out.println("                                                                         ��������������������������������������������������������������������������������");
                        System.out.print("                                                                                      Enter : ");

                        int enter = MyIOStream.sc.nextInt();

                        if (enter == -1) {
                            System.out.println("Back..");
                        } else if (enter > 0 && enter <= descendingList.size()) {
                            seeMoreInfo(descendingList.get(enter - 1));
                        } else {
                            System.out.println("Wrong Input..");
                        }
                        break;
                    default:
                        System.out.println("Wrong Input..");
                        break;
                }
            }
        }
    }

    private void printAscendingList(SearchAllHouseController searchAllHouseController) throws
            Exception {
        Protocol protocol = searchAllHouseController.ascendingHouseRequest();
        List<HouseAndFeeDTO> ascendingList = (List<HouseAndFeeDTO>) protocol.getObject();
        System.out.format("                                             ��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("                                             ��                                          ASCENDING LIST                                       ��%n");
        System.out.format("                                             ��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

        if (ascendingList != null) {
            printHouseList(ascendingList);

            int listCommand = getHouseListCommand();

            if (listCommand == 4) {
                System.out.println("Back..");
            } else {
                switch (listCommand) {
                    case 1:
                        // ��������
                        printAscendingList(searchAllHouseController);
                        break;
                    case 2:
                        // ��������
                        printDescendingList(searchAllHouseController);
                        break;
                    case 3:
                        System.out.println("                                                                         ��������������������������������������������������������������������������������");
                        System.out.println("                                                                         �� Enter Num to Reservation (Back : -1) ��");
                        System.out.println("                                                                         ��������������������������������������������������������������������������������");
                        System.out.print("                                                                                      Enter : ");

                        int enter = MyIOStream.sc.nextInt();

                        if (enter == -1) {
                            System.out.println("Back..");
                        } else if (enter > 0 && enter <= ascendingList.size()) {
                            seeMoreInfo(ascendingList.get(enter - 1));
                        } else {
                            System.out.println("Wrong Input..");
                        }
                        break;
                    default:
                        System.out.println("Wrong Input..");
                        break;
                }
            }
        }
    }

    private void printHouseList(List<HouseAndFeeDTO> houseList) {
        int i = 0;
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("��                             <HOUSE LIST>                               ��                                                                                                           ��%n");
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

        for (HouseAndFeeDTO houseAndFeeDTO : houseList) {
//            System.out.printf("%d. %s\n", ++i, houseAndFeeDTO.toString());
            printFormatted("[" + (++i) + "] " + houseAndFeeDTO.getHouseName(), "[House Address] " + houseAndFeeDTO.getHouseAddress(), firstColWidth, secondColWidth);
            printFormatted("", "[Weekday Cost] " + houseAndFeeDTO.getWeekday(), firstColWidth, secondColWidth);
            printFormatted("", "[Weekend Cost] " + houseAndFeeDTO.getWeekend(), firstColWidth, secondColWidth);
            printFormatted("", "[Bedroom] " + houseAndFeeDTO.getBedroom(), firstColWidth, secondColWidth);
            printFormatted("", "[House Type] " + houseAndFeeDTO.getHouseType().toString(), firstColWidth, secondColWidth);
            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        }
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

    }

    private static void printFormattedReview(String label, String text, int labelWidth, int textWidth) {
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
                System.out.printf("�� �� %-" + labelWidth + "s ��  ��%-" + textWidth + "s   ��%n", currentLabel, textLine.toString().trim());
                textLine.setLength(0);
                labelIndex++;
            }
            textLine.append(word).append(" ");
        }
        // �ؽ�Ʈ�� ������ �ٰ� ���� �� �� ���
        while (labelIndex < formattedLabel.size() || textLine.length() > 0) {
            String currentLabel = labelIndex < formattedLabel.size() ? formattedLabel.get(labelIndex) : "";
            String currentText = textLine.toString().trim();
            System.out.printf("�� �� %-" + labelWidth + "s��   ��%-" + textWidth + "s  ��%n", currentLabel, currentText);
            textLine.setLength(0);
            labelIndex++;
        }
    }
}
