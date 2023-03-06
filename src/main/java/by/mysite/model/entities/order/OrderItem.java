package by.mysite.model.entities.order;

import by.mysite.model.entities.food.FoodItem;

public class OrderItem {
    private String orderId;
    private FoodItem item;
    private int quantity;

    public OrderItem(FoodItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public FoodItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;

        OrderItem orderItem = (OrderItem) o;

        if (quantity != orderItem.quantity) return false;
        if (orderId != null ? !orderId.equals(orderItem.orderId) : orderItem.orderId != null) return false;
        return item != null ? item.equals(orderItem.item) : orderItem.item == null;
    }
}
