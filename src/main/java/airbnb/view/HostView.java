package airbnb.view;

import java.io.IOException;
import java.util.Scanner;

public class HostView {

    private Scanner sc = new Scanner(System.in);

    public void showView() throws IOException {
        while (true) {
            System.out.println("\n===== 호스트 관리 메뉴 =====");
            System.out.println("1. 숙박 등록");
            System.out.println("2. 요금 정책 설정");
            System.out.println("3. 할인 정책 설정");
            System.out.println("4. 숙박 예약 현황");
            System.out.println("5. 예약 승인 / 거절");
            System.out.println("6. 리뷰 관리");
            System.out.println("0. 로그아웃");
            System.out.print("메뉴를 선택하세요: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    registerAccommodation();
                    break;
                case 2:
                    setPricingPolicy();
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
                case 0:
                    break; // 로그아웃 및 뷰 종료
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }
    }

    // HostView 클래스 내의 다른 메서드들

    private void setPricingPolicy() {
        System.out.println("##### 주말 / 평일 요금 설정 #####");
        System.out.print("주말 요금(원) : ");
        int weekendRate = sc.nextInt();
        System.out.print("평일 요금(원) : ");
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

    // 추가적인 메서드로 숙박 등록을 위한 registerAccommodation() 메서드
    private void registerAccommodation() {
        System.out.println("1. 숙박 등록");
        System.out.print("숙소 이름 작성 : ");
        String name = sc.next();
        System.out.print("숙소 소개 작성(500자 이내) : ");
        String description = sc.next();
        System.out.println("객실 타입 선택");
        System.out.println("1. 개인실\n2. 집 전체");
        int roomType = sc.nextInt();
        System.out.print("수용 정보 작성(최대 인원 설정) : ");
        int capacity = sc.nextInt();
        System.out.println("----편의시설 선택----\n" +
                "1.무선 인터넷 2.주방 3.세탁기 4.건조기 5.에어컨난방\n" +
                "6.업무전용 공간 7.TV 8.헤어드라이어 9.다리미 10.수영장\n" +
                "11.온수 욕조 12.무료 주차 공간 13.아기 침대 14.킹사이즈 침대 15.헬스장\n" +
                "16.바비큐 그릴 17.아침식사 18.실내 벽난로 19.흡연 가능 20.화재경보기\n" +
                "21.일산화탄소 경보기");
        // 편의시설 선택 로직
        // 숙박 등록 로직
    }
}
