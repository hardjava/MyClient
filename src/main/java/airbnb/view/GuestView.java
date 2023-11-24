package airbnb.view;

import airbnb.controller.SearchAllHouseController;
import airbnb.controller.SearchMoreHouseInfoController;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class GuestView {
    private UserDTO userDTO;

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
                        System.out.println("\t\t[House list]");
                        System.out.printf("%-40s%-40s\n", "<House_Name>", "<House_Address>");
                        int i = 0;
                        for (HouseDTO houseDTO : list) {
                            System.out.printf("%d. %s\n", ++i, houseDTO.toString());
                        }

                        System.out.print("Select Accommodation (exit -1) : ");
                        int enter = MyIOStream.sc.nextInt();

                        if (enter == -1) {
                            System.out.println("Exit..");
                            break;
                        }

                        if (enter > 0 && enter <= i) {
                            HouseDTO houseDTO = list.get(i - 1);
                            SearchMoreHouseInfoController searchMoreHouseInfoController = new SearchMoreHouseInfoController();
                            protocol = searchMoreHouseInfoController.printMoreInfo(houseDTO);
                            MoreHouseInfoDTO moreHouseInfoDTO = (MoreHouseInfoDTO) protocol.getObject();

                            List<AmenitiesDTO> amenitiesDTOList = moreHouseInfoDTO.getAmenitiesDTOList();
                            FeePolicyDTO feePolicyDTO = moreHouseInfoDTO.getFeePolicyDTO();
                            List<ReservationDTO> reservationDTOList = moreHouseInfoDTO.getReservationDTOList();
                            List<ReviewDTO> reviewDTOList = moreHouseInfoDTO.getReviewDTOList();

                            System.out.println("[ House Name : " + houseDTO.getHouseName() + " ]");
                            System.out.println("[ House Address : " + houseDTO.getHouseAddress() + " ]");
                            System.out.println("[ House Capacity : " + houseDTO.getBedroom() + " ]");
                            System.out.println("[ Bedroom Count : " + houseDTO.getBedroom() + " ]");
                            System.out.println("[ Bathroom Count : " + houseDTO.getBathroom() + " ]");
                            System.out.println("[ House Info : " + houseDTO.getHouseIntroduce() + " ]");
                            System.out.println("[ Weekday Cost : " + feePolicyDTO.getWeekday() + " ]");
                            System.out.println("[ Weekend Cost : " + feePolicyDTO.getWeekend() + " ]");
                            System.out.println("[ Amenities Info]");
                            System.out.println("\t[Basic Amenities]");
                            for (AmenitiesDTO amenitiesDTO : amenitiesDTOList) {
                                if (amenitiesDTO.getTypeId() == 1)
                                    System.out.println(amenitiesDTO.getAmenities());
                            }
                            System.out.println("\t[Safety Amenities]");
                            for (AmenitiesDTO amenitiesDTO : amenitiesDTOList) {
                                if (amenitiesDTO.getTypeId() == 2)
                                    System.out.println(amenitiesDTO.getAmenities());
                            }
                            System.out.println("\t[Accessibility Amenities]");
                            for (AmenitiesDTO amenitiesDTO : amenitiesDTOList) {
                                if (amenitiesDTO.getTypeId() == 3)
                                    System.out.println(amenitiesDTO.getAmenities());
                            }

                            System.out.println("[Review]");
                            for (ReviewDTO reviewDTO : reviewDTOList) {
                                System.out.println(reviewDTO.toString());
                            }
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
                    System.out.println("Please Select (1) ~ (4)");
                    break;
            }
        }
    }

    private int getCommand() {
        System.out.println("\t\t<Guset Page>");
        printUserInfo();
        System.out.println("\t\t1. ���� �˻�");
        System.out.println("\t\t2. ���� ���");
        System.out.println("\t\t3. ���� ������");
        System.out.println("\t\t4. �α׾ƿ�");
        System.out.print("enter : ");
        return MyIOStream.sc.nextInt();
    }

    private void printUserInfo() {
        System.out.println("Name :  " + userDTO.getUserName() + "\nRole :  " + userDTO.getRole());
    }

    private void calender() {
        // ���� �޷� �ν��Ͻ� ������
        Calendar calendar = Calendar.getInstance();
        // ���� ���� ù �� ����
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        int month = calendar.get(Calendar.MONTH);
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // �޷� ���� ���
        System.out.println("Sun Mon Tue Wed Thu Fri Sat");

        // ù ��° ��¥�� ���۵� ������ ��������
        for (int i = 1; i < firstDayOfWeek; i++) {
            System.out.print("    ");
        }

        // ��¥ ���
        for (int i = 1; i <= daysInMonth; i++) {
            System.out.printf("%2d  ", i);
            if ((i + firstDayOfWeek - 1) % 7 == 0 || i == daysInMonth) {
                System.out.println();
                // ��¥ �Ʒ� ���� �߰�
                for (int j = 0; j < 7 && (i - j) > 0; j++) {
                    System.out.print("    "); // ��¥ ���� ����
                }
                System.out.println(); // ���� �ַ� �̵�
            }
        }
    }
}
