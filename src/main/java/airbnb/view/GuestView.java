package airbnb.view;

import airbnb.controller.*;
import airbnb.network.HouseType;
import airbnb.network.MyIOStream;
import airbnb.network.Protocol;
import airbnb.persistence.dto.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class GuestView {
    private final UserDTO userDTO;
    private final int firstColWidth = 70; // 羅 廓簞 翮曖 ァ
    private final int secondColWidth = 105; // 舒 廓簞 翮曖 ァ
    private final String leftAlignFormat = "| %-" + firstColWidth + "s | %-" + secondColWidth + "s |%n";

    public GuestView(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public void showView() throws Exception {
        for (; ; ) {
            try {
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
                        System.out.println("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                        System.out.println("弛   Please Select (1) ~ (4)    弛");
                        System.out.println("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong Input..");
                MyIOStream.sc.nextLine();
            }
        }
    }

    private void houseFiltering() throws Exception {
        String houseName, checkIn, checkOut = "";
        int guestNum = 0;
        MyIOStream.sc.nextLine(); // Buffer Clear
        System.out.format("                                                    忙式式式                                                                     式式式忖%n");
        System.out.format("                                                    弛                                                                           弛%n");
        System.out.format("                                                                                                                                 %n");
        System.out.print("                                                               Enter House Name (If not, Press 'Enter'): ");

        houseName = MyIOStream.sc.nextLine();
        System.out.print("                                                               Enter CheckIn (YYYY-MM-DD) (If not, Press 'Enter'): ");
        checkIn = MyIOStream.sc.nextLine();
        if (!checkIn.equals("")) {
            System.out.print("                                                               Enter CheckOut (YYYY-MM-DD) (If not, Press 'Enter'): ");
            checkOut = MyIOStream.sc.nextLine();
        }
        System.out.print("                                                               Enter Guest Num : ");
        guestNum = MyIOStream.sc.nextInt();

        System.out.println("                                                                     忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
        System.out.println("                                                                     弛              Enter House Type             弛");
        System.out.println("                                                                     戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
        System.out.println("                                                                     弛          (1) private   (2) public         弛");
        System.out.println("                                                                     戌式式                                       式式戎");
        System.out.print("                                                                                     Selection : ");
        int houseType = MyIOStream.sc.nextInt();
        System.out.format("                                                    戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");

        if (houseType == 1 || houseType == 2) {
            // Amenities List 轎溘
            List<String> amenitiesList = new ArrayList<>();
            AmenitiesRequestController amenitiesRequestController = new AmenitiesRequestController();
            Protocol protocol = amenitiesRequestController.basicAmenitiesListRequest();
            System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
            System.out.format("弛                       Basic Amenities List                             弛                                                                                                           弛%n");
            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");


            if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                int basicNum = 0;
                List<AmenitiesDTO> list = (List<AmenitiesDTO>) protocol.getObject();
                for (AmenitiesDTO amenitiesDTO : list) {
                    String BasicList = String.format("%d. %s", ++basicNum, amenitiesDTO.getAmenities());
                    printFormatted(BasicList, "", firstColWidth, secondColWidth);
                }
                System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");

                System.out.print("Enter (separated by commas) (If not, Press 'Enter') : ");
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
            System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
            System.out.format("弛                        Safety Amenities List                           弛                                                                                                           弛%n");
            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");


            if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                List<AmenitiesDTO> list = (List<AmenitiesDTO>) protocol.getObject();
                int safetyNum = 0;
                for (AmenitiesDTO amenitiesDTO : list) {
                    String SafetyList = String.format("%d. %s", ++safetyNum, amenitiesDTO.getAmenities());
                    printFormatted(SafetyList, "", firstColWidth, secondColWidth);
                }
                System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");

                System.out.print("Enter (separated by commas) (If not, Press 'Enter') : ");
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
            System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
            System.out.format("弛                    Accessibility Amenities List                        弛                                                                                                           弛%n");
            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");

            if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                List<AmenitiesDTO> list = (List<AmenitiesDTO>) protocol.getObject();
                int accessNum = 0;
                for (AmenitiesDTO amenitiesDTO : list) {
                    String AccessibilityList = String.format("%d. %s", ++accessNum, amenitiesDTO.getAmenities());
                    printFormatted(AccessibilityList, "", firstColWidth, secondColWidth);
                }
                System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");

                System.out.print("Enter (separated by commas) (If not, Press 'Enter') : ");
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
                System.out.format("                                             忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
                System.out.format("                                             弛                       Which accommodation would you like to see more INFO? (back : -1)        弛%n");
                System.out.format("                                             戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");
                System.out.print("                                                                                      Enter : ");

                int index = MyIOStream.sc.nextInt();

                if (index == -1) {
                    System.out.println("back..");
                } else if (index > 0 && index <= houseAndFeeDTOS.size()) {
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

    private int getCommand() {
        System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
        System.out.format("弛                             <Guest Page>                               弛                                                                                                           弛%n");
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        printUserInfo();
        System.out.format("弛                            1. FIND HOUSE                               弛                                                                                                           弛%n");
        System.out.format("弛                            2. HOUSE LIST                               弛                                                                                                           弛%n");
        System.out.format("弛                            3. MYPAGE                                   弛                                                                                                           弛%n");
        System.out.format("弛                            4. LOGOUT                                   弛                                                                                                           弛%n");
        System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");

        System.out.print("enter : ");
        return MyIOStream.sc.nextInt();
    }

    private void printUserInfo() {
        System.out.format("弛                          Name : %-13s                          弛                                                                                                           弛%n", userDTO.getUserName());
        System.out.format("弛                          Role : %-13s                          弛                                                                                                           弛%n", userDTO.getRole());
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
    }

    private int getHouseListCommand() {
        System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
        System.out.format("弛                             <HOUSE LIST>                               弛                                                                                                           弛%n");
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        System.out.format("弛                            1. Ascending                                弛                                                                                                           弛%n");
        System.out.format("弛                            2. Descending                               弛                                                                                                           弛%n");
        System.out.format("弛                            3. Reservation                              弛                                                                                                           弛%n");
        System.out.format("弛                            4. Back                                     弛                                                                                                           弛%n");
        System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");
        System.out.print(" Enter : ");
        return MyIOStream.sc.nextInt();
    }

    private void houseAllList() throws Exception {
        SearchAllHouseController searchAllHouseController = new SearchAllHouseController();
        Protocol protocol = searchAllHouseController.allHouseRequest();

        List<HouseAndFeeDTO> allList = (List<HouseAndFeeDTO>) protocol.getObject();
        System.out.format("                                             忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
        System.out.format("                                             弛                                              ALL LIST                                         弛%n");
        System.out.format("                                             戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");

        if (allList != null) {
            printHouseList(allList);
            int listCommand = getHouseListCommand();

            if (listCommand == 4) {
                System.out.println("Back..");
            } else {
                switch (listCommand) {
                    case 1:
                        // 螃葷離牖
                        printAscendingList(searchAllHouseController);
                        break;
                    case 2:
                        // 頂葡離牖
                        printDescendingList(searchAllHouseController);
                        break;
                    case 3:
                        System.out.format("                                             忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
                        System.out.format("                                             弛                                          ALL LIST                                             弛%n");
                        System.out.format("                                             戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");

                        System.out.format("                                             忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
                        System.out.format("                                             弛                                 Enter Num to Reservation (Back : -1)                          弛%n");
                        System.out.format("                                             戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");
                        System.out.print("                                                                                      Enter : ");

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
        // 鼻撮薑爾 爾罹輿晦
        System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
        System.out.format("弛                         DETAIL INFORMATION                             弛                                                                                                           弛%n");
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        printFormatted("[House Name]", houseAndFeeDTO.getHouseName(), firstColWidth, secondColWidth);
        printFormatted("[House Address]", houseAndFeeDTO.getHouseAddress(), firstColWidth, secondColWidth);
        printFormatted("[Capacity]", String.valueOf(houseAndFeeDTO.getBedroom()), firstColWidth, secondColWidth);
        printFormatted("[Bedroom Count]", String.valueOf(houseAndFeeDTO.getBedroom()), firstColWidth, secondColWidth);
        printFormatted("[Bathroom Count]", String.valueOf(houseAndFeeDTO.getBathroom()), firstColWidth, secondColWidth);
        printFormatted("[House Type]", houseAndFeeDTO.getHouseType().toString(), firstColWidth, secondColWidth);
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        printFormatted("[Weekday Cost]", String.valueOf(houseAndFeeDTO.getWeekday()), firstColWidth, secondColWidth);
        printFormatted("[Weekend Cost]", String.valueOf(houseAndFeeDTO.getWeekend()), firstColWidth, secondColWidth);
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        printFormatted("[House Info]", houseAndFeeDTO.getHouseIntroduce(), firstColWidth, secondColWidth);

        List<AmenitiesDTO> amenitiesList = moreHouseInfoDTO.getAmenitiesDTOList();
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        System.out.format("弛                           Basic Amenities                              弛                                                                                                           弛%n");
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        if (amenitiesList != null) {
            for (AmenitiesDTO amenitiesDTO : amenitiesList) {
                if (amenitiesDTO.getTypeId() == 1) {
                    printFormatted(amenitiesDTO.getAmenities(), "", firstColWidth, secondColWidth);
                }
            }
        }
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");

        System.out.format("弛                          Safety Amenities                              弛                                                                                                           弛%n");
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        if (amenitiesList != null) {
            for (AmenitiesDTO amenitiesDTO : amenitiesList) {
                if (amenitiesDTO.getTypeId() == 2) {
                    printFormatted(amenitiesDTO.getAmenities(), "", firstColWidth, secondColWidth);
                }
            }
        }
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");

        System.out.format("弛                       Accessibility Amenities                          弛                                                                                                           弛%n");
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        if (amenitiesList != null) {
            for (AmenitiesDTO amenitiesDTO : amenitiesList) {
                if (amenitiesDTO.getTypeId() == 3) {
                    printFormatted(amenitiesDTO.getAmenities(), "", firstColWidth, secondColWidth);
                }
            }
        }
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");

        List<UserReviewDTO> userReviewDTOS = moreHouseInfoDTO.getReviewDTOList();
        System.out.format("弛                                Review                                  弛                                                                                                           弛%n");
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");

        int reviewWidth1 = 65;
        int reviewWidth2 = 105;
        if (userReviewDTOS != null) {
            for (UserReviewDTO userReviewDTO : userReviewDTOS) {
                printFormatted("User Name: " + userReviewDTO.getUserName(), "", firstColWidth, secondColWidth);
                printFormatted("STAR: " + userReviewDTO.toString(), "", firstColWidth, secondColWidth);
                printFormatted("REVIEW: " + userReviewDTO.getReview(), "", firstColWidth, secondColWidth);

                if (replyDTOList != null) {
                    printFormatted("忙式式式式式式式式式式式式式式式式式式式式式式式式式 <HOST REPLY> 式式式式式式式式式式式式式式式式式式式式式式式式式式式忖", "", firstColWidth, secondColWidth);
                    for (ReplyDTO replyDTO : replyDTOList) {
                        if (replyDTO.getReservationId() == userReviewDTO.getReservationId()) {
                            printFormattedReview(replyDTO.toString(), "", reviewWidth1, reviewWidth2);
                        }
                    }
                    printFormatted("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎", "", firstColWidth, secondColWidth);
                }
            }
        }
        System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");


        System.out.println("                                                                             忙式式                          式式忖");
        System.out.println("                                                                             弛  (1) Reservation  (2) Back   弛");
        System.out.println("                                                                             戌式式                          式式戎");
        System.out.print("                                                                                       Selection : ");
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
        System.out.println("[Show Reservation available dates]");//////////殖溘 旋濠 熱 �挫恉炾� 轎溘ж晦

        if (reservationDTOList != null) {
//           MyCalender.print(reservationDTOList);
            CalendarViewerForAdmin.run(reservationDTOList, houseAndFeeDTO);
        } else {
            System.out.println("                                                                         忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
            System.out.println("                                                                         弛 All Date is Possible to Reservation  弛");
            System.out.println("                                                                         戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
        }

        System.out.format("                                                      忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
        System.out.format("                                                      弛                                                                           弛%n");
        System.out.format("                                                                                                                                   %n");

        System.out.print("                                                                               Adult Num : ");
        int adultNum = MyIOStream.sc.nextInt();
        System.out.print("                                                                                Child Num : ");
        int childNum = MyIOStream.sc.nextInt();
        System.out.print("                                                                       Enter CheckIn Day (YYYY-MM-DD) : ");
        MyIOStream.sc.nextLine();
        String checkIn = MyIOStream.sc.next();
        System.out.print("                                                                       Enter CheckOut Day (YYYY-MM-DD) : ");
        String checkOut = MyIOStream.sc.next();
        int totalNum = adultNum + childNum;

        int cost = 0;

        if (houseAndFeeDTO.getHouseType() == HouseType.PRIVATE) {
            if (discountRate > 0) {
                cost = SaleCalculator.CalculateRate(checkIn, checkOut, discountPolicyDTO, feePolicyDTO, totalNum);
            } else {
                cost = SaleCalculator.CalculateAmount(checkIn, checkOut, discountPolicyDTO, feePolicyDTO, totalNum);
            }
        } else {
            if (discountRate > 0) {
                cost = SaleCalculator_2.CalculateRate(checkIn, checkOut, discountPolicyDTO, feePolicyDTO, totalNum);
            } else {
                cost = SaleCalculator_2.CalculateAmount(checkIn, checkOut, discountPolicyDTO, feePolicyDTO, totalNum);
            }
        }
        System.out.println("                                                                             忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
        System.out.format("                                                                                    Cost = %s $             \n", cost);
        System.out.println("                                                                             戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");

        System.out.println("                                                                     忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
        System.out.println("                                                                     弛   Would you like to make a reservation?   弛");
        System.out.println("                                                                     戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
        System.out.println("                                                                     弛             (1) YES!   (2) NO!            弛");
        System.out.println("                                                                     戌式式                                       式式戎");
        System.out.print("                                                                                      Selection : ");

        int enter = MyIOStream.sc.nextInt();

        if (enter == 1) {
            ReservationRequestController reservationRequestController = new ReservationRequestController();
            Protocol protocol = reservationRequestController.reservationRequest(houseAndFeeDTO.getHouseId(), userDTO.getUserId(), totalNum, checkIn, checkOut, cost);

            if (protocol.getProtocolCode() == Protocol.CODE_SUCCESS) {
                System.out.println("                                                                             忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                System.out.println("                                                                             弛    Success to Reservation    弛");
                System.out.println("                                                                             戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
            } else {
                System.out.println(protocol.getObject());
            }
        } else if (enter == 2) {
            System.out.println("                                                                             忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
            System.out.println("                                                                             弛           CANCEL!!           弛");
            System.out.println("                                                                             戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
        } else {
            System.out.println("Wrong Input..");
        }
    }

    private void printDescendingList(SearchAllHouseController searchAllHouseController) throws
            Exception {
        Protocol protocol = searchAllHouseController.descendingHouseRequest();
        List<HouseAndFeeDTO> descendingList = (List<HouseAndFeeDTO>) protocol.getObject();
        System.out.format("                                             忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
        System.out.format("                                             弛                                         DESCENDING LIST                                       弛%n");
        System.out.format("                                             戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");

        if (descendingList != null) {
            printHouseList(descendingList);

            int listCommand = getHouseListCommand();

            if (listCommand == 4) {
                System.out.println("Back..");
            } else {
                switch (listCommand) {
                    case 1:
                        // 螃葷離牖
                        printAscendingList(searchAllHouseController);
                        break;
                    case 2:
                        // 頂葡離牖
                        printDescendingList(searchAllHouseController);
                        break;
                    case 3:
                        System.out.println("                                                                         忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                        System.out.println("                                                                         弛 Enter Num to Reservation (Back : -1) 弛");
                        System.out.println("                                                                         戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
                        System.out.print("                                                                                      Enter : ");

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
        System.out.format("                                             忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
        System.out.format("                                             弛                                          ASCENDING LIST                                       弛%n");
        System.out.format("                                             戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");

        if (ascendingList != null) {
            printHouseList(ascendingList);

            int listCommand = getHouseListCommand();

            if (listCommand == 4) {
                System.out.println("Back..");
            } else {
                switch (listCommand) {
                    case 1:
                        // 螃葷離牖
                        printAscendingList(searchAllHouseController);
                        break;
                    case 2:
                        // 頂葡離牖
                        printDescendingList(searchAllHouseController);
                        break;
                    case 3:
                        System.out.println("                                                                         忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖");
                        System.out.println("                                                                         弛 Enter Num to Reservation (Back : -1) 弛");
                        System.out.println("                                                                         戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎");
                        System.out.print("                                                                                      Enter : ");

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
        System.out.format("忙式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式忖%n");
        System.out.format("弛                             <HOUSE LIST>                               弛                                                                                                           弛%n");
        System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");

        for (HouseAndFeeDTO houseAndFeeDTO : houseList) {
//            System.out.printf("%d. %s\n", ++i, houseAndFeeDTO.toString());
            printFormatted("[" + (++i) + "] " + houseAndFeeDTO.getHouseName(), "[House Address] " + houseAndFeeDTO.getHouseAddress(), firstColWidth, secondColWidth);
            printFormatted("", "[Weekday Cost] " + houseAndFeeDTO.getWeekday(), firstColWidth, secondColWidth);
            printFormatted("", "[Weekend Cost] " + houseAndFeeDTO.getWeekend(), firstColWidth, secondColWidth);
            printFormatted("", "[Bedroom] " + houseAndFeeDTO.getBedroom(), firstColWidth, secondColWidth);
            printFormatted("", "[House Type] " + houseAndFeeDTO.getHouseType().toString(), firstColWidth, secondColWidth);
            System.out.format("戍式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式托式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扣%n");
        }
        System.out.format("戌式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式戎%n");

    }

    private static void printFormattedReview(String label, String text, int labelWidth, int textWidth) {
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
                System.out.printf("弛 弛 %-" + labelWidth + "s 弛  弛%-" + textWidth + "s   弛%n", currentLabel, textLine.toString().trim());
                textLine.setLength(0);
                labelIndex++;
            }
            textLine.append(word).append(" ");
        }
        // 臢蝶お曖 葆雖虞 還婁 陴擎 塭漣 還 轎溘
        while (labelIndex < formattedLabel.size() || textLine.length() > 0) {
            String currentLabel = labelIndex < formattedLabel.size() ? formattedLabel.get(labelIndex) : "";
            String currentText = textLine.toString().trim();
            System.out.printf("弛 弛 %-" + labelWidth + "s弛   弛%-" + textWidth + "s  弛%n", currentLabel, currentText);
            textLine.setLength(0);
            labelIndex++;
        }
    }
}
