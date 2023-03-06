package by.mysite.controllers;

import by.mysite.model.services.FoodService;
import by.mysite.model.services.CartService;
import by.mysite.model.services.OrderService;
import by.mysite.model.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.mysite.constants.JspConstants.MESSAGE_ATTR;

@WebServlet(name = "AbstractController")
public class AbstractController extends HttpServlet {

    protected UserService userservice;
    protected FoodService foodService;
    protected CartService cartService;
    protected OrderService orderService;

    public AbstractController() {
        userservice = UserService.getInstance();
        foodService = FoodService.getInstance();
        cartService = CartService.getInstance();
        orderService = OrderService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void forward(HttpServletRequest req, HttpServletResponse resp, String url)
            throws ServletException, IOException {
        req.getRequestDispatcher(url).forward(req, resp);
    }

    protected void forward(HttpServletRequest req, HttpServletResponse resp, String url, String message)
            throws ServletException, IOException {
        req.setAttribute(MESSAGE_ATTR, message);
        forward(req, resp, url);
    }

    protected void redirect(HttpServletResponse resp, String url) throws IOException {
        resp.sendRedirect(getServletContext().getContextPath() + url);
    }
}
