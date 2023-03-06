package by.mysite.controllers.food;

import by.mysite.controllers.AbstractController;
import by.mysite.model.entities.food.FoodItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.mysite.constants.AppConstants.MENU_CONTROLLER;
import static by.mysite.constants.JspConstants.*;

@WebServlet(name = "MenuController", urlPatterns = MENU_CONTROLLER)
public class MenuController extends AbstractController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int foodType = Integer.parseInt(req.getParameter(FOOD_TYPE_PARAM));
        List<FoodItem> items = foodService.getFoodItemsByType(foodType);
        richRequest(req, foodType, items);
        forward(req, resp, HOME_JSP);
    }

    private void richRequest(HttpServletRequest req, int foodType, List<FoodItem> items) {
        switch (foodType) {
            case 1 : {
                req.setAttribute(PIZZA_ATTR, items);
                break;
            }
            case 2 : {
                req.setAttribute(DRINK_ATTR, items);
                break;
            }
            default: break;
        }
    }
}
