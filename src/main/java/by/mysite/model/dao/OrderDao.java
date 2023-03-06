package by.mysite.model.dao;

import by.mysite.model.db.ConnectionManager;
import by.mysite.model.entities.User;
import by.mysite.model.entities.order.OrderItem;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static by.mysite.constants.DbConstants.INSERT_ORDER;
import static by.mysite.constants.DbConstants.INSERT_ORDER_ITEM;
import static by.mysite.constants.JspConstants.ORDER_ITEMS_ATTR;
import static by.mysite.constants.JspConstants.USER_ATTR;

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
        saveOrder(orderId, date, user.getId(), address);
        boolean success = true;
        for (OrderItem item : items) {
            boolean isSaved = saveOrderItems(orderId, item);
            if (!isSaved) {
                success = false;
            }
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

    private void saveOrder(String id, String date, int userId, String address) {
        try (Connection cn = ConnectionManager.getConnection();
             PreparedStatement ps = cn.prepareStatement(INSERT_ORDER)){
            ps.setString(1, id);
            ps.setString(2, date);
            ps.setInt(3, userId);
            ps.setString(4, address);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
