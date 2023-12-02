package airbnb.view;

import airbnb.network.MyIOStream;
import airbnb.persistence.dto.DiscountPolicyDTO;
import airbnb.persistence.dto.FeePolicyDTO;
import airbnb.persistence.dto.ReservationDTO;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class CalendarViewer {


    public static void selectMonth(List<ReservationDTO> reservationDTOList, DiscountPolicyDTO discountPolicyDTO, FeePolicyDTO feePolicyDTO) throws Exception {
        System.out.print("Select month!! ( 1 ~ 12 ): ");
        int input = MyIOStream.sc.nextInt();
        switch (input) {
            case 1:
                viewCalendar(1, reservationDTOList, discountPolicyDTO, feePolicyDTO);
                break;
            case 2:
                viewCalendar(2, reservationDTOList, discountPolicyDTO, feePolicyDTO);
                break;
            case 3:
                viewCalendar(3, reservationDTOList, discountPolicyDTO, feePolicyDTO);
                break;
            case 4:
                viewCalendar(4, reservationDTOList, discountPolicyDTO, feePolicyDTO);
                break;
            case 5:
                viewCalendar(5, reservationDTOList, discountPolicyDTO, feePolicyDTO);
                break;
            case 6:
                viewCalendar(6, reservationDTOList, discountPolicyDTO, feePolicyDTO);
                break;
            case 7:
                viewCalendar(7, reservationDTOList, discountPolicyDTO, feePolicyDTO);
                break;
            case 8:
                viewCalendar(8, reservationDTOList, discountPolicyDTO, feePolicyDTO);
                break;
            case 9:
                viewCalendar(9, reservationDTOList, discountPolicyDTO, feePolicyDTO);
                break;
            case 10:
                viewCalendar(10, reservationDTOList, discountPolicyDTO, feePolicyDTO);
                break;
            case 11:
                viewCalendar(11, reservationDTOList, discountPolicyDTO, feePolicyDTO);
                break;
            case 12:
                viewCalendar(12, reservationDTOList, discountPolicyDTO, feePolicyDTO);
                break;
        }
        System.out.println();
    }

    private static void viewCalendar(int month, List<ReservationDTO> list, DiscountPolicyDTO discountPolicyDTO, FeePolicyDTO feePolicyDTO) throws Exception {
        List<Date> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            newList = makeList(list.get(i).getCheckIn(), list.get(i).getCheckOut(), newList);
        }
        int total = 0;
        view(month, newList);

        for (int i = 0; i < list.size(); i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(list.get(i).getCheckIn());
            int checkInMonth = calendar.get(Calendar.MONTH) + 1;
            calendar.setTime(list.get(i).getCheckOut());
            int checkOutMonth = calendar.get(Calendar.MONTH) + 1;

            if (checkInMonth == checkOutMonth) {
                total += list.get(i).getCost();
            } else {
                int frontCost;
                int backCost;
                calendar.setTime(list.get(i).getCheckOut());
                int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 해당 월의 마지막 날짜
                calendar.set(Calendar.DAY_OF_MONTH, lastDay);
                Date modifiedDateFront = calendar.getTime();

                LocalDate localDate = modifiedDateFront.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                // LocalDate를 원하는 형식의 문자열로 변환
                String formattedDate = localDate.toString();

                calendar.setTime(list.get(i).getCheckOut());
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                Date modifiedDateBack = calendar.getTime();
                LocalDate localDate_1 = modifiedDateBack.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                // LocalDate를 원하는 형식의 문자열로 변환
                String formattedDate_1 = localDate_1.toString();


                int discountRate = discountPolicyDTO.getDiscount_rate();


                if (discountRate > 0) {
                    frontCost = SaleCalculator.CalculateRate(list.get(i).getCheckIn().toString(), formattedDate, discountPolicyDTO, feePolicyDTO, list.get(i).getGuestNum());
                    backCost = SaleCalculator.CalculateRate(formattedDate_1, list.get(i).getCheckOut().toString(), discountPolicyDTO, feePolicyDTO, list.get(i).getGuestNum());

                } else {
                    frontCost = SaleCalculator.CalculateAmount(list.get(i).getCheckIn().toString(), formattedDate, discountPolicyDTO, feePolicyDTO, list.get(i).getGuestNum());
                    backCost = SaleCalculator.CalculateAmount(formattedDate_1, list.get(i).getCheckOut().toString(), discountPolicyDTO, feePolicyDTO, list.get(i).getGuestNum());

                }

                if (checkInMonth == month) {
                    total += frontCost;

                } else {
                    total += backCost;
                }


            }


        }
        System.out.println("TOTAL COST : " + total);
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


    private static void view(int monthValue, List<Date> list) {

        Collections.sort(list);
        int count = 0;
        ArrayList<Integer> arrayList = new ArrayList<>();
//        Scanner scanner = new Scanner(System.in);

        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
//        int year = Integer.parseInt(yearFormat.format(list.get(0)));
//        int month = Integer.parseInt(monthFormat.format(list.get(0)));
//        int day = Integer.parseInt(dayFormat.format(list.get(0)));

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

        // 달력 출력
        System.out.println("SUN\tMON\tTUE\tWED\tTHU\tFRI\tSAT");
        for (int i = 1; i < firstDay; i++) {
            System.out.print("\t");
        }
        for (int i = 1; i <= lastDay; i++) {
            if (!arrayList.isEmpty()) {
                if ((i == arrayList.get(count))) {
                    System.out.print("-\t");
                    if (count == arrayList.size() - 1) {

                    } else {
                        count++;

                    }
                } else {
                    System.out.print(i + "\t");
                }
                if ((firstDay + i - 1) % 7 == 0) {
                    System.out.println();
                }
            } else {
                System.out.print(i + "\t");
                if ((firstDay + i - 1) % 7 == 0) {
                    System.out.println();
                }
            }

        }
    }

    private static int printTotalCost(List<Date> list) {
        int total = 0;
        for (int i = 0; i < list.size(); i++) {
//            total += list.get(i).getCost();
        }
        return total;
    }

}
