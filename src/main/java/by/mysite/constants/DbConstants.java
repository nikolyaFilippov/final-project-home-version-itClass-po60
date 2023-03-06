package by.mysite.constants;

public class DbConstants {
    public static final String ID_COL = "id";
    public static final String LOGIN_COL = "login";
    public static final String NAME_COL = "name";
    public static final String PASS_COL = "password";
    public static final String EMAIL_COL = "email";
    public static final String PRICE_COL = "price";
    public static final String SELECT_USER_BY_CREDENTIAL = "SELECT id, name, email FROM user WHERE login = ? AND password = ?";
    public static final String SELECT_USER_ID_BY_LOGIN = "SELECT id FROM user WHERE login = ?";
    public static final String INSERT_USER = "INSERT INTO user (login, name, password, email) values (?, ?, ?, ?)";

    public static final String SELECT_FOOD_ITEMS_BY_TYPE = "SELECT id, name, price FROM foodItem WHERE foodTypeId = ?";

    public static final String INSERT_ORDER_ITEM = "INSERT INTO orderItem (orderId, itemId, quantity) values (?, ?, ?)";
    public static final String INSERT_ORDER = "INSERT INTO orders (id, date, userId, address) values (?, ?, ?, ?)";
}
