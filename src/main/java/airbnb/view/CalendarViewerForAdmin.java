package airbnb.view;

import airbnb.persistence.dto.HouseAndFeeDTO;
import airbnb.persistence.dto.HouseDTO;
import airbnb.persistence.dto.ReservationDTO;
import org.w3c.dom.Node;

import javax.swing.*;
import java.rmi.NoSuchObjectException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CalendarViewerForAdmin {

    public static void run(List<ReservationDTO> inputList, HouseAndFeeDTO houseAndFeeDTO){

        List<Date> dateList = new ArrayList<>();

        for (int i = 0; i < inputList.size(); i++) {
            dateList = makeList(inputList.get(i).getCheckIn(), inputList.get(i).getCheckOut(), dateList);
        }


        HashSet<Integer> monthSet = new HashSet<>(); // 월을 저장할 HashSet 생성
        Calendar cal = Calendar.getInstance(); // Calendar 객체 생성

        for (Date date : dateList) {//예약된 달을 저장함. 밑 스위치로 들어감
            cal.setTime(date); // Date 객체를 Calendar 객체로 변환
            monthSet.add(cal.get(Calendar.MONTH) + 1); // 월을 얻어와 HashSet에 추가, Calendar.MONTH는 0부터 시작하므로 +1
        }

        List<Integer> monthList = new ArrayList<>(monthSet); // 월 정보를 담은 HashSet을 ArrayList로 변환

        for (int i = 0; i < monthList.size(); i++) {
            selectMonth(monthList.get(i), inputList, houseAndFeeDTO);
            System.out.println();
        }

    }

    private static void inputDate(int month,List<ReservationDTO> inputList, HouseAndFeeDTO houseAndFeeDTO ){
//        int[] arr;
//        List <DateAndGuest> objectList = new ArrayList<>();
//        String checkInDateString = "2023-11-01";
//        String checkOutDateString = "2023-11-02";
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date checkInDate = dateFormat.parse(checkInDateString);
//        Date checkInDate_1 = dateFormat.parse(checkOutDateString);
//
//        DateAndGuest dateAndGuest = new DateAndGuest(checkInDate,2);
//        DateAndGuest dateAndGuest_1 = new DateAndGuest(checkInDate_1,8);
//        DateAndGuest dateAndGuest_2 = new DateAndGuest(checkInDate,3);
//        objectList.add(dateAndGuest);
//        objectList.add(dateAndGuest_1);
//        objectList.add(dateAndGuest_2);
//
//
//        List<Date> list = new ArrayList<>();
//        list.add(checkInDate);
//        list.add(checkInDate_1);
//
//        arr = returnArr(objectList,8);
//        view(2, list,arr);
        //=================================
        int[] arr;
        List<Date> list = new ArrayList<>();
        List <DateAndGuest> objectList = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i++) {
            objectList = makeList_1(inputList.get(i).getCheckIn(),inputList.get(i).getCheckOut(),objectList,inputList.get(i).getGuestNum());
        }

        for (int i = 0; i < objectList.size(); i++) {
            list.add(objectList.get(i).getDate());
        }


        arr = returnArr(objectList,houseAndFeeDTO.getBedroom(),month);
        view(month, list,arr);

    }



    private static void view(int monthValue, List<Date> list,int[] arr) {

        Collections.sort(list);
        ArrayList<Integer> arrayList = new ArrayList<>();

        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");

        for (int i = 0; i < list.size(); i++) {
            if (Integer.parseInt(monthFormat.format(list.get(i))) == monthValue) {
                arrayList.add(Integer.parseInt(dayFormat.format(list.get(i))));
            }
        }


        // Calendar 객체 생성
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, monthValue - 1, 1); // 입력한 년도와 월로 설정

        // 해당 월의 첫 번째 날짜와 마지막 날짜 구하기
        int firstDay = calendar.get(Calendar.DAY_OF_WEEK); // 해당 월의 첫 번째 날의 요일(일: 1, 월: 2, ..., 토: 7)
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 해당 월의 마지막 날짜

        ArrayList<String> arrayList_1 = new ArrayList<>();
        ArrayList<String> arrayList_2 = new ArrayList<>();

        for (int i = 1; i < firstDay; i++) {
            arrayList_1.add(" ");
            arrayList_2.add(" ");
        }
        for (int i = 1; i <= lastDay; i++) {
            if (!arrayList.isEmpty()) {
                arrayList_1.add(Integer.toString(i));
                arrayList_2.add("*");
            } else {
                arrayList_1.add(Integer.toString(i));
                arrayList_2.add("*");
            }

        }

        //=========================
        System.out.println("##################################"+monthValue+"##################################");

        System.out.println("SUN         MON         TUE         WED         THU         FRI         SAT");
        int count_1 = 0;
        for (int i = 0; i < (arrayList_1.size() / 5) - 1; i++) {
            for (int j = count_1; j < count_1 + 7; j++) {
                if (j > arrayList_1.size() - 1) {

                } else {
                    if(arrayList_1.get(j).equals(" ")){
                        System.out.printf("%-12s", arrayList_1.get(j));


                    }else{
                        System.out.print("|");
                        System.out.printf("%-11s", arrayList_1.get(j));
                    }


                }
            }
            System.out.println();
            for (int j = count_1; j < count_1 + 7; j++) {
                if (j > arrayList_2.size() - 1) {

                } else {
//                    System.out.printf("%-5s", arrayList_2.get(j));
                    if(arrayList_1.get(j).equals(" ")){
                        System.out.printf("%-12s", " ");
                    }else{
                        System.out.print("ROOM : ");
                        if(arr[Integer.parseInt(arrayList_1.get(j))] ==0){
                            System.out.printf("%-5s", "X");
                        }else{
                            System.out.printf("%-5d", arr[Integer.parseInt(arrayList_1.get(j))]);

                        }

                    }

                }
            }
            System.out.println();
            System.out.println();
            count_1 += 7;


        }
    }

    public static int[] returnArr(List<DateAndGuest> list, int max,int month){
        int[] arr = new int[32];

        for (int i = 0; i < list.size(); i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(list.get(i).getDate());

            // 현재 날짜의 일 정보 얻기
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            int Month = calendar.get(Calendar.MONTH) + 1;
            if(Month == month){
                arr[dayOfMonth] +=list.get(i).getGuestNum();

            }
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = max - arr[i];
        }
        return arr;
    }


    public static void selectMonth(int val, List<ReservationDTO> reservationDTOList, HouseAndFeeDTO houseAndFeeDTO) {

        switch (val) {
            case 1:
                inputDate(1, reservationDTOList,houseAndFeeDTO);
                break;
            case 2:
                inputDate(2, reservationDTOList,houseAndFeeDTO);
                break;
            case 3:
                inputDate(3, reservationDTOList,houseAndFeeDTO);
                break;
            case 4:
                inputDate(4, reservationDTOList,houseAndFeeDTO);
                break;
            case 5:
                inputDate(5, reservationDTOList,houseAndFeeDTO);
                break;
            case 6:
                inputDate(6, reservationDTOList,houseAndFeeDTO);
                break;
            case 7:
                inputDate(7, reservationDTOList,houseAndFeeDTO);
                break;
            case 8:
                inputDate(8, reservationDTOList,houseAndFeeDTO);
                break;
            case 9:
                inputDate(9, reservationDTOList,houseAndFeeDTO);
                break;
            case 10:
                inputDate(10, reservationDTOList,houseAndFeeDTO);
                break;
            case 11:
                inputDate(11, reservationDTOList,houseAndFeeDTO);
                break;
            case 12:
                inputDate(12, reservationDTOList,houseAndFeeDTO);
                break;
        }
        System.out.println();
    }

    private static List<Date> makeList(Date checkIn, Date checkOut, List<Date> list) {


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        Date checkIn = formatter.parse(checkInDate);
//        Date checkOut = formatter.parse(checkOutDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkIn);

        // 체크아웃 날짜를 하루 앞당김
        Calendar checkOutCalendar = Calendar.getInstance();
        checkOutCalendar.setTime(checkOut);
        checkOutCalendar.add(Calendar.DATE, -1);
        checkOut = checkOutCalendar.getTime();


        while (!calendar.getTime().after(checkOut)) {
            Date result = calendar.getTime();
            list.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return list;
    }

    private static List<DateAndGuest> makeList_1(Date checkIn, Date checkOut, List<DateAndGuest> list,int guestNum) {


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        Date checkIn = formatter.parse(checkInDate);
//        Date checkOut = formatter.parse(checkOutDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkIn);

        // 체크아웃 날짜를 하루 앞당김
        Calendar checkOutCalendar = Calendar.getInstance();
        checkOutCalendar.setTime(checkOut);
        checkOutCalendar.add(Calendar.DATE, -1);
        checkOut = checkOutCalendar.getTime();


        while (!calendar.getTime().after(checkOut)) {
            Date result = calendar.getTime();
            DateAndGuest dateAndGuest =new DateAndGuest(result,guestNum);
            list.add(dateAndGuest);
            calendar.add(Calendar.DATE, 1);
        }
        return list;
    }
}
