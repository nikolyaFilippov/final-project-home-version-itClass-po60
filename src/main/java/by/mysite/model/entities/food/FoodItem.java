package by.mysite.model.entities.food;

public class FoodItem {
    protected int id;
    protected int type;
    protected String name;
    protected double price;

    public FoodItem(int id, int type, String name, double price) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoodItem)) return false;

        FoodItem foodItem = (FoodItem) o;

        if (id != foodItem.id) return false;
        if (type != foodItem.type) return false;
        if (Double.compare(foodItem.price, price) != 0) return false;
        return name != null ? name.equals(foodItem.name) : foodItem.name == null;
    }
}
