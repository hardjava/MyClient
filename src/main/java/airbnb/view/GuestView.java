package airbnb.view;

import airbnb.controller.SearchAllHouseController;
import airbnb.controller.SearchMoreHouseInfoController;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GuestView {
    UserDTO userDTO;
    int firstColWidth = 20; // ù ��° ���� ��
    int secondColWidth = 50; // �� ��° ���� ��
    String leftAlignFormat = "| %-" + firstColWidth + "s | %-" + secondColWidth + "s |%n";

    public GuestView(UserDTO userDTO) {
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
                    break;
                case 2:
                    SearchAllHouseController searchAllHouseController = new SearchAllHouseController();
                    Protocol protocol = searchAllHouseController.printAllHouseRequest();

                    if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {

                        List<HouseDTO> list = (List<HouseDTO>) protocol.getObject();
                        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
                        System.out.format("��      House list      ��                                                    ��%n");
                        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
                        System.out.format("�� %-20s �� %-50s ��%n", "[House Name]", "[House Address]");

                        int i = 0;
                        int labelWidth = 20;
                        int textWidth = 50;

                        for (HouseDTO houseDTO : list) {
                            String houseLabel = String.format("%d. %s", ++i, houseDTO.getHouseName());
                            printFormatted(houseLabel, houseDTO.getHouseAddress(), labelWidth, textWidth);
                        }
                        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");

                        System.out.print("Select More Info (exit -1) : ");
                        int enter = MyIOStream.sc.nextInt();

                        if (enter == -1) {
                            System.out.println("Exit..");
                            break;
                        }

                        if (enter > 0 && enter <= i) {
                            HouseDTO houseDTO = list.get(enter - 1);
                            SearchMoreHouseInfoController searchMoreHouseInfoController = new SearchMoreHouseInfoController();
                            protocol = searchMoreHouseInfoController.printMoreInfo(houseDTO);
                            MoreHouseInfoDTO moreHouseInfoDTO = (MoreHouseInfoDTO) protocol.getObject();

                            List<AmenitiesDTO> amenitiesDTOList = moreHouseInfoDTO.getAmenitiesDTOList();
                            FeePolicyDTO feePolicyDTO = moreHouseInfoDTO.getFeePolicyDTO();
                            List<ReservationDTO> reservationDTOList = moreHouseInfoDTO.getReservationDTOList();
                            List<UserReviewDTO> userReviewDTOS = moreHouseInfoDTO.getReviewDTOList();

                            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
                            System.out.format("�� House Information    �� Details                                            ��%n");
                            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
                            printFormatted("Name", houseDTO.getHouseName(), firstColWidth, secondColWidth);
                            printFormatted("Address", houseDTO.getHouseAddress(), firstColWidth, secondColWidth);
                            printFormatted("Bedrooms", String.valueOf(houseDTO.getBedroom()), firstColWidth, secondColWidth);
                            printFormatted("Bathrooms", String.valueOf(houseDTO.getBathroom()), firstColWidth, secondColWidth);
                            printFormatted("Description", houseDTO.getHouseIntroduce(), firstColWidth, secondColWidth);

                            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
                            System.out.format("�� Cost Details         ������������������������������������������������������������������������������������������������������������%n");
                            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
                            printFormatted("Weekday", String.valueOf(feePolicyDTO.getWeekday()), firstColWidth, secondColWidth);
                            printFormatted("Weekend", String.valueOf(feePolicyDTO.getWeekend()), firstColWidth, secondColWidth);

                            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
                            System.out.format("�� Amenities            �� Details                                            ��%n");
                            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");


                            String basicAmenities = amenitiesDTOList.stream()
                                    .filter(amenitiesDTO -> amenitiesDTO.getTypeId() == 1)
                                    .map(AmenitiesDTO::getAmenities)
                                    .collect(Collectors.joining(", "));

                            if (!basicAmenities.isEmpty()) {
                                printFormatted("Basic", basicAmenities, firstColWidth, secondColWidth);
                            } else {
                                System.out.format("�� Basic                ��                                                    ��%n");
                            }


                            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");


                            String safetyAmenities = amenitiesDTOList.stream()
                                    .filter(amenitiesDTO -> amenitiesDTO.getTypeId() == 2)
                                    .map(AmenitiesDTO::getAmenities)
                                    .collect(Collectors.joining(", "));

                            if (!safetyAmenities.isEmpty()) {
                                printFormatted("Safety", safetyAmenities, firstColWidth, secondColWidth);
                            } else {
                                System.out.format("�� Safety               ��                                                    ��%n");
                            }


                            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");


                            String accessibilityAmenities = amenitiesDTOList.stream()
                                    .filter(amenitiesDTO -> amenitiesDTO.getTypeId() == 3)
                                    .map(AmenitiesDTO::getAmenities)
                                    .collect(Collectors.joining(", "));

                            if (!accessibilityAmenities.isEmpty()) {
                                printFormatted("Accessibility", accessibilityAmenities, firstColWidth, secondColWidth);
                            } else {
                                System.out.format("�� Accessibility        ��                                                    ��%n");
                            }

                            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
                            System.out.format("�� Reviews              ��                                                    ��%n");
                            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
                            boolean isFirstReview = true;
                            for (UserReviewDTO userReviewDTO : userReviewDTOS) {
                                String[] reviewLines = userReviewDTO.toString().split("\n");
                                for (String line : reviewLines) {
                                    if (isFirstReview) {
                                        printFormatted("Review", line, firstColWidth, secondColWidth);
                                        isFirstReview = false;
                                    } else {
                                        printFormatted("", line, firstColWidth, secondColWidth);
                                    }
                                }
                            }
                            System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");

                        } else {
                            System.out.println("Invalid Input..");
                        }
                    } else if (protocol.getProtocolCode() == Protocol.CODE_ERROR) {
                        System.out.println(protocol.getObject());
                    }

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
        }
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

    private int getCommand() {
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        System.out.format("��    <Guest Page>      ��                                                    ��%n");
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
        printUserInfo();
        System.out.format("�� 1. FIND HOUSE        ��                                                    ��%n");
        System.out.format("�� 2. HOUSE LIST        ��                                                    ��%n");
        System.out.format("�� 3. MYPAGE            ��                                                    ��%n");
        System.out.format("�� 4. LOGOUT            ��                                                    ��%n");
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");

        System.out.print("enter : ");
        return MyIOStream.sc.nextInt();
    }

    private void printUserInfo() {
        System.out.format("�� Name : %-13s ��                                                    ��%n", userDTO.getUserName());
        System.out.format("�� Role : %-13s ��                                                    ��%n", userDTO.getRole());
        System.out.format("����������������������������������������������������������������������������������������������������������������������������������������������������������%n");
    }
}
