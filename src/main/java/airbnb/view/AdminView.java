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
            System.out.format("��������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
            System.out.format("��   <Admin Page>         ��                                                    ��%n");
            System.out.format("��������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
            System.out.format("�� 1. Approval/Reject Host's Accommodation registration                        ��%n");
            System.out.format("�� 2. Check current accommodation situation                                    ��%n");
            System.out.format("�� 0. Logout              ��                                                    ��%n");
            System.out.format("��������������������������������������������������������������������������������������������������������������������������������������������������������������%n");

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
        //���� ����Ʈ ���
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
        // ȣ��Ʈ�� ���� ��� ��û�� ����Ʈ �����ֱ�
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
            // ������ �� ���� ����
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
                // ����
                protocol = requestHouseRegistrationController.approvalRequest(list.get(enter - 1).getHouseId());

                if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                    System.out.println("Success to Approval");
                }

            } else if (command == 2) {
                protocol = requestHouseRegistrationController.rejectRequest(list.get(enter - 1).getHouseId());

                if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                    System.out.println("Success to Reject");
                }
                // ����
            } else {
                System.out.println("Wrong Input..");
            }
        } else {
            System.out.println("Wrong Input..");
        }


//        System.out.format("��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
//        System.out.format("�� 1. Pending Accommodations      ��                                                              ��%n");
//        System.out.format("�� 2. Rejected Accommodations     ��                                                              ��%n");
//        System.out.format("�� 3. Go Back                     ��                                                              ��%n");
//        System.out.format("��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
//        System.out.print("Enter command: ");

//        int choice = sc.nextInt();
//        switch (choice) {
//            case 1:
//                 �̽��� ���� �ü� ���
//                System.out.format("��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
//                System.out.format("�� Unapproved Accommodation List  ��                                                              ��%n");
//                System.out.format("��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
//                printFormatted("Line", "", firstColWidth, secondColWidth);
//                 �� printFormatted�� �̿��ؼ� Line�� DTO���� �����°� �ְ� �ι�°���ʹ� ����
//                System.out.format("��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
//                System.out.println("Please select the unapproved accommodation : ");
//
//                System.out.println("������                          ������");
//                System.out.println("�� (1) Approval  (2) Rejection  ��");
//                System.out.println("������                          ������");
//                System.out.println("          Selection : ");
//
//                 ����Ʈ�� ���� �ü� �� �����ϸ� "����" "����" �� �����ϰ� �޽��� ���
//                System.out.println("Accommodation request from ~~ has been approved.");
//                System.out.println("Accommodation request from ~~ has been rejected.");
//                break;
//            case 2:
//                 ������ ���� �ü� ���
//                System.out.format("��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
//                System.out.format("��  Rejected Accommodation List   ��                                                              ��%n");
//                System.out.format("��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
//                printFormatted("Line", "", firstColWidth, secondColWidth);
//                 �� printFormatted�� �̿��ؼ� Line�� DTO���� �����°� �ְ� �ι�°���ʹ� ����
//                System.out.format("��������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������%n");
//                System.out.println("Press Any Key : ");
//
//            case 0:
//                return; // �ڷΰ���
//            default:
//                System.out.println("Invalid input. Please try again.");
//        }
//    }
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
    // ���� ����� �ҷ����� �޼��� �� �߰������� �ʿ��� ������ ����
}
