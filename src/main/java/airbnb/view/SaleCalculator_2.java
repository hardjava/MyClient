package airbnb.view;

import airbnb.persistence.dto.DiscountPolicyDTO;
import airbnb.persistence.dto.FeePolicyDTO;

import java.text.SimpleDateFormat;
import java.util.*;

public class SaleCalculator_2 {

    public static int CalculateAmount(String checkIn, String checkOut, DiscountPolicyDTO discountPolicyDTO, FeePolicyDTO feePolicyDTO, int guestNum) throws Exception {


        List<Integer> withDiscount = new ArrayList<Integer>();
        List<Integer> withoutDiscount = new ArrayList<Integer>();
        List<CostObject> withoutCostObject = new ArrayList<CostObject>();
        List<CostObject> withCostObject = new ArrayList<CostObject>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date checkInDate = dateFormat.parse(checkIn);
        Date checkOutDate = dateFormat.parse(checkOut);

        List<Date> dateList = generateDateList(checkInDate, checkOutDate);
        List<Date> discountDays = generateDateList_1(discountPolicyDTO.getDiscountStart(), discountPolicyDTO.getDiscountEnd());

        // 가격 설정
        int weekdayPrice = feePolicyDTO.getWeekday(); // 평일 가격
        int weekendPrice = feePolicyDTO.getWeekend(); // 주말 가격
        if (dateList.size() >= discountPolicyDTO.getDiscountDay()) {
            withoutDiscount = printDates(dateList, weekdayPrice, weekendPrice);
            System.out.println();

            withDiscount = printDatesWithDiscountForAmount(dateList, weekdayPrice, weekendPrice, discountPolicyDTO.getDiscount_amount());


            for (int i = 0; i < withoutDiscount.size(); i++) {
                CostObject costObject = new CostObject(dateList.get(i), withoutDiscount.get(i));
                withoutCostObject.add(costObject);
            }

            for (int i = 0; i < withDiscount.size(); i++) {
                CostObject costObject = new CostObject(dateList.get(i), withDiscount.get(i));
                withCostObject.add(costObject);
            }

            return CalculateTotalCost(dateList, discountDays, withoutCostObject, withCostObject);
        } else {
            int totalPrice = calculateTotalPrice(dateList, weekdayPrice, weekendPrice);
            return totalPrice;
        }


    }


    public static int CalculateRate(String checkIn, String checkOut, DiscountPolicyDTO discountPolicyDTO, FeePolicyDTO feePolicyDTO, int guestNum) throws Exception {


        List<Integer> withDiscount = new ArrayList<Integer>();
        List<Integer> withoutDiscount = new ArrayList<Integer>();
        List<CostObject> withoutCostObject = new ArrayList<CostObject>();
        List<CostObject> withCostObject = new ArrayList<CostObject>();


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date checkInDate = dateFormat.parse(checkIn);
        Date checkOutDate = dateFormat.parse(checkOut);
        Date discountStart = dateFormat.parse("2023-10-01");
        Date discountEnd = dateFormat.parse("2023-10-10");

        List<Date> dateList = generateDateList(checkInDate, checkOutDate);
        List<Date> discountDays = generateDateList_1(discountPolicyDTO.getDiscountStart(), discountPolicyDTO.getDiscountEnd());

        // 가격 설정
        int weekdayPrice = feePolicyDTO.getWeekday(); // 평일 가격
        int weekendPrice = feePolicyDTO.getWeekend(); // 주말 가격

        // 총 가격 출력
        if (dateList.size() >= discountPolicyDTO.getDiscountDay()) {
            withoutDiscount = printDates(dateList, weekdayPrice, weekendPrice);
            System.out.println();

            withDiscount = printDatesWithDiscountForRate(dateList, weekdayPrice, weekendPrice, discountPolicyDTO.getDiscount_amount());


            for (int i = 0; i < withoutDiscount.size(); i++) {
                CostObject costObject = new CostObject(dateList.get(i), withoutDiscount.get(i));
                withoutCostObject.add(costObject);
            }

            for (int i = 0; i < withDiscount.size(); i++) {
                CostObject costObject = new CostObject(dateList.get(i), withDiscount.get(i));
                withCostObject.add(costObject);
            }

            return CalculateTotalCost(dateList, discountDays, withoutCostObject, withCostObject) * guestNum;
        } else {
            int totalPrice = calculateTotalPrice(dateList, weekdayPrice, weekendPrice);
            return totalPrice;
        }
    }


