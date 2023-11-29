package airbnb.view;

import java.util.Date;

public class DateAndGuest {
    private Date date;
    private int guestNum;

    public DateAndGuest(Date date, int guestNum) {
        this.date = date;
        this.guestNum = guestNum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getGuestNum() {
        return guestNum;
    }

    public void setGuestNum(int guestNum) {
        this.guestNum = guestNum;
    }
}
