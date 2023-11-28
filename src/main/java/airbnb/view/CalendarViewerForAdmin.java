package airbnb.view;

import airbnb.persistence.dto.HouseDTO;
import airbnb.persistence.dto.ReservationDTO;
import org.w3c.dom.Node;

import java.rmi.NoSuchObjectException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CalendarViewerForAdmin {
    public static void print(List<ReservationDTO> list,int max) {

        List<Date> dateList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            dateList = makeList(list.get(i).getCheckIn(), list.get(i).getCheckOut(), dateList);
        }


        HashSet<Integer> monthSet = new HashSet<>(); // ���� ������ HashSet ����
        Calendar cal = Calendar.getInstance(); // Calendar ��ü ����

        for (Date date : dateList) {//����� ���� ������. �� ����ġ�� ��
            cal.setTime(date); // Date ��ü�� Calendar ��ü�� ��ȯ
            monthSet.add(cal.get(Calendar.MONTH) + 1); // ���� ���� HashSet�� �߰�, Calendar.MONTH�� 0���� �����ϹǷ� +1
        }

        List<Integer> monthList = new ArrayList<>(monthSet); // �� ������ ���� HashSet�� ArrayList�� ��ȯ

        for (int i = 0; i < monthList.size(); i++) {
            selectMonth(monthList.get(i), list,max);
            System.out.println();
        }
    }



    public static void selectMonth(int val, List<ReservationDTO> reservationDTOList,int max) {

        switch (val) {
            case 1:
                view(1, reservationDTOList,max);
                break;
            case 2:
                view(2, reservationDTOList,max);
                break;
            case 3:
                view(3, reservationDTOList,max);
                break;
            case 4:
                view(4, reservationDTOList,max);
                break;
            case 5:
                view(5, reservationDTOList,max);
                break;
            case 6:
                view(6, reservationDTOList,max);
                break;
            case 7:
                view(7, reservationDTOList,max);
                break;
            case 8:
                view(8, reservationDTOList,max);
                break;
            case 9:
                view(9, reservationDTOList,max);
                break;
            case 10:
                view(10, reservationDTOList,max);
                break;
            case 11:
                view(11, reservationDTOList,max);
                break;
            case 12:
                view(12, reservationDTOList,max);
                break;
        }
        System.out.println();
    }






    public static void view(int month, List<ReservationDTO> list, int max)  {

        int[] arr = new int[32];


        List<NoName> NoNameList = new ArrayList<NoName>();
        for (int i = 0; i < list.size(); i++) {
            NoNameList = makeList_1(list.get(i).getCheckIn(), list.get(i).getCheckOut(), NoNameList, list.get(i).getGuestNum(),max);
        }

        for (int i = 0; i < NoNameList.size(); i++) {
            arr[NoNameList.get(i).getDate().getDay()] += NoNameList.get(i).getPeopleCount();
        }


        try {
            printCalendar(month, arr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public static void printCalendar(int month, int[] arr) throws ParseException {
        Calendar calendar = Calendar.getInstance();


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date checkInDate = dateFormat.parse("2023-"+month+"-01");
        calendar.setTime(checkInDate);

        int printMonth = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        System.out.println("   " + (printMonth + 1) + "�� " + year + "��");

        int startDay = calendar.get(Calendar.DAY_OF_WEEK);
        int endDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);




        System.out.println("��############ ("+ (month + 1)+") ############################################################################################################################�� ");


        System.out.println("�� SUN                   MON                   TUE                   WED                   THR                   FRI                   SAT      �� ");

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int start = calendar.get(Calendar.DAY_OF_WEEK);
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> star_list = new ArrayList<>();

        // ù �� ���� �ϱ��� ���� ���
        for (int i = 1; i < start; i++) {
            list.add("    ");
        }

        // �޷� ���
        for (int i = 1; i <= endDay; i++) {
            list.add(Integer.toString(i));
        }

        // * ���
        for (int i = 1; i < start; i++) {
            star_list.add("    ");
        }
        for (int i = 1; i <= endDay; i++) {
            star_list.add("  *");
        }

        int count = 0;
        for (int i = 0; i < (list.size() / 5)-1; i++) {
            System.out.print("��");
            for (int j = count; j < count + 7; j++) {
                if (j > list.size()-1) {
                    System.out.print("    ");
                } else {
                    if(list.get(j).equals("    ")){
                        System.out.print("                    ");
                    }else{
                        System.out.print("| ");
                        System.out.printf("%-18d", Integer.parseInt(list.get(j)));

                    }
                }
            }
//            System.out.println("  �� ");
            if(i==(list.size() / 5)-2){
                System.out.println("                                  ��");
            }else{
                System.out.println("  ��");
            }
            System.out.print("��");
            for (int j = count; j < count + 7; j++) {
                if(j > star_list.size()-1){
                    System.out.print("    ");
                }else{
                    System.out.printf("%-20s", star_list.get(j));
                }
            }
            //============================================================
            if(i==(list.size() / 5)-2){
                System.out.println("                                  ��");
            }else{
                System.out.println("  ��");
            }
            System.out.print("��");


            for (int j = count; j < count + 7; j++) {
                if(j > star_list.size()-1){
                    System.out.print("    ");
                }else{

                    if(list.get(j).equals("    ")){
                        System.out.printf("%-20s", " ");

                    }else{
                        System.out.print("  room ");
                        if(arr[Integer.parseInt(list.get(j))]==0){
                            System.out.printf("%-13s", "FULL");
                        }else{
                            System.out.printf("%-13s", arr[Integer.parseInt(list.get(j))]);

                        }

                    }
                }
            }
            //===========================================================


            count += 7;
            if(i==(list.size() / 5)-2){
                System.out.println("                                  ��");
            }else{
                System.out.println("  ��");
            }
            System.out.println("��                                                                                                                                              ��");
            System.out.println("��                                                                                                                                              ��");
            System.out.println("��                                                                                                                                              ��");
            System.out.println("��----------------------------------------------------------------------------------------------------------------------------------------------��");


        }

        System.out.println("������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");

    }



    private static List<NoName> makeList_1(Date checkIn, Date checkOut, List<NoName> list, int guestNum, int maxGuestNum) {


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        Date checkIn = formatter.parse(checkInDate);
//        Date checkOut = formatter.parse(checkOutDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkIn);

        // üũ�ƿ� ��¥�� �Ϸ� �մ��
        Calendar checkOutCalendar = Calendar.getInstance();
        checkOutCalendar.setTime(checkOut);
        checkOutCalendar.add(Calendar.DATE, -1);
        checkOut = checkOutCalendar.getTime();


        while (!calendar.getTime().after(checkOut)) {
            Date result = calendar.getTime();
            NoName noName = new NoName(result, guestNum);
            list.add(noName);
            calendar.add(Calendar.DATE, 1);
        }

        return list;
    }

    private static List<Date> makeList(Date checkIn, Date checkOut, List<Date> list) {


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        Date checkIn = formatter.parse(checkInDate);
//        Date checkOut = formatter.parse(checkOutDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkIn);

        // üũ�ƿ� ��¥�� �Ϸ� �մ��
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
}
