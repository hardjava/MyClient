package airbnb.view;

import java.io.IOException;
import java.util.Scanner;

public class AdminView {

    private Scanner sc = new Scanner(System.in);

    public void showView() throws IOException {
        while (true) {
            System.out.println("\n===== 관리자 메뉴 =====");
            System.out.println("1. 숙소 등록 신청 목록 - 승인 / 거절");
            System.out.println("2. 숙소별 월별 예약 현황");
            System.out.println("3. 숙소별 월별 총 매출");
            System.out.println("0. 로그아웃");
            System.out.print("메뉴를 선택하세요: ");

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
                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }
    }

    private void manageAccommodationRequests() {
        System.out.println("1. 미승인 숙박 시설 목록"); // 선택시 미승인 숙박 시설 리스트업
        System.out.println("2. 거절된 숙박 시설 목록"); // 선택시 거절된 숙박 시설 리스트업
        System.out.println("3. 뒤로가기");

        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                // 미승인 숙박 시설 목록
                // 리스트업 숙박 시설 중 선택하면 "승인" "거절" 중 선택하고 메시지 출력
                System.out.println("~~님의 숙소 등록 신청이 승인되었습니다.");
                System.out.println("~~님의 숙소 등록 신청이 거절되었습니다.");
            case 2:
                // 거절된 숙박 시설 목록
            case 0:
                return; // 뒤로가기
            default:
                System.out.println("잘못된 입력입니다. 다시 시도하세요.");
        }
    }

    private void viewMonthlyReservationStatus() {
        // 선택하면 등록된 숙소들이 리스트업 되고 숙소를 선택하면 그 숙소에 대한 예약 현황을 달력으로 출력 calender() 참고
        System.out.println("---등록된 숙박 시설 목록---");
        // 리스트업
        System.out.println("확인할 년, 월을 입력하시오 : ");
    }

    private void viewMonthlyRevenue() {
        System.out.println("3. 숙소별 월별 총 매출");
        System.out.println("---등록된 숙박 시설 목록---");
        // 리스트업
        System.out.println("확인할 년, 월을 입력하시오 : ");
    }

    // 숙소 목록을 불러오는 메서드 등 추가적으로 필요할 것으로 예상
}
