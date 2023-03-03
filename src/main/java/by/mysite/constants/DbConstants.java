package by.mysite.constants;

public class DbConstants {
    public static final String ID_COL = "id";
    public static final String LOGIN_COL = "login";
    public static final String NAME_COL = "name";
    public static final String PASS_COL = "password";
    public static final String EMAIL_COL = "email";
    public static final String SELECT_USER_BY_CREDENTIAL = "SELECT id, name, email FROM user WHERE login = ? AND password = ?";
    public static final String SELECT_USER_ID_BY_LOGIN = "SELECT id FROM user WHERE login = ?";
    public static final String INSERT_USER = "INSERT INTO user (login, name, password, email) values (?, ?, ?, ?)";

}
