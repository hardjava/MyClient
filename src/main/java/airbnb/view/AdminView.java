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
                System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
                System.out.format("弛                            <ADMIN PAGE>                                弛                                                                                                           弛%n");
                System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
                System.out.format("弛 1. Approval/Reject Host's Accommodation registration                   弛                                                                                                           弛%n");
                System.out.format("弛 2. Check current accommodation situation                               弛                                                                                                           弛%n");
                System.out.format("弛 0. Logout                                                              弛                                                                                                           弛%n");
                System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");

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
                        System.out.format("                                             忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
                        System.out.format("                                             弛                                 Invalid input. Please try again.                              弛%n");
                        System.out.format("                                             戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong Input..");
            }
        }
    }

    private void checkAccommodationSituation() throws Exception {
        //熨模 葬蝶お 轎溘
        AccommodationSituationController accommodationSituationController = new AccommodationSituationController();
        Protocol protocol = accommodationSituationController.listRequest();
        List<HouseDTO> houseDTOList = (List<HouseDTO>) protocol.getObject();
        System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
        System.out.format("弛                             <HOUSE LIST>                               弛                                                                                                           弛%n");
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        System.out.format("弛                             [HOUSE NAME]                               弛                                                [HOUSE ADDRESS]                                            弛%n");

        int i = 0;
        if (houseDTOList != null) {
            for (HouseDTO houseDTO : houseDTOList) {
                printFormatted(++i + ". " + houseDTO.getHouseName(), houseDTO.getHouseAddress(), firstColWidth, secondColWidth);
            }
        }
        System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");


        System.out.println("                                                           忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
        System.out.println("                                                           弛                   Select Accommodation number (back : -1)       弛");
        System.out.println("                                                           戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
        System.out.print("                                                                                     Selection : ");
        int accommodationNum = MyIOStream.sc.nextInt();

        if (accommodationNum > 0 && accommodationNum <= i) {
            //蹺陛 衛濛
            System.out.println("                                                           忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
            System.out.println("                                                           弛   1. Check monthly reservation status for each accommodation    弛");
            System.out.println("                                                           弛   2. Check total monthly sales for each accommodation           弛");
            System.out.println("                                                           戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
            System.out.print("                                                                                     Selection : ");
            int num = MyIOStream.sc.nextInt();

            if (num == 1) { // 賅萇 reservationDTOList 陛螳諦憮 鼻鷓縑 評塭 殖溘縑 ル衛п憮 嗥錶邀
                protocol = accommodationSituationController.monthlyReservationRequest(houseDTOList.get(accommodationNum - 1).getHouseId());
                MoreHouseInfoDTO moreHouseInfoDTO = (MoreHouseInfoDTO) protocol.getObject();
                List<ReservationDTO> reservationDTOList = moreHouseInfoDTO.getReservationDTOList();
                System.out.println("                                                                         忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                System.out.println("                                                                         弛        Select Month (1 ~ 12)         弛");
                System.out.println("                                                                         戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
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
                System.out.println("                                                                         忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                System.out.println("                                                                         弛        Select Month (1 ~ 12)         弛");
                System.out.println("                                                                         戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
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
        // �ˊ瘋挫� 熨模 蛔煙 褐羶и 葬蝶お 爾罹輿晦
        RequestHouseRegistrationController requestHouseRegistrationController = new RequestHouseRegistrationController();
        Protocol protocol = requestHouseRegistrationController.listRequest();
        List<HouseAndHostDTO> list = (List<HouseAndHostDTO>) protocol.getObject();
        System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
        System.out.format("弛                                                                               [ Discount Policy ]                                                                                       弛%n");
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式扣%n");
        System.out.format("弛%-60s弛%-80s弛%-20s弛%-15s       弛%n", "[House Name]", "[HouseAddress]", "[Host Name]", "[Host ID]");
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式扣%n");

        int i = 0;
        if (list != null) {
            for (HouseAndHostDTO houseAndHostDTO : list) {
                printFormattedForAccommodationRequests(++i + ". " + houseAndHostDTO.getHouseName(), houseAndHostDTO.getHouseAddress(), houseAndHostDTO.getHostName(), String.valueOf(houseAndHostDTO.getHostId()), 58, 78, 18, 20);
            }
        }
        System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式戎%n");
        System.out.println("                                                                         忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
        System.out.println("                                                                         弛  See More Detail Number [Back (-1)]  弛");
        System.out.println("                                                                         戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
        System.out.print("                                                                                      Select : ");

        int enter = MyIOStream.sc.nextInt();

        if (enter == -1) {
            System.out.println("Back..");
        } else if (enter > 0 && enter <= i) {
            // 熨模曖 鼻撮 薑爾 嗥辦晦
            protocol = requestHouseRegistrationController.detailInfoRequest(list.get(enter - 1).getHouseId());

            List<AmenitiesDTO> amenitiesDTOList = (List<AmenitiesDTO>) protocol.getObject();
            System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
            System.out.format("弛                                                                                    HOUSE DETAIL                                                                                    弛%n");
            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
            printFormatted("[Host Name]", list.get(enter - 1).getHostName(), firstColWidth, secondColWidth);
            printFormatted("[Host ID]", list.get(enter - 1).getLoginId(), firstColWidth, secondColWidth);
            printFormatted("[House Name]", list.get(enter - 1).getHouseName(), firstColWidth, secondColWidth);
            printFormatted("[House Address]", list.get(enter - 1).getHouseAddress(), firstColWidth, secondColWidth);
            printFormatted("[Bedroom Count]", String.valueOf(list.get(enter - 1).getBedroom()), firstColWidth, secondColWidth);
            printFormatted("[Bathroom Count]", String.valueOf(list.get(enter - 1).getBathroom()), firstColWidth, secondColWidth);
            printFormatted("[Capacity]", String.valueOf(list.get(enter - 1).getBedroom()), firstColWidth, secondColWidth);
            printFormatted("[House Type]", list.get(enter - 1).getHouseType().toString(), firstColWidth, secondColWidth);
            printFormatted("[House Info]", list.get(enter - 1).getHouseIntroduce(), firstColWidth, secondColWidth);


            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
            System.out.format("弛                           Basic Amenities                              弛                                                                                                           弛%n");
            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");

            if (list != null) {
                for (AmenitiesDTO amenitiesDTO : amenitiesDTOList) {
                    if (amenitiesDTO.getTypeId() == 1) {
                        printFormatted(amenitiesDTO.getAmenities(), "", firstColWidth, secondColWidth);
                    }
                }
            }
            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");

            System.out.format("弛                          Safety Amenities                              弛                                                                                                           弛%n");
            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");

            if (list != null) {
                for (AmenitiesDTO amenitiesDTO : amenitiesDTOList) {
                    if (amenitiesDTO.getTypeId() == 2) {
                        printFormatted(amenitiesDTO.getAmenities(), "", firstColWidth, secondColWidth);
                    }
                }
            }
            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");

            System.out.format("弛                       Accessibility Amenities                          弛                                                                                                           弛%n");
            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
            if (list != null) {
                for (AmenitiesDTO amenitiesDTO : amenitiesDTOList) {
                    if (amenitiesDTO.getTypeId() == 3) {
                        printFormatted(amenitiesDTO.getAmenities(), "", firstColWidth, secondColWidth);
                    }
                }
            }
            System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");

            System.out.println("                                                                          忙式式                                式式忖");
            System.out.println("                                                                          弛  (1) Approval (2) Reject (3) Back  弛");
            System.out.println("                                                                          戌式式                                式式戎");
            System.out.print("                                                                                     Selection : ");

            int command = MyIOStream.sc.nextInt();

            if (command == 3) {
                System.out.println("Back..");
            } else if (command == 1) {
                // 蝓檣
                protocol = requestHouseRegistrationController.approvalRequest(list.get(enter - 1).getHouseId());

                if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                    System.out.println("                                                                             忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                    System.out.println("                                                                             弛     Success to Approval      弛");
                    System.out.println("                                                                             戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");

                }

            } else if (command == 2) {
                protocol = requestHouseRegistrationController.rejectRequest(list.get(enter - 1).getHouseId());

                if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                    System.out.println("                                                                             忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                    System.out.println("                                                                             弛      Success to Reject       弛");
                    System.out.println("                                                                             戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");

                }
                // 剪瞰
            } else {
                System.out.println("Wrong Input..");
            }
        } else {
            System.out.println("Wrong Input..");
        }
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
        String[] labelWords = label.split(" ");
        String[] textWords = text.split(" ");
        ArrayList<String> formattedLabel = new ArrayList<>();
        StringBuilder labelLine = new StringBuilder();
        StringBuilder textLine = new StringBuilder();

        // 塭漣 還夥翎 籀葬
        for (String word : labelWords) {
            if (labelLine.length() + word.length() + 1 > labelWidth) { // 奢寥 んл
                formattedLabel.add(labelLine.toString().trim());
                labelLine.setLength(0);
            }
            labelLine.append(word).append(" ");
        }
        formattedLabel.add(labelLine.toString().trim()); // 葆雖虞 塭漣 還 蹺陛

        // 臢蝶お 還夥翎 塽 塭漣婁 л眷 轎溘
        int labelIndex = 0;
        for (String word : textWords) {
            if (textLine.length() + word.length() + 1 > textWidth) { // 奢寥 んл
                String currentLabel = labelIndex < formattedLabel.size() ? formattedLabel.get(labelIndex) : "";
                System.out.printf("弛 %-" + labelWidth + "s 弛 %-" + textWidth + "s 弛%n", currentLabel, textLine.toString().trim());
                textLine.setLength(0);
                labelIndex++;
            }
            textLine.append(word).append(" ");
        }
        // 臢蝶お曖 葆雖虞 還婁 陴擎 塭漣 還 轎溘
        while (labelIndex < formattedLabel.size() || textLine.length() > 0) {
            String currentLabel = labelIndex < formattedLabel.size() ? formattedLabel.get(labelIndex) : "";
            String currentText = textLine.toString().trim();
            System.out.printf("弛 %-" + labelWidth + "s 弛 %-" + textWidth + "s 弛%n", currentLabel, currentText);
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

            System.out.format("弛 %-" + firstWidth + "s 弛 %-" + secondWidth + "s 弛 %-" + thirdWidth + "s 弛 %-" + fourthWidth + "s 弛%n",
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
