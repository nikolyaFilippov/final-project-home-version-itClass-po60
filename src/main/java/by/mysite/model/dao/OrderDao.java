package by.mysite.model.dao;

import by.mysite.model.db.ConnectionManager;
import by.mysite.model.entities.User;
import by.mysite.model.entities.order.Order;
import by.mysite.model.entities.order.OrderItem;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static by.mysite.constants.DbConstants.*;
import static by.mysite.constants.JspConstants.*;

public class OrderDao {
    private static OrderDao dao;

    public OrderDao() {
        ConnectionManager.init();
    }

    public static OrderDao getInstance() {
        return dao == null ? new OrderDao() : dao;
    }

    public boolean saveOrder(HttpSession session, String address) {
        User user = (User) session.getAttribute(USER_ATTR);
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String time = now.format(DateTimeFormatter.ofPattern("HH-mm"));
        String orderId = user.getName() + "-" + date + "-" + time;
        List<OrderItem> items = (List<OrderItem>) session.getAttribute(ORDER_ITEMS_ATTR);
        boolean success = true;
        if (saveOrder(orderId, date, user.getId(), address)) {
            for (OrderItem item : items) {
                boolean isSaved = saveOrderItems(orderId, item);
                if (!isSaved) {
                    success = false;
                }
            }
        } else {
            return false;
        }
        if (success) {
            session.setAttribute(ORDER_ID_ATTR, orderId);
        }
        return success;
    }

    private boolean saveOrderItems(String orderId,  OrderItem item) {
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement ps = cn.prepareStatement(INSERT_ORDER_ITEM)){
            ps.setString(1, orderId);
            ps.setInt(2, item.getItem().getId());
            ps.setInt(3, item.getQuantity());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean saveOrder(String id, String date, int userId, String address) {
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement ps = cn.prepareStatement(INSERT_ORDER)){
            ps.setString(1, id);
            ps.setString(2, date);
            ps.setInt(3, userId);
            ps.setString(4, address);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Order> getOrdersList(int userId) {
        List<Order> orders = new ArrayList<>();
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement ps = cn.prepareStatement(SELECT_ORDERS_BY_USER)){
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString(ID_COL);
                String date = resultSet.getString(DATE_COL);
                String address = resultSet.getString(ADDRESS_COL);
                orders.add(new Order(id, date, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    public String getReceipt(String orderId) {
        StringBuilder sb = new StringBuilder();
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement ps = cn.prepareStatement(SELECT_HEAD_FOR_ORDER)){
            ps.setString(1, orderId);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                String date = resultSet.getString(DATE_COL);
                String address = resultSet.getString(ADDRESS_COL);
                sb.append("<h2>Order id : ").append(orderId).append("</h2>")
                        .append("<h2>Date of order : ").append(date).append("</h2>")
                        .append("<h2>Delivery address : ").append(address).append("</h2>");
                sb.append("<h2 class='underline'>You ordered :");
                sb.append(getItemsForReceipt(orderId));
                sb.append(getTotalAmount(orderId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private String getItemsForReceipt(String orderId) {
        StringBuilder sb = new StringBuilder();
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement ps = cn.prepareStatement(SELECT_ITEMS_FOR_ORDER)) {
            ps.setString(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sb.append("<h2>").append(rs.getInt(QUANTITY_COL)).append(" ").append(rs.getString(NAME_COL)).append(" by ")
                        .append(rs.getDouble(PRICE_COL)).append(" byn. Amount : ").append(rs.getDouble(AMOUNT_COL)).append(" byn.</h2>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private String getTotalAmount(String orderId) {
        StringBuilder sb = new StringBuilder();
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement ps = cn.prepareStatement(SELECT_TOTAL_AMOUNT)) {
            ps.setString(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sb.append("<h2 class='underline'>Total amount: ").append(rs.getDouble(AMOUNT_COL)).append(" byn.</h2>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
