package airbnb.view;

import java.util.Date;

public class NoName {
    private Date date;
    private int peopleCount;
    public NoName(Date date, int peopleCount){
        this.date =date;
        this.peopleCount =peopleCount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }
}
