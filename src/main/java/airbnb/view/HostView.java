package airbnb.view;

import airbnb.controller.*;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.network.Status;
import airbnb.persistence.dto.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HostView {
    private final UserDTO userDTO;
    private final int firstColWidth = 70; // 羅 廓簞 翮曖 ァ
    private final int secondColWidth = 105; // 舒 廓簞 翮曖 ァ

    public HostView(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public void showView() throws IOException, ClassNotFoundException {
        for (; ; ) {
            int command = getCommand();

            if (command == 0) {
                System.out.println("Log out..");
                break;
            }

            switch (command) {
                case 1:
                    registerAccommodation();
                    break;
                case 2:
                    setCost();
                    break;
                case 3:
                    setDiscountPolicy();
                    break;
                case 4:
                    showReservationStatus();
                    break;
                case 5:
                    manageReservations();
                    break;
                case 6:
                    manageReviews();
                    break;
                default:
                    System.out.println("Wrong Input..");
            }
        }
    }

    private void manageReviews() throws IOException, ClassNotFoundException {
        System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
        System.out.format("弛                          Unreviewed List                               弛                                                                                                           弛%n");
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");

        ReviewRequestController reviewRequestController = new ReviewRequestController();
        Protocol protocol = reviewRequestController.listRequest(userDTO);
        int i = 0;
        List<ReviewCheckDTO> reviewCheckDTOList = (List<ReviewCheckDTO>) protocol.getObject();
        if (reviewCheckDTOList != null) {
            for (ReviewCheckDTO reviewCheckDTO : reviewCheckDTOList) {
//                System.out.println("(" + ++i + ")\n" + reviewCheckDTO.toString());
                printFormatted("(" + ++i + ") " + reviewCheckDTO.getHouseName(), "Guest Name : " + reviewCheckDTO.getUserDTO().getUserName(), firstColWidth, secondColWidth);
                printFormatted("", reviewCheckDTO.getReviewDTO().toString(), firstColWidth, secondColWidth);
                printFormatted("", reviewCheckDTO.getReviewDTO().getReview(), firstColWidth, secondColWidth);
            }
            System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");

            System.out.format("                                             忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
            System.out.format("                                             弛                   The accommodation number that you want to reply to (Back -1)                弛%n");
            System.out.format("                                             戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");
            System.out.print("                                                                                      Enter : ");

            int enter = MyIOStream.sc.nextInt();

            if (enter == -1) {
                System.out.println("Back..");
            } else if (enter > 0 && enter <= i) {
                System.out.format("                                             忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
                System.out.format("                                             弛                              Please write the content of your reply                           弛%n");
                System.out.format("                                             戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");
                System.out.print("                                                Enter : ");
                MyIOStream.sc.nextLine(); // Buffer Clear
                String text = MyIOStream.sc.nextLine();

                System.out.println("                                                                             [     Do you want to reply?    ]");
                System.out.println("                                                                             忙式式                          式式忖");
                System.out.println("                                                                             弛       (1) Yes   (2) No       弛");
                System.out.println("                                                                             戌式式                          式式戎");
                System.out.print("                                                                                       Selection : ");

                int check = MyIOStream.sc.nextInt();

                if (check == 1) {
                    ReviewDTO reviewDTO = reviewCheckDTOList.get(enter - 1).getReviewDTO();
                    protocol = reviewRequestController.writeReviewRequest(reviewDTO.getReservationId(), userDTO.getLoginId(), userDTO.getUserName(), text);

                    if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                        System.out.println("                                                                             忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                        System.out.println("                                                                             弛   Success To Write Reply!    弛");
                        System.out.println("                                                                             戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
                    } else if (protocol.getProtocolCode() == Protocol.CODE_ERROR) {
                        System.out.println(protocol.getObject());
                    }
                    // 港旋 殖晦
                } else if (check == 2) {
                    System.out.println("                                                                             忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                    System.out.println("                                                                             弛            Cancel            弛");
                    System.out.println("                                                                             戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");

                    // 港旋 寰殖晦
                } else {
                    System.out.println("Wrong Input..");
                }
                // 葬箔 婦葬 煎霜
            } else {
                System.out.println("Wrong Input..");
            }
        } else {
            System.out.println("                                                                             忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
            System.out.println("                                                                             弛  Not Exist Unreviewed List   弛");
            System.out.println("                                                                             戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");

        }
    }

    private void manageReservations() throws IOException, ClassNotFoundException {
        // 蕨擒 蝓檣 渠晦 葬蝶お
        ReservationAllowOrRefuseController reservationAllowOrRefuseController = new ReservationAllowOrRefuseController();
        Protocol protocol = reservationAllowOrRefuseController.listRequest(userDTO);
        // 蕨擒 蝓檣 渠晦 熨模 葬蝶お 嗥辦晦
        int i = 0;
        System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
        System.out.format("弛                Waiting list for reservation approval                   弛                                                                                                           弛%n");
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
//
//        System.out.printf("  %-30s%-15s%-15s%-15s%-15s%-15s%-30s%-30s\n", "[House Name]", "[Check In]"
//                , "[Check Out]", "[Guest Num]", "[User Name]", "[User Phone]", "[ID]", "[Cost]");
        List<HouseAndReservationDTO> list = (List<HouseAndReservationDTO>) protocol.getObject();
        if (list != null) {
            for (HouseAndReservationDTO houseAndReservationDTO : list) {
//                System.out.println(++i + ". " + houseAndReservationDTO.toString());
                printFormatted("[" + (++i) + "] " + "[House Name] " + houseAndReservationDTO.getHouseName(), "[ID] " + houseAndReservationDTO.getLoginId(), firstColWidth, secondColWidth);
                printFormatted("", "[User Name] " + houseAndReservationDTO.getUserName(), firstColWidth, secondColWidth);
                printFormatted("", "[User Phone] " + houseAndReservationDTO.getUserPhone(), firstColWidth, secondColWidth);
                printFormatted("", "[Check In] " + houseAndReservationDTO.getCheckIn(), firstColWidth, secondColWidth);
                printFormatted("", "[Check Out] " + houseAndReservationDTO.getCheckOut(), firstColWidth, secondColWidth);
                printFormatted("", "[Guest Num] " + houseAndReservationDTO.getGuestNum(), firstColWidth, secondColWidth);
                printFormatted("", "[Cost] " + houseAndReservationDTO.getCost(), firstColWidth, secondColWidth);
                System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
            }
            System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");
        }
        System.out.println("                                                                     忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
        System.out.println("                                                                     弛                 1. APPROVAL               弛");
        System.out.println("                                                                     弛                 2. REJECT                 弛");
        System.out.println("                                                                     弛                 3. BACK                   弛");
        System.out.println("                                                                     戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
        System.out.print("                                                                                     Selection : ");

        int enter = MyIOStream.sc.nextInt();

        if (enter == 3) {
            System.out.println("Back..");
        } else if (enter == 1) {
            // 蝓檣й 匙 殮溘嫡晦
            System.out.println("                                                                     忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
            System.out.println("                                                                     弛   Which accommodations will you approve?  弛");
            System.out.println();
            System.out.print("                                                                                     Enter : ");
            int approvalNumber = MyIOStream.sc.nextInt();
            System.out.println("                                                                     戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");

            if (approvalNumber > 0 && approvalNumber <= i) {
                protocol = reservationAllowOrRefuseController.statusChangeRequest(list.get(approvalNumber - 1).getReservationId(), Status.BEFORE_STAY);

                if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                    System.out.println("                                                                     忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                    System.out.println("                                                                     弛           Success!           弛");
                    System.out.println("                                                                     戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
                } else {
                    System.out.println("                                                                     忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                    System.out.println("                                                                     弛            Error!            弛");
                    System.out.println("                                                                     戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");

                }

            } else {
                System.out.println("Wrong Input..");
            }


        } else if (enter == 2) {
            // 剪瞰й 匙 殮溘嫡晦
            System.out.println("                                                                     忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
            System.out.println("                                                                     弛   Which accommodations will you reject?   弛");
            System.out.println();
            System.out.print("                                                                                     Enter : ");
            int rejectNumber = MyIOStream.sc.nextInt();
            System.out.println("                                                                     戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");

            if (rejectNumber > 0 && rejectNumber <= i) {
                protocol = reservationAllowOrRefuseController.statusChangeRequest(list.get(rejectNumber - 1).getReservationId(), Status.REFUSE);

                if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                    System.out.println("                                                                     忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                    System.out.println("                                                                     弛           Success!           弛");
                    System.out.println("                                                                     戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
                } else {
                    System.out.println("                                                                     忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                    System.out.println("                                                                     弛            Error!            弛");
                    System.out.println("                                                                     戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
                }

            } else {
                System.out.println("Wrong Input..");
            }

        } else {
            System.out.println("Wrong Input..");
        }
    }

    private void showReservationStatus() throws IOException, ClassNotFoundException { // 熨夢 蕨擒 ⑷�� 爾晦
        // 蛔煙脹 熨模 褻��
        SearchHostReservationController searchHostReservationController = new SearchHostReservationController();
        Protocol protocol = searchHostReservationController.houseListRequest(userDTO);
        List<HouseDTO> list = (List<HouseDTO>) protocol.getObject();
        System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
        System.out.format("弛                           Approved List                                弛                                                                                                           弛%n");
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        if (list != null) {
            int i = 0;
            int labelWidth = 70; // Adjust as needed
            int textWidth = 105; // Adjust as needed

            for (HouseDTO houseDTO : list) {
                String houseLabel = String.format("%d. %s", ++i, houseDTO.getHouseName());
                printFormatted(houseLabel, "", labelWidth, textWidth); // Modify this line to match your data structure
            }
            System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");

            System.out.format("                                             忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
            System.out.format("                                             弛                 Please enter the number of the accommodation you want to search               弛%n");
            System.out.format("                                             戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");
            System.out.print("                                                Enter : ");


            int enter = MyIOStream.sc.nextInt();

            if (enter > 0 && enter <= i) {
                protocol = searchHostReservationController.reservationListRequest(list.get(enter - 1));
                List<ReservationDTO> reservationDTOList = (List<ReservationDTO>) protocol.getObject();
                System.out.println("                                                                       忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                System.out.println("                                                                       弛             Reservation Status            弛");
                System.out.println("                                                                       戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");

                if (reservationDTOList != null) {
                    CalendarViewerForAdmin_host.run(reservationDTOList, list.get(enter -1).getBedroom(), list.get(enter - 1).getHouseType());
                }
            } else {
                System.out.println("Wrong Input..");
            }
        } else {
            System.out.println("No List..");
        }
    }

    // 蹺陛瞳檣 詭憮萄煎 熨夢 蛔煙擊 嬪и registerAccommodation() 詭憮萄
    private void registerAccommodation() throws IOException, ClassNotFoundException {
        System.out.format("                                                    忙式式式                                                                     式式式忖%n");
        System.out.format("                                                    弛                           <Register Accommodation>                        弛%n");
        System.out.format("                                                                                                                                 %n");

        System.out.print("                                                                       House Name : ");
        MyIOStream.sc.nextLine(); // 幗ぷ 綠辦晦
        String houseName = MyIOStream.sc.nextLine();
        System.out.print("                                                                       House Address : ");
        String houseAddress = MyIOStream.sc.nextLine();
        System.out.println("                                                                     忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
        System.out.println("                                                                     弛             Accommodation Type            弛");
        System.out.println("                                                                     戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
        System.out.println("                                                                     弛    (1) Private room   (2) Entire space    弛");
        System.out.println("                                                                     戌式式                                       式式戎");
        System.out.print("                                                                                     Selection : ");
        int roomType = MyIOStream.sc.nextInt();

        System.out.print("                                                                       Bathroom Count : ");
        int bathroomCount = MyIOStream.sc.nextInt();

        System.out.print("                                                                       Bedroom Count : ");
        int bedroomCount = MyIOStream.sc.nextInt();

        System.out.print("                                                                       Accommodation Info : ");
        MyIOStream.sc.nextLine(); // 幗ぷ 綠辦晦
        String info = MyIOStream.sc.nextLine();

        System.out.format("                                                      戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");

        System.out.format("                                                      忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
        System.out.format("                                                      戍式式式式式式式式式式式式式式式式式式式式式式式[ Amenities Register ]式式式式式式式式式式式式式式式式式式式式式式式式弛%n");
        System.out.format("                                                      戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        List<AmenitiesDTO> amenitiesDTOList = new ArrayList<>();
        AmenitiesRequestController amenitiesRequestController = new AmenitiesRequestController();
        System.out.format("                                                      戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        System.out.format("                                                      弛                           Basic Amenities                           弛%n");
        System.out.format("                                                      弛                      (If don't have, enter -1)                      弛%n");
        System.out.format("                                                      戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        Protocol protocol = amenitiesRequestController.basicAmenitiesListRequest();

        if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
            int basicNum = 0;
            List<AmenitiesDTO> list = (List<AmenitiesDTO>) protocol.getObject();
            for (AmenitiesDTO amenitiesDTO : list) {
                System.out.format("                                                      弛 %-67s 弛%n", ++basicNum + ". " + amenitiesDTO.getAmenities());
            }
            System.out.format("                                                      戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");

            System.out.print("                                                      Enter (separated by commas) : ");
            String basicAmenities = MyIOStream.sc.nextLine();
            if (!basicAmenities.equals("-1")) {
                String[] basicArr = basicAmenities.split(",");
                for (String s : basicArr) {
                    int n = Integer.parseInt(s);
                    if (n > 0 && n <= list.size()) {
                        amenitiesDTOList.add(list.get(n - 1));
                    }
                }
            }
        }
        System.out.format("                                                      忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
        System.out.format("                                                      弛                           Safety Amenities                          弛%n");
        System.out.format("                                                      弛                       (If don't have, enter -1)                     弛%n");
        System.out.format("                                                      戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        protocol = amenitiesRequestController.safetyAmenitiesListRequest();
        if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
            List<AmenitiesDTO> list = (List<AmenitiesDTO>) protocol.getObject();
            int safetyNum = 0;
            for (AmenitiesDTO amenitiesDTO : list) {
                System.out.format("                                                      弛 %-67s 弛%n", ++safetyNum + ". " + amenitiesDTO.getAmenities());
            }
            System.out.format("                                                      戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");
            System.out.print("                                                      Enter (separated by commas) : ");
            String safetyAmenities = MyIOStream.sc.nextLine();
            if (!safetyAmenities.equals("-1")) {
                String[] safetyArr = safetyAmenities.split(",");
                for (String s : safetyArr) {
                    int n = Integer.parseInt(s);
                    if (n > 0 && n <= list.size()) {
                        amenitiesDTOList.add(list.get(n - 1));
                    }
                }
            }
        }
        System.out.format("                                                      忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
        System.out.format("                                                      弛                        Accessibility Amenities                      弛%n");
        System.out.format("                                                      弛                       (If don't have, enter -1)                     弛%n");
        System.out.format("                                                      戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        protocol = amenitiesRequestController.accessAmenitiesListRequest();
        if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
            List<AmenitiesDTO> list = (List<AmenitiesDTO>) protocol.getObject();
            int accessNum = 0;
            for (AmenitiesDTO amenitiesDTO : list) {
                System.out.format("                                                      弛 %-67s 弛%n", ++accessNum + ". " + amenitiesDTO.getAmenities());
            }
            System.out.format("                                                      戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");
            System.out.print("                                                      Enter (separated by commas) : ");
            String accessibilityAmenities = MyIOStream.sc.nextLine();
            if (!accessibilityAmenities.equals("-1")) {
                String[] accessArr = accessibilityAmenities.split(",");
                for (String s : accessArr) {
                    int n = Integer.parseInt(s);
                    if (n > 0 && n <= list.size()) {
                        amenitiesDTOList.add(list.get(n - 1));
                    }
                }
            }
        }
        System.out.println("                                                                     忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
        System.out.println("                                                                     弛        Would you like to register?        弛");
        System.out.println("                                                                     弛           (Enter 1 to register)           弛");
        System.out.println("                                                                     戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
        System.out.print("                                                                                     Selection : ");
        String enter = MyIOStream.sc.next();
        if (enter.equals("1")) {
            HouseDTO houseDTO = new HouseDTO(userDTO.getUserId(), houseName, houseAddress, info, bedroomCount, bathroomCount);
            HouseRegistrationController houseRegistrationController = new HouseRegistrationController();
            protocol = houseRegistrationController.houseRegisterRequest(houseDTO, amenitiesDTOList);
            if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                System.out.println("                                                                          忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                System.out.println("                                                                          弛         Successful!          弛");
                System.out.println("                                                                          戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
            } else if (protocol.getProtocolCode() == Protocol.CODE_ERROR) {
                System.out.println(protocol.getObject());
            }
        } else {
            System.out.println("                                                                          忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
            System.out.println("                                                                          弛       Fail to Register       弛");
            System.out.println("                                                                          戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
        }
    }

    private void setCost() throws IOException, ClassNotFoundException {
        System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式[Approved List ]式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣                                                                                                           弛%n");
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");

        SetCostPolicyConroller setCostPolicyConroller = new SetCostPolicyConroller();
        Protocol protocol = setCostPolicyConroller.sendHouseListRequest(userDTO);

        List<HouseDTO> list = (List<HouseDTO>) protocol.getObject();
        int i = 0;

        for (HouseDTO houseDTO : list) {
            printFormatted(++i + ". " + houseDTO.getHouseName(), houseDTO.getHouseAddress(), firstColWidth, secondColWidth);
        }
        System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");
        System.out.println("                                                                     忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
        System.out.println("                                                                     弛   Enter The Number You Want to Set Cost   弛");
        System.out.println("                                                                     弛                (Back to -1)               弛");
        System.out.println("                                                                     戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
        System.out.print("                                                                                     Enter : ");

        int enter = MyIOStream.sc.nextInt();

        if (enter == -1) {
            System.out.println("Back..");
        } else if (enter > 0 && enter <= i) {
            System.out.println("                                                                     忙式式                                       式式忖");
            System.out.print("                                                                               Enter Weekday Cost : ");
            int weekdayCost = MyIOStream.sc.nextInt();
            System.out.print("                                                                               Enter Weekend Cost : ");
            int weekendCost = MyIOStream.sc.nextInt();
            System.out.println("                                                                     戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");

            System.out.println("                                                                     忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
            System.out.println("                                                                     弛        Would you like to register?        弛");
            System.out.println("                                                                     弛          (1) YES   (2) NO, BACK           弛");
            System.out.println("                                                                     戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
            System.out.print("                                                                                     Selection : ");

            int input = MyIOStream.sc.nextInt();

            if (input == -1) {
                System.out.println("Back..");
            } else if (input == 1) {
                if (weekdayCost >= 0 && weekendCost >= 0) {
                    FeePolicyDTO feePolicyDTO = new FeePolicyDTO(list.get(enter - 1).getHouseId(), weekdayCost, weekendCost);
                    protocol = setCostPolicyConroller.costSettingRequest(feePolicyDTO);
                    if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                        System.out.println("                                                                     忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                        System.out.println("                                                                     弛           Success!           弛");
                        System.out.println("                                                                     戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
                    } else if (protocol.getProtocolCode() == Protocol.CODE_ERROR) {
                        System.out.println(protocol.getObject());
                    }
                } else {
                    System.out.println("Wrong Input..");
                }
            } else {
                System.out.println("Wrong Input..");
            }
        } else {
            System.out.println("Wrong Input..");
        }
    }

    public void setDiscountPolicy() throws IOException, ClassNotFoundException {
        SetDiscountPolicyController setDiscountPolicyController = new SetDiscountPolicyController();
        Protocol protocol = setDiscountPolicyController.houseListRequest(userDTO);
        List<HouseAndDiscountDTO> list = (List<HouseAndDiscountDTO>) protocol.getObject();
        if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
            System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
            System.out.format("弛                                                                               [ Discount Policy ]                                                                                       弛%n");
            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
            System.out.format("弛%-70s弛%-15s弛%-20s弛%-20s                                                 弛%n", "[House Name]", "[Day]", "[Quantitative Discount]     ", "[Flat rate discount]");
            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");

            int i = 0;
            for (HouseAndDiscountDTO houseAndDiscountDTO : list) {
                printFormattedForAccommodationRequests((++i) + ". " + houseAndDiscountDTO.getHouseDTO().getHouseName(),
                        String.valueOf(houseAndDiscountDTO.getDiscountPolicyDTO().getDiscountDay()),
                        String.valueOf(houseAndDiscountDTO.getDiscountPolicyDTO().getDiscount_amount()),
                        String.valueOf(houseAndDiscountDTO.getDiscountPolicyDTO().getDiscount_rate()), 68, 13, 26, 67);
            }

            System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");

            System.out.print("Select Number : ");

            int number = MyIOStream.sc.nextInt();

            if (number > 0 && number <= i) {
                System.out.println("                                                                     忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                System.out.println("                                                                     弛             Set discount period           弛");
                System.out.println();
                System.out.print("                                                                                  Period setting : ");

                int discountDay = MyIOStream.sc.nextInt();
                System.out.println("                                                                     戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
                System.out.println("                                                                     忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                System.out.println("                                                                     弛          1. Quantitative Discount         弛");
                System.out.println("                                                                     弛          2. Flat rate discount            弛");
                System.out.println("                                                                     戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
                System.out.print("                                                                                     Selection : ");
                int select = MyIOStream.sc.nextInt();
                if (select == 1) {
                    System.out.println("                                                                     忙式式                                       式式忖");
                    System.out.print("                                                                                 Amount Set : ");
                    int amount = MyIOStream.sc.nextInt();
                    System.out.println("                                                                     戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");

                    if (amount < 0) {
                        System.out.println("Wrong Input..");
                    } else {
                        System.out.println("                                                                     忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                        System.out.println("                                                                     弛              Enter Start Date             弛");
                        System.out.println("                                                                     弛                (YYYY-MM-DD)               弛");
                        System.out.println();
                        System.out.print("                                                                                  Enter : ");
                        MyIOStream.sc.nextLine(); // Buffer Clear
                        String startDate = MyIOStream.sc.nextLine();
                        System.out.println("                                                                     戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");

                        System.out.println("                                                                     忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                        System.out.println("                                                                     弛               Enter End Date              弛");
                        System.out.println("                                                                     弛                (YYYY-MM-DD)               弛");
                        System.out.println();
                        System.out.print("                                                                                  Enter : ");
                        String endDate = MyIOStream.sc.nextLine();
                        System.out.println("                                                                     戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");

                        protocol = setDiscountPolicyController.setDiscountPolicyRequest(list.get(number - 1).getDiscountPolicyDTO().getHouseId(), discountDay, amount, 0, startDate, endDate);
                        if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                            System.out.println("                                                                          忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                            System.out.println("                                                                          弛           Success!           弛");
                            System.out.println("                                                                          戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
                        }
                    }
                } else if (select == 2) {
                    System.out.println("                                                                     忙式式                                       式式忖");
                    System.out.print("                                                                                    Set Rate : ");
                    int rate = MyIOStream.sc.nextInt();
                    System.out.println("                                                                     戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");

                    if (rate < 0) {
                        System.out.println("Wrong Input..");
                    } else {
                        System.out.println("                                                                     忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                        System.out.println("                                                                     弛              Enter Start Date             弛");
                        System.out.println("                                                                     弛                (YYYY-MM-DD)               弛");
                        System.out.println();
                        System.out.print("                                                                                  Enter : ");
                        MyIOStream.sc.nextLine(); // Buffer Clear
                        String startDate = MyIOStream.sc.nextLine();
                        System.out.println("                                                                     戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");

                        System.out.println("                                                                     忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                        System.out.println("                                                                     弛               Enter End Date              弛");
                        System.out.println("                                                                     弛                (YYYY-MM-DD)               弛");
                        System.out.println();
                        System.out.print("                                                                                  Enter : ");
                        String endDate = MyIOStream.sc.nextLine();
                        System.out.println("                                                                     戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");

                        protocol = setDiscountPolicyController.setDiscountPolicyRequest(list.get(number - 1).getDiscountPolicyDTO().getHouseId(), discountDay, 0, rate, startDate, endDate);
                        if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                            System.out.println("                                                                     忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                            System.out.println("                                                                     弛           Success!           弛");
                            System.out.println("                                                                     戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
                        }
                    }
                } else {
                    System.out.println("Wrong Input..");
                }
            } else {
                System.out.println("Wrong Input..");
            }


        }
    }


    private int getCommand() {
        System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
        System.out.format("弛                             <Host Page>                                弛                                                                                                           弛%n");
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        System.out.format("弛         1. Accommodation Registration                                  弛                                                                                                           弛%n");
        System.out.format("弛         2. Set Cost                                                    弛                                                                                                           弛%n");
        System.out.format("弛         3. Set Discount Policy                                         弛                                                                                                           弛%n");
        System.out.format("弛         4. Check reservation status                                    弛                                                                                                           弛%n");
        System.out.format("弛         5. Approval/Reject guest's accommodation reservation           弛                                                                                                           弛%n");
        System.out.format("弛         6. Register a reply to a guest review                          弛                                                                                                           弛%n");
        System.out.format("弛         0. Log Out                                                     弛                                                                                                           弛%n");
        System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");
        System.out.print("enter : ");

        return MyIOStream.sc.nextInt();
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

            System.out.format("弛 %-" + firstWidth + "s 弛 %-" + secondWidth + "s 弛 %-" + thirdWidth + "s 弛 %-" + fourthWidth + "s 弛 %n",
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