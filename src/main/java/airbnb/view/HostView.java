package airbnb.view;

import airbnb.controller.AmenitiesRequestController;
import airbnb.controller.HouseRegistrationController;
import airbnb.controller.SetCostPolicyConroller;
import airbnb.controller.SetDiscountPolicyController;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HostView {
    private UserDTO userDTO;

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
                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }
    }

    private void manageReviews() {
        System.out.println("1. 최근 리뷰 확인");
        System.out.println("2. 리뷰 답글 작성");
        System.out.println("3. 뒤로가기");

        int choice = MyIOStream.sc.nextInt();
        switch (choice) {
            case 1:
                // 최근 리뷰 확인
            case 2:
                // 리뷰 답글 작성
            case 3:
                break; // 뒤로가기
            default:
                System.out.println("잘못된 입력입니다. 다시 시도하세요.");
        }
        // 리뷰 관리 로직
    }

    private void manageReservations() {
        // 예약 승인 대기 리스트

    }

    private void showReservationStatus() { // 숙박 예약 현황 보기
        // 등록된 숙소 조회
        int i = 0;
        //
        System.out.print("Please enter the number of the accommodation you want to search : ");
        int enter = MyIOStream.sc.nextInt();

        if (enter > 0 && enter <= i) {
            // 숙소의 ReservationDTO 받아오기

        } else {
            System.out.println("Wrong Input..");
        }
    }

    // 추가적인 메서드로 숙박 등록을 위한 registerAccommodation() 메서드
    private void registerAccommodation() throws IOException, ClassNotFoundException {
        System.out.println("\t\t<Register Accommodation>");
        System.out.print("\t\tHouse Name : ");
        MyIOStream.sc.nextLine(); // 버퍼 비우기
        String houseName = MyIOStream.sc.nextLine();
        System.out.print("\t\tHouse Address : ");
        String houseAddress = MyIOStream.sc.nextLine();
        System.out.print("\t\tAccommodation Type (1. Private room.  2. Entire space) : ");
        int roomType = MyIOStream.sc.nextInt();

        System.out.print("\t\tBathroom Count : ");
        int bathroomCount = MyIOStream.sc.nextInt();

        System.out.print("\t\tBedroom Count : ");
        int bedroomCount = MyIOStream.sc.nextInt();

        System.out.print("\t\tAccommodation Info : ");
        MyIOStream.sc.nextLine(); // 버퍼 비우기
        String info = MyIOStream.sc.nextLine();

        System.out.println("\t\t[Amenities Register]");
        List<AmenitiesDTO> amenitiesDTOList = new ArrayList<>();
        AmenitiesRequestController amenitiesRequestController = new AmenitiesRequestController();
        System.out.println("\t\tBasic Amenities : (If not, enter -1) : ");
        Protocol protocol = amenitiesRequestController.basicAmenitiesListRequest();

        if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
            int basicNum = 0;
            List<AmenitiesDTO> list = (List<AmenitiesDTO>) protocol.getObject();
            for (AmenitiesDTO amenitiesDTO : list) {
                System.out.println("\t" + ++basicNum + ". " + amenitiesDTO.getAmenities());
            }
            System.out.print("Enter (separated by commas) : ");
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
        System.out.println("\t\tSafety Amenities : (If not, enter -1) : ");
        protocol = amenitiesRequestController.safetyAmenitiesListRequest();
        if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
            List<AmenitiesDTO> list = (List<AmenitiesDTO>) protocol.getObject();
            int safetyNum = 0;
            for (AmenitiesDTO amenitiesDTO : list) {
                System.out.println("\t" + ++safetyNum + ". " + amenitiesDTO.getAmenities());
            }
            System.out.print("Enter (separated by commas) : ");
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
        System.out.println("\t\tAccessibility Amenities : (If not, enter -1) : ");
        protocol = amenitiesRequestController.accessAmenitiesListRequest();
        if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
            List<AmenitiesDTO> list = (List<AmenitiesDTO>) protocol.getObject();
            int accessNum = 0;
            for (AmenitiesDTO amenitiesDTO : list) {
                System.out.println("\t" + ++accessNum + ". " + amenitiesDTO.getAmenities());
            }
            System.out.print("Enter (separated by commas) : ");
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
        System.out.print("\t\tWould you like to register? (Enter 1 to register) : ");
        String enter = MyIOStream.sc.next();
        if (enter.equals("1")) {
            HouseDTO houseDTO = new HouseDTO(userDTO.getUserId(), houseName, houseAddress, info, bedroomCount, bathroomCount);
            HouseRegistrationController houseRegistrationController = new HouseRegistrationController();
            protocol = houseRegistrationController.houseRegisterRequest(houseDTO, amenitiesDTOList);

            if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                System.out.println("Successful!");
            } else if (protocol.getProtocolCode() == Protocol.CODE_ERROR) {
                System.out.println(protocol.getObject());
            }
        } else {
            System.out.println("Fail to Register..");
        }
    }

    private void setCost() throws IOException, ClassNotFoundException {
        System.out.println("\t\t[Approved List]");
        SetCostPolicyConroller setCostPolicyConroller = new SetCostPolicyConroller();
        Protocol protocol = setCostPolicyConroller.sendHouseListRequest(userDTO);

        List<HouseDTO> list = (List<HouseDTO>) protocol.getObject();
        int i = 0;
        for (HouseDTO houseDTO : list) {
            System.out.println(++i + ". " + houseDTO.toString());
        }

        System.out.print("\t\tEnter The Number You Want to Set Cost, (Back to -1) : ");
        int enter = MyIOStream.sc.nextInt();

        if (enter == -1) {
            System.out.println("Back..");
        } else if (enter > 0 && enter <= i) {
            System.out.print("\t\tEnter Weekday Cost : ");
            int weekdayCost = MyIOStream.sc.nextInt();
            System.out.print("\t\tEnter Weekend Cost : ");
            int weekendCost = MyIOStream.sc.nextInt();

            System.out.print("\t\tWould you like to register? (Enter 1 to register, back to -1) : ");
            int input = MyIOStream.sc.nextInt();

            if (input == -1) {
                System.out.println("Back..");
            } else if (input == 1) {
                if (weekdayCost >= 0 && weekendCost >= 0) {
                    FeePolicyDTO feePolicyDTO = new FeePolicyDTO(list.get(enter - 1).getHouseId(), weekdayCost, weekendCost);
                    protocol = setCostPolicyConroller.costSettingRequest(feePolicyDTO);
                    if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                        System.out.println("Success!");
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
            System.out.println("[List]");
            System.out.printf("%-20s%-10s%-15s%-15s\n", "[House Name]", "[Day]", "[Quantitative Discount]", "[Flat rate discount]");
            int i = 0;
            for (HouseAndDiscountDTO houseAndDiscountDTO : list) {
                System.out.println(++i + ". " + houseAndDiscountDTO.toString());
            }

            System.out.print("Select Number : ");
            int number = MyIOStream.sc.nextInt();

            if (number > 0 && number <= i) {
                System.out.print("Enter Discount Day : ");
                int discountDay = MyIOStream.sc.nextInt();
                System.out.print("(1) Quantitative Discount (2) Flat rate discount : ");
                int select = MyIOStream.sc.nextInt();
                if (select == 1) {
                    System.out.print("Amount : ");
                    int amount = MyIOStream.sc.nextInt();
                    if (amount < 0) {
                        System.out.println("Wrong Input..");
                    } else {
                        protocol = setDiscountPolicyController.setDiscountPolicyRequest(discountDay, amount, 0, list.get(number - 1).getDiscountPolicyDTO().getHouseId());
                        if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                            System.out.println("Success!");
                        }
                    }
                } else if (select == 2) {
                    System.out.print("Rate : ");
                    int rate = MyIOStream.sc.nextInt();
                    if (rate < 0) {
                        System.out.println("Wrong Input..");
                    } else {
                        protocol = setDiscountPolicyController.setDiscountPolicyRequest(discountDay, 0, rate, list.get(number - 1).getDiscountPolicyDTO().getHouseId());
                        if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                            System.out.println("Success!");
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
        System.out.println("\t\t<Host Page>");
        System.out.println("\t\t1. Accommodation Registration");
        System.out.println("\t\t2. Set Cost");
        System.out.println("\t\t3. Set Discount Policy");
        System.out.println("\t\t4. Approve/Decline reservation");
        System.out.println("\t\t5. Review Management");
        System.out.println("\t\t0. Log Out");
        System.out.print("enter : ");

        return MyIOStream.sc.nextInt();
    }
}