    public static List<Date> generateDateList(Date checkInDate, Date checkOutDate) {
        List<Date> dateList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkInDate);

        while (calendar.getTime().before(checkOutDate)) {
            dateList.add((Date) calendar.getTime());
            calendar.add(Calendar.DATE, 1);
        }

        return dateList;
    }

    public static List<Date> generateDateList_1(Date checkInDate, Date checkOutDate) {

        List<Date> dateList = new ArrayList<>();
        if (checkInDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(checkInDate);

            while (!calendar.getTime().after(checkOutDate)) {
                dateList.add((Date) calendar.getTime().clone());
                calendar.add(Calendar.DATE, 1);
            }

            return dateList;
        } else {
            return dateList;
        }

    }


    public static int calculateTotalPrice(List<Date> dates, int weekdayPrice, int weekendPrice) {
        int totalPrice = 0;
        for (Date date : dates) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            boolean isWeekend = (dayOfWeek == Calendar.FRIDAY) ||
                    (dayOfWeek == Calendar.SATURDAY) ||
                    (dayOfWeek == Calendar.SUNDAY);
            if (isWeekend) {
                totalPrice += weekendPrice;
            } else {
                totalPrice += weekdayPrice;
            }
        }
        return totalPrice;
    }

    public static List<Integer> printDates(List<Date> dates, int weekdayPrice, int weekendPrice) {
        List<Integer> withoutDiscount = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Date date : dates) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            boolean isWeekend = (dayOfWeek == Calendar.FRIDAY) ||
                    (dayOfWeek == Calendar.SATURDAY) ||
                    (dayOfWeek == Calendar.SUNDAY);
            if (isWeekend) {
                withoutDiscount.add(weekendPrice);
            } else {
                withoutDiscount.add(weekdayPrice);
            }
        }
        return withoutDiscount;
    }

    public static List<Integer> printDatesWithDiscountForAmount(List<Date> dates, int weekdayPrice, int weekendPrice, int discount) {
        List<Integer> withDiscount = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Date date : dates) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            boolean isWeekend = (dayOfWeek == Calendar.FRIDAY) ||
                    (dayOfWeek == Calendar.SATURDAY) ||
                    (dayOfWeek == Calendar.SUNDAY);
            if (isWeekend) {
                withDiscount.add(weekendPrice - discount);
            } else {
                withDiscount.add(weekdayPrice - discount);
            }
        }
        return withDiscount;
    }


    public static List<Integer> printDatesWithDiscountForRate(List<Date> dates, int weekdayPrice, int weekendPrice, int discount) {
        List<Integer> withDiscount = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Date date : dates) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            boolean isWeekend = (dayOfWeek == Calendar.FRIDAY) ||
                    (dayOfWeek == Calendar.SATURDAY) ||
                    (dayOfWeek == Calendar.SUNDAY);
            if (isWeekend) {
                withDiscount.add(weekendPrice - (weekendPrice * discount / 100));

            } else {
                withDiscount.add(weekdayPrice - (weekdayPrice * discount / 100));
            }
        }
        return withDiscount;
    }

    public static int CalculateTotalCost(List<Date> dateList, List<Date> dateList_2, List<CostObject> withOutList, List<CostObject> withList) {
        List<Date> list = findOverlap(dateList, dateList_2);
        Collections.sort(list);

        int total = 0;
        int count = 0;
        if (list.size() != 0) {
            for (int i = 0; i < withOutList.size(); i++) {
                if (withOutList.get(i).getDate().equals(list.get(count))) {
                    total += withList.get(i).getCost();
                    if (count == list.size() - 1) {

                    } else {
                        count++;
                    }

                } else {
                    total += withOutList.get(i).getCost();
                }
            }
        } else {
            for (int i = 0; i < withOutList.size(); i++) {
                total += withOutList.get(i).getCost();

            }
        }

        return total;
    }

    public static List<Date> findOverlap(List<Date> list1, List<Date> list2) {
        // 겹치는 날짜를 저장할 Set 생성
        Set<Date> overlapDates = new HashSet<>();

        // list1의 날짜를 Set에 추가
        for (Date date : list1) {
            overlapDates.add(date);
        }
        List<Date> dates = new ArrayList<>();
        // list2의 날짜 중 겹치는 날짜를 Set에 추가
        for (Date date : list2) {
            if (overlapDates.contains(date)) {
                overlapDates.add(date);
                dates.add(date);
            }
        }


        // 겹치는 날짜를 리스트로 변환하여 반환
//        return new ArrayList<>(overlapDates);
        return dates;
    }

}
