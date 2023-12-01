package airbnb.view;

import airbnb.ReservationRequestController;
import airbnb.controller.AmenitiesRequestController;
import airbnb.controller.SearchAllHouseController;
import airbnb.controller.SearchHouseController;
import airbnb.controller.SearchMoreHouseInfoController;
import airbnb.network.HouseType;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.*;

import java.util.ArrayList;
import java.util.List;

public class GuestView {
    private final UserDTO userDTO;
    private final int firstColWidth = 20; // 첫 번째 열의 폭
    private final int secondColWidth = 50; // 두 번째 열의 폭
    private final String leftAlignFormat = "| %-" + firstColWidth + "s | %-" + secondColWidth + "s |%n";

    public GuestView(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public void showView() throws Exception {
        for (; ; ) {
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
                    System.out.println("┌──────────────────────────────┐");
                    System.out.println("│   Please Select (1) ~ (4)    │");
                    System.out.println("└──────────────────────────────┘");
                    break;
            }
        }

    }

    private void houseFiltering() throws Exception {
        String houseName, checkIn, checkOut = "";
        int guestNum = 0;
        MyIOStream.sc.nextLine(); // Buffer Clear
        System.out.print("Enter House Name (if not Enter) : ");
        houseName = MyIOStream.sc.nextLine();
        System.out.print("Enter CheckIn (YYYY-MM-DD) (if not Enter) : ");
        checkIn = MyIOStream.sc.nextLine();
        if (!checkIn.equals("")) {
            System.out.print("Enter CheckOut (YYYY-MM-DD) (if not Enter) : ");
            checkOut = MyIOStream.sc.nextLine();
        }
        System.out.print("Enter Guest Num : ");
        guestNum = MyIOStream.sc.nextInt();
        System.out.print("Enter House Type (1) private (2) public : ");
        int houseType = MyIOStream.sc.nextInt();
        if (houseType == 1 || houseType == 2) {
            // Amenities List 출력
            List<String> amenitiesList = new ArrayList<>();
            AmenitiesRequestController amenitiesRequestController = new AmenitiesRequestController();
            Protocol protocol = amenitiesRequestController.basicAmenitiesListRequest();
            System.out.println("\t\t[Basic Amenities List]");

            if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                int basicNum = 0;
                List<AmenitiesDTO> list = (List<AmenitiesDTO>) protocol.getObject();
                for (AmenitiesDTO amenitiesDTO : list) {
                    System.out.println("\t" + ++basicNum + ". " + amenitiesDTO.getAmenities());
                }
                System.out.print("Enter (separated by commas) (If not, enter) : ");
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
            System.out.println("\t\t[Safety Amenities List]");

            if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                List<AmenitiesDTO> list = (List<AmenitiesDTO>) protocol.getObject();
                int safetyNum = 0;
                for (AmenitiesDTO amenitiesDTO : list) {
                    System.out.println("\t" + ++safetyNum + ". " + amenitiesDTO.getAmenities());
                }
                System.out.print("Enter (separated by commas) (If not, enter) : ");
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
            System.out.println("\t\t[Accessibility Amenities List]");

            if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                List<AmenitiesDTO> list = (List<AmenitiesDTO>) protocol.getObject();
                int accessNum = 0;
                for (AmenitiesDTO amenitiesDTO : list) {
                    System.out.println("\t" + ++accessNum + ". " + amenitiesDTO.getAmenities());
                }
                System.out.print("Enter (separated by commas) (If not, enter) : ");
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
                System.out.print("See More Info : ");
                int index = MyIOStream.sc.nextInt();

                if (index > 0 && index <= houseAndFeeDTOS.size()) {
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
        // 라벨과 텍스트를 받아 포맷에 맞게 출력하는 메소드
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        System.out.printf("│ %-" + labelWidth + "s │ ", label);

        for (String word : words) {
            if (line.length() + word.length() > textWidth) {
                System.out.printf("%-" + textWidth + "s │%n│ %" + labelWidth + "s │ ", line.toString(), "");
                line.setLength(0);
            }
            line.append(word).append(" ");
        }

        System.out.printf("%-" + textWidth + "s │%n", line.toString());
    }

    private int getCommand() {
        System.out.format("┌──────────────────────┬────────────────────────────────────────────────────┐%n");
        System.out.format("│    <Guest Page>      │                                                    │%n");
        System.out.format("├──────────────────────┼────────────────────────────────────────────────────┤%n");
        printUserInfo();
        System.out.format("│ 1. FIND HOUSE        │                                                    │%n");
        System.out.format("│ 2. HOUSE LIST        │                                                    │%n");
        System.out.format("│ 3. MYPAGE            │                                                    │%n");
        System.out.format("│ 4. LOGOUT            │                                                    │%n");
        System.out.format("└──────────────────────┴────────────────────────────────────────────────────┘%n");
        System.out.print("enter : ");
        return MyIOStream.sc.nextInt();
    }

    private void printUserInfo() {
        System.out.format("│ Name : %-13s │                                                    │%n", userDTO.getUserName());
        System.out.format("│ Role : %-13s │                                                    │%n", userDTO.getRole());
        System.out.format("├──────────────────────┼────────────────────────────────────────────────────┤%n");
    }

    private int getHouseListCommand() {
        System.out.print("(1) Ascending (2) Descending (3) Reservation (4) Back : ");

        return MyIOStream.sc.nextInt();
    }

    private void houseAllList() throws Exception {
        SearchAllHouseController searchAllHouseController = new SearchAllHouseController();
        Protocol protocol = searchAllHouseController.allHouseRequest();

        List<HouseAndFeeDTO> allList = (List<HouseAndFeeDTO>) protocol.getObject();
        System.out.println("[All List]");
        if (allList != null) {
            printHouseList(allList);
            int listCommand = getHouseListCommand();

            if (listCommand == 4) {
                System.out.println("Back..");
            } else {
                switch (listCommand) {
                    case 1:
                        // 오름차순
                        printAscendingList(searchAllHouseController);
                        break;
                    case 2:
                        // 내림차순
                        printDescendingList(searchAllHouseController);
                        break;
                    case 3:
                        System.out.print("Enter Num to Reservation (Back : -1): ");
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
        // 상세정보 보여주기

        System.out.println("[Detail Info]");
        System.out.println("[House Name] : " + houseAndFeeDTO.getHouseName());
        System.out.println("[House Address] : " + houseAndFeeDTO.getHouseAddress());
        System.out.println("[Bedroom Count] : " + houseAndFeeDTO.getBedroom());
        System.out.println("[Bathroom Count] : " + houseAndFeeDTO.getBathroom());
        System.out.println("[Capacity] : " + houseAndFeeDTO.getBedroom());
        System.out.println("[House Type] : " + houseAndFeeDTO.getHouseType().toString());
        System.out.println("[Weekday Cost] : " + houseAndFeeDTO.getWeekday());
        System.out.println("[Weekend Cost] : " + houseAndFeeDTO.getWeekend());
        System.out.println("[House Info] : " + houseAndFeeDTO.getHouseIntroduce());
        List<AmenitiesDTO> amenitiesList = moreHouseInfoDTO.getAmenitiesDTOList();
        System.out.println("\t\t[Basic Amenities]");
        if (amenitiesList != null) {
            for (AmenitiesDTO amenitiesDTO : amenitiesList) {
                if (amenitiesDTO.getTypeId() == 1) {
                    System.out.println(amenitiesDTO.getAmenities());
                }
            }
        }
        System.out.println("\t\t[Safety Amenities]");
        if (amenitiesList != null) {
            for (AmenitiesDTO amenitiesDTO : amenitiesList) {
                if (amenitiesDTO.getTypeId() == 2) {
                    System.out.println(amenitiesDTO.getAmenities());
                }
            }
        }
        System.out.println("\t\t[Accessibility Amenities]");
        if (amenitiesList != null) {
            for (AmenitiesDTO amenitiesDTO : amenitiesList) {
                if (amenitiesDTO.getTypeId() == 3) {
                    System.out.println(amenitiesDTO.getAmenities());
                }
            }
        }
        List<UserReviewDTO> userReviewDTOS = moreHouseInfoDTO.getReviewDTOList();
        System.out.println("\t\t[Review]");
        if (userReviewDTOS != null) {
            for (UserReviewDTO userReviewDTO : userReviewDTOS) {
                System.out.println("<Guest>");
                System.out.println(userReviewDTO.toString());
                if (replyDTOList != null) {
                    for (ReplyDTO replyDTO : replyDTOList) {
                        if (replyDTO.getReservationId() == userReviewDTO.getReservationId()) {
                            System.out.println("<Host>");
                            System.out.println(replyDTO);
                        }
                    }
                }
            }
        }

        System.out.print("(1) Reservation (2) Back : ");
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
        System.out.println("[Show Reservation available dates]");

        if (reservationDTOList != null) {
//           MyCalender.print(reservationDTOList);
            CalendarViewerForAdmin.run(reservationDTOList, houseAndFeeDTO);

        } else {

            System.out.println("All Date is Possible to Reservation");
        }

        System.out.print("Adult Num : ");
        int adultNum = MyIOStream.sc.nextInt();
        System.out.print("Child Num : ");
        int childNum = MyIOStream.sc.nextInt();
        System.out.print("Enter CheckIn Day (YYYY-MM-DD) : ");
        String checkIn = MyIOStream.sc.next();
        System.out.print("Enter CheckOut Day (YYYY-MM-DD) : ");
        String checkOut = MyIOStream.sc.next();
        int totalNum = adultNum + childNum;

        int cost = 0;

        if (discountRate > 0) {
            cost = SaleCalculator.CalculateRate(checkIn, checkOut, discountPolicyDTO, feePolicyDTO, totalNum);
        } else {
            cost = SaleCalculator.CalculateAmount(checkIn, checkOut, discountPolicyDTO, feePolicyDTO, totalNum);
        }

        System.out.println("Cost = " + cost + "$");
        System.out.print("Would you like to make a reservation? (1) Yes! (2) No! : ");
        int enter = MyIOStream.sc.nextInt();

        if (enter == 1) {
            ReservationRequestController reservationRequestController = new ReservationRequestController();
            Protocol protocol = reservationRequestController.reservationRequest(houseAndFeeDTO.getHouseId(), userDTO.getUserId(), totalNum, checkIn, checkOut, 0);

            if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                System.out.println("Success to Reservation");
            } else {
                System.out.println(protocol.getObject());
            }
        } else if (enter == 2) {
            System.out.println("Cancel..");
        } else {
            System.out.println("Wrong Input..");
        }
    }

    private void printDescendingList(SearchAllHouseController searchAllHouseController) throws
            Exception {
        Protocol protocol = searchAllHouseController.descendingHouseRequest();
        List<HouseAndFeeDTO> descendingList = (List<HouseAndFeeDTO>) protocol.getObject();
        System.out.println("[Descending List]");

        if (descendingList != null) {
            printHouseList(descendingList);

            int listCommand = getHouseListCommand();

            if (listCommand == 4) {
                System.out.println("Back..");
            } else {
                switch (listCommand) {
                    case 1:
                        // 오름차순
                        printAscendingList(searchAllHouseController);
                        break;
                    case 2:
                        // 내림차순
                        printDescendingList(searchAllHouseController);
                        break;
                    case 3:
                        System.out.print("Enter Num to Reservation (Back : -1): ");
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
        System.out.println("[Ascending List]");

        if (ascendingList != null) {
            printHouseList(ascendingList);

            int listCommand = getHouseListCommand();

            if (listCommand == 4) {
                System.out.println("Back..");
            } else {
                switch (listCommand) {
                    case 1:
                        // 오름차순
                        printAscendingList(searchAllHouseController);
                        break;
                    case 2:
                        // 내림차순
                        printDescendingList(searchAllHouseController);
                        break;
                    case 3:
                        System.out.print("Enter Num to Reservation (Back : -1): ");
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
        for (HouseAndFeeDTO houseAndFeeDTO : houseList) {
            System.out.printf("%d. %s\n", ++i, houseAndFeeDTO.toString());
        }
    }
}
