package airbnb.view;

import java.util.Date;

public class CostObject {
     private Date date;
     private int cost;

    public CostObject(Date date, int cost) {
        this.date = date;
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
