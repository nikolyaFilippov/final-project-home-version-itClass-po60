package by.mysite.controllers.user;

import by.mysite.controllers.AbstractController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static by.mysite.constants.AppConstants.LOGOUT_CONTROLLER;
import static by.mysite.constants.JspConstants.INDEX_JSP;

@WebServlet(name = "LogoutController", urlPatterns = LOGOUT_CONTROLLER)
public class LogoutController extends AbstractController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        redirect(resp, INDEX_JSP);
    }
}
