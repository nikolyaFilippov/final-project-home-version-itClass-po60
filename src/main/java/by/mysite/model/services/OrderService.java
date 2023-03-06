package by.mysite.model.services;

import by.mysite.model.dao.OrderDao;

import javax.servlet.http.HttpSession;

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
}
