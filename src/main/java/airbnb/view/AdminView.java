package airbnb.view;

import java.io.IOException;
import java.util.Scanner;

public class AdminView {

    private Scanner sc = new Scanner(System.in);
    int firstColWidth = 30; // First column width
    int secondColWidth = 60; // Second column width
    String leftAlignFormat = "| %-" + firstColWidth + "s | %-" + secondColWidth + "s |%n";

    public void showView() throws IOException {
        while (true) {
            System.out.format("┌────────────────────────┬────────────────────────────────────────────────────┐%n");
            System.out.format("│   <Admin Page>         │                                                    │%n");
            System.out.format("├────────────────────────┼────────────────────────────────────────────────────┤%n");
            System.out.format("│ 1. Manage Requests     │                                                    │%n");
            System.out.format("│ 2. Reservation Status  │                                                    │%n");
            System.out.format("│ 3. Monthly Revenue     │                                                    │%n");
            System.out.format("│ 0. Logout              │                                                    │%n");
            System.out.format("└────────────────────────┴────────────────────────────────────────────────────┘%n");

            System.out.print("Enter command: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    manageAccommodationRequests();
                    break;
                case 2:
                    viewMonthlyReservationStatus();
                    break;
                case 3:
                    viewMonthlyRevenue();
                    break;
                case 0:
                    return; // 로그아웃 및 뷰 종료
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void manageAccommodationRequests() {
        System.out.format("┌────────────────────────────────┬──────────────────────────────────────────────────────────────┐%n");
        System.out.format("│ 1. Pending Accommodations      │                                                              │%n");
        System.out.format("│ 2. Rejected Accommodations     │                                                              │%n");
        System.out.format("│ 3. Go Back                     │                                                              │%n");
        System.out.format("└────────────────────────────────┴──────────────────────────────────────────────────────────────┘%n");
        System.out.print("Enter command: ");

        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                // 미승인 숙박 시설 목록
                System.out.format("┌────────────────────────────────┬──────────────────────────────────────────────────────────────┐%n");
                System.out.format("│ Unapproved Accommodation List  │                                                              │%n");
                System.out.format("├────────────────────────────────┼──────────────────────────────────────────────────────────────┤%n");
                printFormatted("Line", "", firstColWidth, secondColWidth);
                // 위 printFormatted를 이용해서 Line에 DTO에서 가져온걸 넣고 두번째부터는 통일
                System.out.format("└────────────────────────────────┴──────────────────────────────────────────────────────────────┘%n");
                System.out.println("Please select the unapproved accommodation : ");

                System.out.println("┌──                          ──┐");
                System.out.println("│ (1) Approval  (2) Rejection  │");
                System.out.println("└──                          ──┘");
                System.out.println("          Selection : ");

                // 리스트업 숙박 시설 중 선택하면 "승인" "거절" 중 선택하고 메시지 출력
                System.out.println("Accommodation request from ~~ has been approved.");
                System.out.println("Accommodation request from ~~ has been rejected.");
                break;
            case 2:
                // 거절된 숙박 시설 목록
                System.out.format("┌────────────────────────────────┬──────────────────────────────────────────────────────────────┐%n");
                System.out.format("│  Rejected Accommodation List   │                                                              │%n");
                System.out.format("├────────────────────────────────┼──────────────────────────────────────────────────────────────┤%n");
                printFormatted("Line", "", firstColWidth, secondColWidth);
                // 위 printFormatted를 이용해서 Line에 DTO에서 가져온걸 넣고 두번째부터는 통일
                System.out.format("└────────────────────────────────┴──────────────────────────────────────────────────────────────┘%n");
                System.out.println("Press Any Key : ");

            case 0:
                return; // 뒤로가기
            default:
                System.out.println("Invalid input. Please try again.");}
    }

    private void viewMonthlyReservationStatus() {
        // 선택하면 등록된 숙소들이 리스트업 되고 숙소를 선택하면 그 숙소에 대한 예약 현황을 달력으로 출력 calender() 참고
        System.out.format("┌────────────────────────────────┬──────────────────────────────────────────────────────────────┐%n");
        System.out.format("│ Registered Accommodations List │                                                              │%n");
        System.out.format("├────────────────────────────────┼──────────────────────────────────────────────────────────────┤%n");
        printFormatted("Line", "", firstColWidth, secondColWidth);
        // 위 printFormatted를 이용해서 Line에 DTO에서 가져온걸 넣고 두번째부터는 통일
        System.out.format("└────────────────────────────────┴──────────────────────────────────────────────────────────────┘%n");
        System.out.println("Please select the accommodation : ");

        System.out.println("Enter the year and month to check(YYYY MM): ");
    }

    private void viewMonthlyRevenue() {
        System.out.format("┌────────────────────────────────┬──────────────────────────────────────────────────────────────┐%n");
        System.out.format("│ Registered Accommodations List │                                                              │%n");
        System.out.format("├────────────────────────────────┼──────────────────────────────────────────────────────────────┤%n");
        printFormatted("Line", "", firstColWidth, secondColWidth);
        // 위 printFormatted를 이용해서 Line에 DTO에서 가져온걸 넣고 두번째부터는 통일
        System.out.format("└────────────────────────────────┴──────────────────────────────────────────────────────────────┘%n");

        System.out.println("Please select the accommodation : ");

        System.out.println("Enter the year and month to check(YYYY MM): ");
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
    // 숙소 목록을 불러오는 메서드 등 추가적으로 필요할 것으로 예상
}
