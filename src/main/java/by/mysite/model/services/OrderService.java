package by.mysite.model.services;

import by.mysite.model.dao.OrderDao;
import by.mysite.model.entities.order.Order;

import javax.servlet.http.HttpSession;
import java.util.List;

public class OrderService {
    private static OrderService service;
    private OrderDao dao;

    public OrderService() {
        dao = OrderDao.getInstance();
    }

    public static OrderService getInstance() {
        return service == null ? new OrderService() : service;
    }

    public boolean saveOrder(HttpSession session, String address) {
        return dao.saveOrder(session, address);
    }

    public List<Order> getOrdersList(int userId) {
        return dao.getOrdersList(userId);
    }

    public String getReceipt(String orderId) {
        return dao.getReceipt(orderId);
    }
}
