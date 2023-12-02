package airbnb.view;

import airbnb.persistence.dto.DiscountPolicyDTO;
import airbnb.persistence.dto.FeePolicyDTO;
import airbnb.persistence.dto.HouseDTO;
import airbnb.persistence.dto.ReservationDTO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TotalCost {

    public static void totalCost(int month,List<ReservationDTO> list, DiscountPolicyDTO discountPolicyDTO, FeePolicyDTO feePolicyDTO, HouseDTO houseDTO) throws Exception {
        int total = 0;
        for (int i = 0; i < list.size(); i++) {
            Calendar calendarIn = Calendar.getInstance();
            Calendar calendarOut = Calendar.getInstance();
            calendarIn.setTime(list.get(i).getCheckIn());
            calendarOut.setTime(list.get(i).getCheckOut());
            int checkInMonth = calendarIn.get(Calendar.MONTH) + 1;
            int checkOutMonth = calendarOut.get(Calendar.MONTH) + 1;


            if(checkInMonth==checkOutMonth){
             total +=list.get(i).getCost();
            }else{
                Calendar calendar = Calendar.getInstance();
                calendarIn.setTime(list.get(i).getCheckIn());
                int year = calendar.getActualMaximum(Calendar.DAY_OF_YEAR); // 해당 월의 마지막 날짜

                int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 해당 월의 마지막 날짜
                String myDate = Integer.toString(year)+"-"+Integer.toString(month)+"-"+ Integer.toString(lastDay);
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
////        Date checkIn = formatter.parse(checkInDate);

                Calendar calendar_2 = Calendar.getInstance();
                calendar_2.setTime(list.get(i).getCheckIn());
                calendar_2.add(Calendar.MONTH, 1);
                calendar_2.set(Calendar.DATE,1);

                int year_2 = calendar_2.getActualMaximum(Calendar.DAY_OF_YEAR); // 해당 월의 마지막 날짜
                int month_2 = calendar_2.getActualMaximum(Calendar.DAY_OF_MONTH); // 해당 월의 마지막 날짜
                String mydate_2 = year_2+"-"+month_2+"-"+1;


                int frontCost =0;
                int backCost = 0;
                int discountRate = discountPolicyDTO.getDiscount_rate();
                if (discountRate > 0) {
                    frontCost = SaleCalculator.CalculateRate(list.get(i).getCheckIn().toString(), myDate, discountPolicyDTO, feePolicyDTO, list.get(i).getGuestNum());
                    backCost = SaleCalculator.CalculateRate(mydate_2, list.get(i).getCheckOut().toString(), discountPolicyDTO, feePolicyDTO, list.get(i).getGuestNum());

                } else {
                    frontCost = SaleCalculator.CalculateAmount(list.get(i).getCheckIn().toString(), myDate, discountPolicyDTO, feePolicyDTO, list.get(i).getGuestNum());
                    backCost = SaleCalculator.CalculateAmount(mydate_2, list.get(i).getCheckOut().toString(), discountPolicyDTO, feePolicyDTO, list.get(i).getGuestNum());

                }

                Calendar calendarLast = Calendar.getInstance();
                calendarLast.setTime(list.get(i).getCheckIn());
                int lastMonth = calendar.getActualMaximum(Calendar.DAY_OF_YEAR); // 해당 월의 마지막 날짜
                if(month==lastMonth){
                    total +=frontCost;
                }else{
                    total+=backCost;
                }




            }
        }
        System.out.println("TOTAL COST : "+total);




    }
}
