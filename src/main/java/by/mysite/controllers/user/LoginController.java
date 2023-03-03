package by.mysite.controllers.user;

import by.mysite.controllers.AbstractController;
import by.mysite.model.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

import static by.mysite.constants.AppConstants.*;
import static by.mysite.constants.JspConstants.*;

@WebServlet(name = "LoginController", urlPatterns = LOGIN_CONTROLLER)
public class LoginController extends AbstractController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(LOGIN_PARAM);
        String password = req.getParameter(PASSWORD_PARAM);
        User user =  userservice.getUser(login, password);
        if (!Objects.isNull(user)) {
            HttpSession session = req.getSession();
            session.setAttribute(USER_ATTR, user);
            forward(req, resp, HOME_JSP);
        } else {
            forward(req, resp, LOGIN_JSP, USER_NOT_FOUND);
        }
    }
}
