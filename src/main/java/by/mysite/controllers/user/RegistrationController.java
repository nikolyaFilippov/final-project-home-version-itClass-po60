package by.mysite.controllers.user;

import by.mysite.controllers.AbstractController;
import by.mysite.model.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.mysite.constants.AppConstants.REGISTRATION_CONTROLLER;
import static by.mysite.constants.AppConstants.USER_NOT_REGISTERED;
import static by.mysite.constants.JspConstants.*;

@WebServlet(name = "RegistrationController", urlPatterns = REGISTRATION_CONTROLLER)
public class RegistrationController extends AbstractController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(LOGIN_PARAM);
        String name = req.getParameter(NAME_PARAM);
        String password = req.getParameter(PASSWORD_PARAM);
        String email = req.getParameter(EMAIL_PARAM);
        User user = new User(login, name, email);
        if (userservice.addUser(user, password)) {
            redirect(resp, LOGIN_JSP);
        } else {
            forward(req, resp, REGISTRATION_JSP, USER_NOT_REGISTERED);
        }
    }
}
