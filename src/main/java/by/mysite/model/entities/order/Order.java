package by.mysite.model.entities.order;

import java.sql.Date;

public class Order {
    private String id;
    private Date date;
    private int userId;
    private String address;

    public Order(String id, Date date, int userId, String address) {
        this.id = id;
        this.date = date;
        this.userId = userId;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public int getUserId() {
        return userId;
    }

    public String getAddress() {
        return address;
    }
}
