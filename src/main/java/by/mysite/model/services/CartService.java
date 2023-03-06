package by.mysite.model.services;

import by.mysite.model.entities.order.OrderItem;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static by.mysite.constants.JspConstants.ORDER_ITEMS_ATTR;

public class CartService {
    private static CartService service;

    public static CartService getInstance() {
        return service == null ? new CartService() : service;
    }

    public List<OrderItem> processCard(HttpSession session, String cardAction, OrderItem item) {
        List<OrderItem> items = !Objects.isNull(session.getAttribute(ORDER_ITEMS_ATTR))
                ? (List<OrderItem>) session.getAttribute(ORDER_ITEMS_ATTR)
                : new ArrayList<>();
        switch (cardAction) {
            case "addToCard" : {
                items.add(item);
                break;
            }
            case "removeFromCart" : {
                items.remove(item);
                break;
            }
            default: break;
        }
        return items;
    }
}
