package airbnb.view;

import airbnb.controller.AccommodationSituationController;
import airbnb.controller.RequestHouseRegistrationController;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class AdminView {
    private final UserDTO userDTO;
    private final int firstColWidth = 30; // First column width
    private final int secondColWidth = 60; // Second column width
    private final String leftAlignFormat = "| %-" + firstColWidth + "s | %-" + secondColWidth + "s |%n";

    public AdminView(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public void showView() throws IOException, ClassNotFoundException {
        for (; ; ) {
            System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
            System.out.format("弛   <Admin Page>         弛                                                    弛%n");
            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
            System.out.format("弛 1. Approval/Reject Host's Accommodation registration                        弛%n");
            System.out.format("弛 2. Check current accommodation situation                                    弛%n");
            System.out.format("弛 0. Logout              弛                                                    弛%n");
            System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");

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
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void checkAccommodationSituation() throws IOException, ClassNotFoundException {
        //熨模 葬蝶お 轎溘
        AccommodationSituationController accommodationSituationController = new AccommodationSituationController();
        Protocol protocol = accommodationSituationController.listRequest();
        List<HouseDTO> houseDTOList = (List<HouseDTO>) protocol.getObject();
        System.out.println("[List]");
        int i = 0;
        if (houseDTOList != null) {
            for (HouseDTO houseDTO : houseDTOList) {
                System.out.println(++i + ". " + houseDTO.toString());
            }
        }

        System.out.print("Select Accommodation number : ");
        int accommodationNum = MyIOStream.sc.nextInt();

        if (accommodationNum > 0 && accommodationNum <= i) {
            protocol = accommodationSituationController.monthlyReservationRequest(houseDTOList.get(accommodationNum - 1).getHouseId());
            List<ReservationDTO> reservationDTOList = (List<ReservationDTO>) protocol.getObject();
            System.out.print("Select Month (1 ~ 12) : ");
            int enterMonth = MyIOStream.sc.nextInt();

            if (enterMonth >= 1 && enterMonth <= 12) {
                System.out.printf("%-10s%-5s%-20s%-20s%-20s%-20s%-20s\n", "userId", "guestNum", "reservationDate", "checkIn", "checkOut", "cost", "reservationStatus");
                int total = 0;
                for (ReservationDTO reservationDTO : reservationDTOList) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(reservationDTO.getCheckIn());

                    int reservationMonth = calendar.get(Calendar.MONTH) + 1;
                    if (enterMonth == reservationMonth) {
                        System.out.println(reservationDTO.toString());
                        total += reservationDTO.getCost();
                    }
                }
                System.out.println("Total : " + total);
            } else {
                System.out.println("Wrong Input..");
            }


//            CalendarViewer.selectMonth(reservationDTOList);
        } else {
            System.out.println("Wrong Input..");
        }
    }

    private void manageAccommodationRequests() throws IOException, ClassNotFoundException {
        // �ˊ瘋挫� 熨模 蛔煙 褐羶и 葬蝶お 爾罹輿晦
        RequestHouseRegistrationController requestHouseRegistrationController = new RequestHouseRegistrationController();
        Protocol protocol = requestHouseRegistrationController.listRequest();
        List<HouseAndHostDTO> list = (List<HouseAndHostDTO>) protocol.getObject();
        System.out.println("[List]");
        System.out.printf("  %-50s%-80s%-10s%-20s\n", "[House Name]", "[HouseAddress]", "[Host Name]", "[Host ID]");
        int i = 0;
        if (list != null) {
            for (HouseAndHostDTO houseAndHostDTO : list) {
                System.out.println(++i + ". " + houseAndHostDTO.toString());
            }
        }

        System.out.print("\nSee More Detail Number (Back (-1)) : ");
        int enter = MyIOStream.sc.nextInt();

        if (enter == -1) {
            System.out.println("Back..");
        } else if (enter > 0 && enter <= i) {
            // 熨模曖 鼻撮 薑爾 嗥辦晦
            protocol = requestHouseRegistrationController.detailInfoRequest(list.get(enter - 1).getHouseId());

            List<AmenitiesDTO> amenitiesDTOList = (List<AmenitiesDTO>) protocol.getObject();
            System.out.println("[Host Name] : " + list.get(enter - 1).getHostName());
            System.out.println("[Host Id] : " + list.get(enter - 1).getLoginId());
            System.out.println("[House Name] : " + list.get(enter - 1).getHouseName());
            System.out.println("[House Address] : " + list.get(enter - 1).getHouseAddress());
            System.out.println("[Bedroom Count] : " + list.get(enter - 1).getBedroom());
            System.out.println("[Bathroom Count] : " + list.get(enter - 1).getBathroom());
            System.out.println("[Capacity] : " + list.get(enter - 1).getBedroom());
            System.out.println("[House Type] : " + list.get(enter - 1).getHouseType().toString());
            System.out.println("[House Info] : " + list.get(enter - 1).getHouseIntroduce());
            System.out.println("[Basic Amenities]");
            if (list != null) {
                for (AmenitiesDTO amenitiesDTO : amenitiesDTOList) {
                    if (amenitiesDTO.getTypeId() == 1) {
                        System.out.println(amenitiesDTO.getAmenities());
                    }
                }
            }

            System.out.println("[Safety Amenities]");
            if (list != null) {
                for (AmenitiesDTO amenitiesDTO : amenitiesDTOList) {
                    if (amenitiesDTO.getTypeId() == 2) {
                        System.out.println(amenitiesDTO.getAmenities());
                    }
                }
            }

            System.out.println("[Accessibility Amenities]");
            if (list != null) {
                for (AmenitiesDTO amenitiesDTO : amenitiesDTOList) {
                    if (amenitiesDTO.getTypeId() == 3) {
                        System.out.println(amenitiesDTO.getAmenities());
                    }
                }
            }

            System.out.print("(1) Approval (2) Reject (3) Back : ");
            int command = MyIOStream.sc.nextInt();

            if (command == 3) {
                System.out.println("Back..");
            } else if (command == 1) {
                // 蝓檣
                protocol = requestHouseRegistrationController.approvalRequest(list.get(enter - 1).getHouseId());

                if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                    System.out.println("Success to Approval");
                }

            } else if (command == 2) {
                protocol = requestHouseRegistrationController.rejectRequest(list.get(enter - 1).getHouseId());

                if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                    System.out.println("Success to Reject");
                }
                // 剪瞰
            } else {
                System.out.println("Wrong Input..");
            }
        } else {
            System.out.println("Wrong Input..");
        }


//        System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
//        System.out.format("弛 1. Pending Accommodations      弛                                                              弛%n");
//        System.out.format("弛 2. Rejected Accommodations     弛                                                              弛%n");
//        System.out.format("弛 3. Go Back                     弛                                                              弛%n");
//        System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");
//        System.out.print("Enter command: ");

//        int choice = sc.nextInt();
//        switch (choice) {
//            case 1:
//                 嘐蝓檣 熨夢 衛撲 跡煙
//                System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
//                System.out.format("弛 Unapproved Accommodation List  弛                                                              弛%n");
//                System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
//                printFormatted("Line", "", firstColWidth, secondColWidth);
//                 嬪 printFormatted蒂 檜辨п憮 Line縑 DTO縑憮 陛螳螞勘 厥堅 舒廓簞睡攪朝 鱔橾
//                System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");
//                System.out.println("Please select the unapproved accommodation : ");
//
//                System.out.println("忙式式                          式式忖");
//                System.out.println("弛 (1) Approval  (2) Rejection  弛");
//                System.out.println("戌式式                          式式戎");
//                System.out.println("          Selection : ");
//
//                 葬蝶お機 熨夢 衛撲 醞 摹鷗ж賊 "蝓檣" "剪瞰" 醞 摹鷗ж堅 詭衛雖 轎溘
//                System.out.println("Accommodation request from ~~ has been approved.");
//                System.out.println("Accommodation request from ~~ has been rejected.");
//                break;
//            case 2:
//                 剪瞰脹 熨夢 衛撲 跡煙
//                System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
//                System.out.format("弛  Rejected Accommodation List   弛                                                              弛%n");
//                System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
//                printFormatted("Line", "", firstColWidth, secondColWidth);
//                 嬪 printFormatted蒂 檜辨п憮 Line縑 DTO縑憮 陛螳螞勘 厥堅 舒廓簞睡攪朝 鱔橾
//                System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");
//                System.out.println("Press Any Key : ");
//
//            case 0:
//                return; // 菴煎陛晦
//            default:
//                System.out.println("Invalid input. Please try again.");
//        }
//    }
    }

    private void viewMonthlyReservationStatus() {
        // 摹鷗ж賊 蛔煙脹 熨模菟檜 葬蝶お機 腎堅 熨模蒂 摹鷗ж賊 斜 熨模縑 渠и 蕨擒 ⑷�窕� 殖溘戲煎 轎溘 calender() 霤堅
        System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
        System.out.format("弛 Registered Accommodations List 弛                                                              弛%n");
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        printFormatted("Line", "", firstColWidth, secondColWidth);
        // 嬪 printFormatted蒂 檜辨п憮 Line縑 DTO縑憮 陛螳螞勘 厥堅 舒廓簞睡攪朝 鱔橾
        System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");
        System.out.println("Please select the accommodation : ");

        System.out.println("Enter the year and month to check(YYYY MM): ");
    }

    private void viewMonthlyRevenue() {
        System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
        System.out.format("弛 Registered Accommodations List 弛                                                              弛%n");
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        printFormatted("Line", "", firstColWidth, secondColWidth);
        // 嬪 printFormatted蒂 檜辨п憮 Line縑 DTO縑憮 陛螳螞勘 厥堅 舒廓簞睡攪朝 鱔橾
        System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");

        System.out.println("Please select the accommodation : ");

        System.out.println("Enter the year and month to check(YYYY MM): ");
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
    // 熨模 跡煙擊 碳楝螃朝 詭憮萄 蛔 蹺陛瞳戲煎 в蹂й 匙戲煎 蕨鼻
}
