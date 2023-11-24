package airbnb.view;

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
                    //showReservationStatus();
                    break;
                case 5:
                    //manageReservations();
                    break;
                case 6:
                    //manageReviews();
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
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
        System.out.print("\t\tBasic Amenities : (If not, enter -1) : ");
        String basicAmenities = MyIOStream.sc.nextLine();
        if (!basicAmenities.equals("-1")) {
            AmenitiesDTO basic = new AmenitiesDTO(basicAmenities, 1);
            amenitiesDTOList.add(basic);
        }

        System.out.print("\t\tSafety Amenities : (If not, enter -1) : ");
        String safetyAmenities = MyIOStream.sc.nextLine();
        if (!safetyAmenities.equals("-1")) {
            AmenitiesDTO safety = new AmenitiesDTO(safetyAmenities, 2);
            amenitiesDTOList.add(safety);
        }

        System.out.print("\t\tAccessibility Amenities : (If not, enter -1) : ");
        String accessibilityAmeities = MyIOStream.sc.nextLine();
        if (!accessibilityAmeities.equals("-1")) {
            AmenitiesDTO accessibility = new AmenitiesDTO(accessibilityAmeities, 3);
            amenitiesDTOList.add(accessibility);
        }

        System.out.print("\t\tWould you like to register? (Enter 1 to register) : ");
        String enter = MyIOStream.sc.next();
        if (enter.equals("1")) {
            HouseDTO houseDTO = new HouseDTO(houseName, houseAddress, info, bedroomCount, bathroomCount);
            HouseRegistrationController houseRegistrationController = new HouseRegistrationController();
            Protocol protocol = houseRegistrationController.houseRegisterRequest(houseDTO, amenitiesDTOList);

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
                    protocol = setDiscountPolicyController.setDiscountPolicyRequest(discountDay, amount, 0, list.get(number - 1).getHouseDTO().getHouseId());

                    if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                        System.out.println("Success!");
                    }

                } else if (select == 2) {
                    System.out.print("Rate : ");
                    int rate = MyIOStream.sc.nextInt();
                    protocol = setDiscountPolicyController.setDiscountPolicyRequest(discountDay, 0, rate, list.get(number - 1).getHouseDTO().getHouseId());

                    if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                        System.out.println("Success!");
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
/*
    private void setPricingPolicy() {
        System.out.println("##### Set Weekday/Weekend Rates #####");
        System.out.print("Weekday Rates($) : ");
        int weekendRate = sc.nextInt();
        System.out.print("Weekend Rates($) : ");
        int weekdayRate = sc.nextInt();
        // 요금 정책 설정 로직
    }

    private void setDiscountPolicy() {
        System.out.println("1. 연박 할인 적용 기간 설정");
        System.out.println("2. 정량 / 정률 할인 설정");
        System.out.println("3. 뒤로가기");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("[추가 1박당 할인 설정]\n" +
                        "1. 정액 할인\n" +
                        "2. 정률 할인");

                int choiceInside = sc.nextInt();
                switch (choiceInside) {
                    case 1:
                        System.out.println("1박 당 할인 금액 설정(원) : "); // 정액 할인 시
                    case 2:
                        System.out.println("1박 당 할인 백분율 설정(%) : "); // 정률 할인 시
                }

            case 2:
                System.out.println("[평일, 주말 할인 설정]\n" +
                        "1. 정량 할인\n" +
                        "2. 정률 할인");

                int choiceInside2 = sc.nextInt();
                switch (choiceInside2) {
                    case 1:
                        System.out.println("평일 할인 금액 설정(원) : "); // 정액 할인 시
                        System.out.println("주말 할인 금액 설정(원) : ");
                    case 2:
                        System.out.println("평일 할인 백분율 설정(%) : ");// 정률 할인 시
                        System.out.println("주말 할인 백분율 설정(%) : ");
                }
            case 3:
                break; // 뒤로가기
            default:
                System.out.println("잘못된 입력입니다. 다시 시도하세요.");
        }
    }
    // 연박 할인 적용 기간 및 할인율 설정 로직
    // 정량 / 정률 할인 설정 로직


    private void showReservationStatus() {
        System.out.println("4. 숙박 예약 현황");
        // 숙박 예약 현황 클릭시 달력 출력 되어야함. 년도와 월을 입력받는 것과 기호로 예약 유뮤 표시하는건 차후 진행하고 달력 출력은 대충 짜봄
        //calender() 실행 예시
        //Sun Mon Tue Wed Thu Fri Sat
        //             1   2   3   4
        //
        // 5   6   7   8   9  10  11
        //
        //12  13  14  15  16  17  18
        //
        //19  20  21  22  23  24  25
        //
        //26  27  28  29  30
    }

    private void manageReservations() {
        System.out.println("1. 예약 승인 대기 리스트 확인");
        System.out.println("2. 예약 거절 기록 확인");
        System.out.println("3. 뒤로가기");

        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                // 예약 승인 대기 리스트 확인
            case 2:
                // 예약 거절 기록 확인
            case 3:
                break; // 뒤로가기
            default:
                System.out.println("잘못된 입력입니다. 다시 시도하세요.");
        }
        // 예약 승인 및 거절 처리 로직
    }

    private void manageReviews() {
        System.out.println("1. 최근 리뷰 확인");
        System.out.println("2. 리뷰 답글 작성");
        System.out.println("3. 뒤로가기");

        int choice = sc.nextInt();
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

 */
}
