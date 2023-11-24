package airbnb.network;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Protocol implements Serializable {
    public static final int TYPE_UNDEFINED = 0;
    public static final int CODE_UNDEFINED = 0;
    public static final int CODE_SUCCESS = 100;
    public static final int CODE_ERROR= -100;
    //=============================
    public static final int TYPE_SIGN_UP = 0;                   // 회원가입
    public static final int CODE_SEND_SIGN_UP_INFO = 1;         // 회원가입 정보 전달(클라이언트 → 서버)----
//    public static final int CODE_SIGN_UP_SUCCESS = 2;           // 회원가입 성공(서버 → 클라이언트)
//    public static final int CODE_SIGN_UP_FAIL = 3;              // 회원가입 실패(서버 → 클라이언트)

    public static final int TYPE_LOGIN = 1;                     // 로그인
    public static final int CODE_LOGIN_REQUEST = 1;             // 로그인 요청(클라이언트 -> 서버)----
//    public static final int CODE_LOGIN_ACCEPT = 2;              // 로그인 수락
//    public static final int CODE_LOGIN_FAIL = 3;                // 로그인 실패

    public static final int TYPE_PERSONAL_INFO_EDIT = 2;        // 개인정보수정
    public static final int CODE_PERSONAL_INFO_REQUEST = 1;     // 개인정보요청 ----
    public static final int CODE_SEND_PERSONAL_INFO = 2;        // 개인정보전달(서버 → 클라이언트)
    public static final int CODE_SEND_MODIFY_NAME_INFO = 3; // 수정 된 개인정보 전달(클라이언트 → 서버)----
    public static final int CODE_SEND_MODIFY_PHONENUMBER_INFO = 4;
    public static final int CODE_SEND_MODIFY_BIRTHDAY_INFO = 5;
//    public static final int CODE_PERSONAL_INFO_MODIFY_SUCCESS = 6; //수정성공
//    public static final int CODE_PERSONAL_INFO_MODIFY_FAIL = 7; //수정실패

    public static final int TYPE_SEARCH_RESERVATION = 3;        // 예약 현황 조회
    public static final int CODE_MY_RESERVATION_REQUEST = 1;    // 예약 현황 요청----
    public static final int CODE_SEND_RESERVATION = 2;          // 예약현황정보전달(서버→클라이언트)
    public static final int CODE_REQUEST_RESERVATION_CANCELLATION = 3; //예약 취소요청----


    public static final int TYPE_WRITTEN_REVIEW = 4;            // 작성한 리뷰
    public static final int CODE_WRITTEN_REVIEW_REQUEST = 1;    // 작성한 리뷰 요청----
    public static final int CODE_SEND_REVIEW_INFO = 2;          // 리뷰정보 전달(서버 → 클라이언트)
    public static final int CODE_SEND_MODIFY_REVIEW = 3;        // 수정된 리뷰정보 전달(클라이언트 → 서버)----
//    public static final int CODE_MODIFY_REVIEW_SUCCESS = 4;     // 수정성공
//    public static final int CODE_MODIFY_REVIEW_FAIL = 5;        // 수정실패

    public static final int TYPE_STAYED_HOUSE = 5;              // 이용한 숙소 리스트
    public static final int CODE_STAYED_HOUSE_LIST_REQUEST = 1; // 이용한 숙소 리스트 요청----
    public static final int CODE_SEND_STAYED_HOUSE_LIST = 2;    // 숙소리스트 정보 전달(서버→클라이언트)

    public static final int TYPE_SEND_REVIEW = 6;               // 숙소 선택후 별점과 후기 전달
    public static final int CODE_SEND_REVIEW = 1;               // 별점과 후기 전달(클라이언트→ 서버)----

    public static final int TYPE_SEARCH_ALL_HOUSE = 7;          // 전체 숙소 목록 조회
    public static final int CODE_SEARCH_ALL_HOUSE_REQUEST = 1;  // 등록된 전체 숙소 목록 요청----
    public static final int CODE_SEND_ALL_HOUSE = 2;            // 전체 숙소 목록 전달(서버 →클라이언트)


    public static final int TYPE_SELECT_HOUSE_VIEW_DETAIL = 8;  // 선택한 숙소 상세보기
    public static final int CODE_SELECT_HOUSE_INFO_REQUEST = 1; // 선택한 숙소 정보 요청----
    public static final int CODE_SEND_SELECT_HOUSE_INFO = 2;    // 선택한 숙소 정보 전달(서버 → 클라이언트)

    public static final int TYPE_REQUEST_RESERVATION = 9;       // 예약 신청
    public static final int CODE_SEND_RESERVATION_INFO = 1;     // 체크인, 체크아웃, 인원수 등 정보전달(클라이언트 → 서버)----
    public static final int CODE_RESERVATION_SUCCESS = 2;       // 예약 성공 전달(서버 → 클라이언트)
    public static final int CODE_RESERVATION_FAIL = 3;          // 예약 실패 전달( 서버 → 클라이언트)
    //===============================================


    public static final int TYPE_HOUSE_REGISTRATION = 10;           // 숙박 등록
    public static final int CODE_SEND_REGISTRATION_HOUSE_INFO = 1;  // 숙박 등록 정보 전달(클라이언트 → 서버)----
//    public static final int CODE_REGISTRATION_SUCCESS = 2;          // 등록 성공 전달(서버 → 클라이언트)
//    public static final int CODE_REGISTRATION_FAIL = 3;             // 등록 실패 전달(서버 → 클라이언트)

    public static final int TYPE_VIEW_MY_HOUSE = 11;                // 등록된 숙박 업소 보기
    public static final int CODE_REQUEST_MY_HOUSE_LIST = 1;         // 숙박 목록 요청(클라이언트→서버)----
    public static final int CODE_SEND_MY_HOUSE_LIST = 2;            // 숙박 목록 정보 전달(서버 → 클라이언트)

    public static final int TYPE_SET_COST_POLICY = 12;                          // 등록된 숙박 업소 요금 정책 설정
    public static final int CODE_REQUEST_DIVISION_COST_POLICY_HOUSE_LIST = 1;   // 요금 정책이 설정된 시설과 아직 설정안된 시설 구분하여 요청----
    public static final int CODE_SEND_DIVISION_COST_POLICY = 2;                 // 요금 정책이 설정된 시설과 아직 설정안된 시설 구분 리스트 전달
    public static final int CODE_SEND_WEEKEND_WEEKDAYS_COST_POLICY = 3;         // 주말 / 평일 요금 정보 전달(클라이언트 → 서버)----
    public static final int CODE_REQUEST_APPROVED_NOT_SET_FEE_POLICY_HOUSE_LIST = 4; // 승인됐지만 요금 설정이 안된 숙박 목록 요청(클라이언트 → 서버)
//    public static final int CODE_WEEKEND_WEEKDAYS_COST_POLICY_MODIFY_SUCCESS = 4; // 정책설정성공
//    public static final int CODE_WEEKEND_WEEKDAYS_COST_POLICY_MODIFY_FAIL = 5;     // 정책설정실패

    public static final int TYPE_SET_DISCOUNT_POLICY = 13;                      // 할인 정책 설정

    public static final int TYPE_SET_DISCOUNT_POLICY_ON_CONSECUTIVE_NIGHTS = 14;    // 연박 할인 적용 기간 설정
    public static final int CODE_SEND_DISCOUNT_POLICY_ON_CONSECUTIVE_NIGHTS = 1;    // 할인 적용 정보 전달(클라이언트 → 서버)----
    public static final int CODE_APPLY_DISCOUNT_TO_EXISTING_RESERVATIONS = 2;       // 이전 예약 건에 대해서도 할인 적용 설정----

    public static final int TYPE_SET_DISCOUNT_ON_QUANTITY_OR_FLAT = 15;         // 정량 / 정률 할인 설정
    public static final int CODE_SEND_DISCOUNT_ON_QUANTITY_OR_FLAT = 1;         // 할인 적용 정보 전달(클라이언트 → 서버)----

    public static final int TYPE_MY_HOUSE_INFO_EDIT = 16;                       // 등록된 숙박 업소 정보 수정
    public static final int CODE_SEND_EDITED_HOUSE_INFO = 1;                    // 수정된 숙박 정보 전달(클라이언트 → 서버)----

    public static final int TYPE_VIEW_HOUSE_RESERVATION = 17;                   // 숙박 예약 현황
    public static final int CODE_PRINT_HOUSE_RESERVATION = 1;                   // 예약 정보가 담긴 달력 출력(서버 → 클라이언트)

    public static final int TYPE_RESERVATION_ALLOW_OR_REFUSE = 18;              // 예약 승인 / 거절
    public static final int CODE_REQUEST_WAITING_FOR_RESERVATION_APPROVAL = 1;  // 예약 승인 대기 리스트 요청(클라이언트→서버)----
    public static final int CODE_SEND_WAITING_FOR_RESERVATION_APPROVAL = 2;     // 예약 승인 대기 리스트 전달(서버 → 클라이언트)
    public static final int CODE_REQUEST_RESERVATION_REFUSE_LIST = 3;           // 예약 거절 리스트 요청(클라이언트→서버)----
    public static final int CODE_SEND_RESERVATION_REFUSE_LIST = 4;              // 예약 거절 리스트 전달(서버 → 클라이언트)

    public static final int TYPE_REVIEW_MANAGEMENT = 19;                        // 리뷰 관리
    public static final int CODE_REQUEST_MOST_RECENT_REVIEW_LIST = 1;           // 최근순 리뷰 리스트 요청(클라이언트→서버)----
    public static final int CODE_SEND_MOST_RECENT_REVIEW_LIST = 2;              // 최근순 리뷰 리스트 전달(서버 → 클라이언트)
    public static final int CODE_SEND_REPLYING_TO_REVIEW = 3;                   // 리뷰 답글 작성 정보 전달(클라이언트 → 서버)----
    //=============================================

    public static final int TYPE_VIEW_ACCOMMODATION_REGISTRATION_LIST = 20;         // 숙소 등록 신청 목록보기
    public static final int CODE_REQUEST_ACCOMMODATION_REGISTRATION_LIST = 1;       // 등록 신청된 숙소 목록 요청(클라이언트 → 서버)----
    public static final int CODE_SEND_ACCOMMODATION_REGISTRATION_LIST = 2;          // 등록 신청된 숙소 목록 전달(서버 → 클라이언트)
    public static final int CODE_SEND_APPROVAL_OR_REJECT_INFORMATION = 3;           // 승인 / 거절 정보 전달(클라이언트 → 서버)----
//    public static final int CODE_SEND_APPROVAL_OR_REJECT_INFORMATION_SUCCESS = 4;   // 승인 / 거절 성공 전달(서버 → 클라이언트)
//    public static final int CODE_SEND_APPROVAL_OR_REJECT_INFORMATION_FAIL = 5;      // 승인 / 거절 실패 전달(서버 → 클라이언트)
//    public static final int CODE_REQUEST_REJECTED_ACCOMMODATION_LIST = 4;           // 거절된 숙소 목록 요청(클라이언트 → 서버)----
//    public static final int CODE_SEND_REJECTED_ACCOMMODATION_LIST = 5;              // 거절된 숙소 목록 전달(클라이언트 → 서버)----

    public static final int TYPE_MONTHLY_RESERVATION_STATUS_FOR_ACCOMMODATION = 21; // 숙소별 월별 예약 현황
    public static final int CODE_SEND_CALENDAR_WITH_RESERVATION_DETAILS = 1;        // 해당 월 예약 정보가 담긴 달력 출력(서버 → 클라이언트)
    public static final int CODE_REQUEST_CALENDAR_WITH_RESERVATION_DETAILS = 2;     // 해달월 예약 정보가 담긴 달력 요청(클라이언트 -> 서버)

    public static final int TYPE_MONTHLY_TOTAL_REVENUE_FOR_ACCOMMODATION = 22;      // 숙소별 월별 총매출
    public static final int CODE_SEND_TOTAL_SALES_FOR_MONTH = 1;                    // 해당 월 총 매출 전달(서버 → 클라이언트)

    //=============================================
    public static final int TYPE_FILTER = 23;                                       //필터
    public static final int CODE_SEND_SELECT_FILTER = 1;                            // 필터 옵션을 정해서 전달(클라이언트 -> 서버)
    public static final int CODE_SEND_FILTERED_HOUSE_LIST = 2;                      // 필터가 적용된 숙소 목록 전달(서버 ->클라이언트)




    //오류 코드
//    public static final int CODE_ERROR = -1;


    private int protocolType;
    private int protocolCode;
    private Object object;

    // 생성자
    public Protocol() {
        this.protocolType = TYPE_UNDEFINED;
        this.protocolCode = CODE_UNDEFINED;
    }

    public Protocol(int protocolType) {
        this.protocolType = protocolType;
        this.protocolCode = CODE_UNDEFINED;
    }

    public Protocol(int protocolType, int protocolCode) {
        this.protocolType = protocolType;
        this.protocolCode = protocolCode;
    }

    public Protocol(int protocolType, int protocolCode, Object object) {
        this.protocolType = protocolType;
        this.protocolCode = protocolCode;
        this.object = object;
    }
}

