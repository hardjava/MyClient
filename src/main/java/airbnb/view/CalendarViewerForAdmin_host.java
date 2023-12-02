package airbnb.view;

import airbnb.persistence.dto.ReservationDTO;

import java.text.SimpleDateFormat;
import java.util.*;

public class CalendarViewerForAdmin_host {

    public static void run(List<ReservationDTO> inputList, int bedroom) {

        List<Date> dateList = new ArrayList<>();

        for (int i = 0; i < inputList.size(); i++) {
            dateList = makeList(inputList.get(i).getCheckIn(), inputList.get(i).getCheckOut(), dateList);
        }


        HashSet<Integer> monthSet = new HashSet<>(); // ���� ������ HashSet ����
        Calendar cal = Calendar.getInstance(); // Calendar ��ü ����

        for (Date date : dateList) {//����� ���� ������. �� ����ġ�� ��
            cal.setTime(date); // Date ��ü�� Calendar ��ü�� ��ȯ
            monthSet.add(cal.get(Calendar.MONTH) + 1); // ���� ���� HashSet�� �߰�, Calendar.MONTH�� 0���� �����ϹǷ� +1
        }

        List<Integer> monthList = new ArrayList<>(monthSet); // �� ������ ���� HashSet�� ArrayList�� ��ȯ

        for (int i = 0; i < monthList.size(); i++) {
            selectMonth(monthList.get(i), inputList, bedroom);
            System.out.println();
        }

    }

    private static void inputDate(int month, List<ReservationDTO> inputList, int bedroom) {


        int[] arr;
        List<Date> list = new ArrayList<>();
        List<DateAndGuest> objectList = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i++) {
            objectList = makeList_1(inputList.get(i).getCheckIn(), inputList.get(i).getCheckOut(), objectList, inputList.get(i).getGuestNum());
        }

        for (int i = 0; i < objectList.size(); i++) {
            list.add(objectList.get(i).getDate());
        }


        arr = returnArr(objectList, bedroom, month);
        view(month, list, arr);

    }


    private static void view(int monthValue, List<Date> list, int[] arr) {

        Collections.sort(list);
        ArrayList<Integer> arrayList = new ArrayList<>();

        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");

        for (int i = 0; i < list.size(); i++) {
            if (Integer.parseInt(monthFormat.format(list.get(i))) == monthValue) {
                arrayList.add(Integer.parseInt(dayFormat.format(list.get(i))));
            }
        }


        // Calendar ��ü ����
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, monthValue - 1, 1); // �Է��� �⵵�� ���� ����

        // �ش� ���� ù ��° ��¥�� ������ ��¥ ���ϱ�
        int firstDay = calendar.get(Calendar.DAY_OF_WEEK); // �ش� ���� ù ��° ���� ����(��: 1, ��: 2, ..., ��: 7)
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // �ش� ���� ������ ��¥

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
        System.out.println("##################################" + monthValue + "##################################");

        System.out.println("SUN         MON         TUE         WED         THU         FRI         SAT");
        int count_1 = 0;
        for (int i = 0; i < (arrayList_1.size() / 5) - 1; i++) {
            for (int j = count_1; j < count_1 + 7; j++) {
                if (j > arrayList_1.size() - 1) {

                } else {
                    if (arrayList_1.get(j).equals(" ")) {
                        System.out.printf("%-12s", arrayList_1.get(j));


                    } else {
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
                    if (arrayList_1.get(j).equals(" ")) {
                        System.out.printf("%-12s", " ");
                    } else {
                        System.out.print("ROOM : ");
                        if (arr[Integer.parseInt(arrayList_1.get(j))] == 0) {
                            System.out.printf("%-5s", "X");
                        } else {
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

    public static int[] returnArr(List<DateAndGuest> list, int max, int month) {
        int[] arr = new int[32];

        for (int i = 0; i < list.size(); i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(list.get(i).getDate());

            // ���� ��¥�� �� ���� ���
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            int Month = calendar.get(Calendar.MONTH) + 1;
            if (Month == month) {
                arr[dayOfMonth] += list.get(i).getGuestNum();

            }
        }
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = max - arr[i];
//        }
        return arr;
    }


    public static void selectMonth(int val, List<ReservationDTO> reservationDTOList, int bedroom) {

        switch (val) {
            case 1:
                inputDate(1, reservationDTOList, bedroom);
                break;
            case 2:
                inputDate(2, reservationDTOList, bedroom);
                break;
            case 3:
                inputDate(3, reservationDTOList, bedroom);
                break;
            case 4:
                inputDate(4, reservationDTOList, bedroom);
                break;
            case 5:
                inputDate(5, reservationDTOList, bedroom);
                break;
            case 6:
                inputDate(6, reservationDTOList, bedroom);
                break;
            case 7:
                inputDate(7, reservationDTOList, bedroom);
                break;
            case 8:
                inputDate(8, reservationDTOList, bedroom);
                break;
            case 9:
                inputDate(9, reservationDTOList, bedroom);
                break;
            case 10:
                inputDate(10, reservationDTOList, bedroom);
                break;
            case 11:
                inputDate(11, reservationDTOList, bedroom);
                break;
            case 12:
                inputDate(12, reservationDTOList, bedroom);
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

    private static List<DateAndGuest> makeList_1(Date checkIn, Date checkOut, List<DateAndGuest> list, int guestNum) {


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
            DateAndGuest dateAndGuest = new DateAndGuest(result, guestNum);
            list.add(dateAndGuest);
            calendar.add(Calendar.DATE, 1);
        }
        return list;
    }
}
