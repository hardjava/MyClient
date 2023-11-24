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
    int firstColWidth = 20; // 羅 廓簞 翮曖 ァ
    int secondColWidth = 50; // 舒 廓簞 翮曖 ァ
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
                        System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
                        System.out.format("弛      House list      弛                                                    弛%n");
                        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
                        System.out.format("弛 %-20s 弛 %-50s 弛%n", "[House Name]", "[House Address]");

                        int i = 0;
                        int labelWidth = 20;
                        int textWidth = 50;

                        for (HouseDTO houseDTO : list) {
                            String houseLabel = String.format("%d. %s", ++i, houseDTO.getHouseName());
                            printFormatted(houseLabel, houseDTO.getHouseAddress(), labelWidth, textWidth);
                        }
                        System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");

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

                            System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
                            System.out.format("弛 House Information    弛 Details                                            弛%n");
                            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
                            printFormatted("Name", houseDTO.getHouseName(), firstColWidth, secondColWidth);
                            printFormatted("Address", houseDTO.getHouseAddress(), firstColWidth, secondColWidth);
                            printFormatted("Bedrooms", String.valueOf(houseDTO.getBedroom()), firstColWidth, secondColWidth);
                            printFormatted("Bathrooms", String.valueOf(houseDTO.getBathroom()), firstColWidth, secondColWidth);
                            printFormatted("Description", houseDTO.getHouseIntroduce(), firstColWidth, secondColWidth);

                            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
                            System.out.format("弛 Cost Details         戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
                            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
                            printFormatted("Weekday", String.valueOf(feePolicyDTO.getWeekday()), firstColWidth, secondColWidth);
                            printFormatted("Weekend", String.valueOf(feePolicyDTO.getWeekend()), firstColWidth, secondColWidth);

                            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
                            System.out.format("弛 Amenities            弛 Details                                            弛%n");
                            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");


                            String basicAmenities = amenitiesDTOList.stream()
                                    .filter(amenitiesDTO -> amenitiesDTO.getTypeId() == 1)
                                    .map(AmenitiesDTO::getAmenities)
                                    .collect(Collectors.joining(", "));

                            if (!basicAmenities.isEmpty()) {
                                printFormatted("Basic", basicAmenities, firstColWidth, secondColWidth);
                            } else {
                                System.out.format("弛 Basic                弛                                                    弛%n");
                            }


                            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");


                            String safetyAmenities = amenitiesDTOList.stream()
                                    .filter(amenitiesDTO -> amenitiesDTO.getTypeId() == 2)
                                    .map(AmenitiesDTO::getAmenities)
                                    .collect(Collectors.joining(", "));

                            if (!safetyAmenities.isEmpty()) {
                                printFormatted("Safety", safetyAmenities, firstColWidth, secondColWidth);
                            } else {
                                System.out.format("弛 Safety               弛                                                    弛%n");
                            }


                            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");


                            String accessibilityAmenities = amenitiesDTOList.stream()
                                    .filter(amenitiesDTO -> amenitiesDTO.getTypeId() == 3)
                                    .map(AmenitiesDTO::getAmenities)
                                    .collect(Collectors.joining(", "));

                            if (!accessibilityAmenities.isEmpty()) {
                                printFormatted("Accessibility", accessibilityAmenities, firstColWidth, secondColWidth);
                            } else {
                                System.out.format("弛 Accessibility        弛                                                    弛%n");
                            }

                            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
                            System.out.format("弛 Reviews              弛                                                    弛%n");
                            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
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
                            System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");

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
                    System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                    System.out.println("弛   Please Select (1) ~ (4)    弛");
                    System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
                    break;
            }
        }
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

    private int getCommand() {
        System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
        System.out.format("弛    <Guest Page>      弛                                                    弛%n");
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        printUserInfo();
        System.out.format("弛 1. FIND HOUSE        弛                                                    弛%n");
        System.out.format("弛 2. HOUSE LIST        弛                                                    弛%n");
        System.out.format("弛 3. MYPAGE            弛                                                    弛%n");
        System.out.format("弛 4. LOGOUT            弛                                                    弛%n");
        System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");

        System.out.print("enter : ");
        return MyIOStream.sc.nextInt();
    }

    private void printUserInfo() {
        System.out.format("弛 Name : %-13s 弛                                                    弛%n", userDTO.getUserName());
        System.out.format("弛 Role : %-13s 弛                                                    弛%n", userDTO.getRole());
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
    }
}
