package by.mysite.controllers.order;

import by.mysite.controllers.AbstractController;
import by.mysite.model.entities.User;
import by.mysite.model.entities.order.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.mysite.constants.AppConstants.ORDERS_HISTORY_CONTROLLER;
import static by.mysite.constants.JspConstants.*;

@WebServlet(name = "OrdersHistoryController", urlPatterns = ORDERS_HISTORY_CONTROLLER)
public class OrdersHistoryController extends AbstractController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int userId = ((User) session.getAttribute(USER_ATTR)).getId();
        List<Order> ordersList = orderService.getOrdersList(userId);
        req.setAttribute(ORDERS_LIST_ATTR, ordersList);
        forward(req, resp, ORDERS_JSP);
    }
}
