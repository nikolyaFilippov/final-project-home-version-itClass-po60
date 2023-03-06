package by.mysite.controllers.order;

import by.mysite.controllers.AbstractController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.mysite.constants.AppConstants.ORDER_CONTROLLER;
import static by.mysite.constants.JspConstants.*;

@WebServlet(name = "OrderController", urlPatterns = ORDER_CONTROLLER)
public class OrderController extends AbstractController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String address = req.getParameter(ADDRESS_PARAM);
        HttpSession session = req.getSession();

        if (orderService.saveOrder(session, address)) {
            redirect(resp, HOME_JSP);
        } else {
            forward(req, resp, CART_JSP, "BAD");
        }
    }
}
