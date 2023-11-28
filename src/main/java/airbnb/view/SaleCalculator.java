package airbnb.view;
import airbnb.persistence.dto.DiscountPolicyDTO;
import airbnb.persistence.dto.FeePolicyDTO;
import airbnb.persistence.dto.ReservationDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SaleCalculator {

    public static int CalculateAmount(String checkIn, String checkOut, DiscountPolicyDTO discountPolicyDTO, FeePolicyDTO feePolicyDTO) throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


            Date checkInDate = dateFormat.parse(checkIn);
            Date checkOutDate = dateFormat.parse(checkOut);
        int value = 0;

        List<Date> dateList = generateDateList(checkInDate, checkOutDate);
        if (dateList.size() >= discountPolicyDTO.getDiscountDay()) {
            int weekPrice = feePolicyDTO.getWeekday() - discountPolicyDTO.getDiscount_amount();
            int weekendPrice = feePolicyDTO.getWeekend() - discountPolicyDTO.getDiscount_amount();
            value = calculateTotalPrice(dateList, weekPrice, weekendPrice);
        } else {
            value = calculateTotalPrice(dateList, feePolicyDTO.getWeekday(), feePolicyDTO.getWeekend());

        }


        return value;
    }
    public static int CalculateRate(String checkIn, String checkOut, DiscountPolicyDTO discountPolicyDTO,FeePolicyDTO feePolicyDTO) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


        Date checkInDate = dateFormat.parse(checkIn);
        Date checkOutDate = dateFormat.parse(checkOut);
        int value = 0;

        List<Date> dateList = generateDateList(checkInDate, checkOutDate);
        if (dateList.size() >= discountPolicyDTO.getDiscountDay()) {
            int weekPrice = feePolicyDTO.getWeekday() *(1-discountPolicyDTO.getDiscount_amount()/100);
            int weekendPrice = feePolicyDTO.getWeekend() *(1-discountPolicyDTO.getDiscount_amount()/100);
            value = calculateTotalPrice(dateList, weekPrice, weekendPrice);
        } else {
            value = calculateTotalPrice(dateList, feePolicyDTO.getWeekday(), feePolicyDTO.getWeekend());

        }


        return value;
    }


    public static List<Date> generateDateList(Date checkInDate, Date checkOutDate) {
        List<Date> dateList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkInDate);

        while (calendar.getTime().before(checkOutDate)) {
            dateList.add((Date) calendar.getTime());
            calendar.add(Calendar.DATE, 1);
        }

        // 체크아웃 날짜를 리스트에 포함하지 않도록 수정
        // dateList.add(checkOutDate);

        return dateList;
    }


    public static int calculateTotalPrice(List<Date> dateList, int weekPrice, int weekendPrice) {
        int totalPrice = 0;
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.ENGLISH);

        for (Date date : dateList) {
            String dayOfWeek = dayFormat.format(date);

            if (isWeekend(dayOfWeek)) {
                totalPrice += weekendPrice;
            } else {
                totalPrice += weekPrice;
            }
        }

        return totalPrice;
    }

    public static boolean isWeekend(String dayOfWeek) {
        return dayOfWeek.equals("Saturday") || dayOfWeek.equals("Sunday");
    }
}

