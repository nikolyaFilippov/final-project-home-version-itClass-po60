package by.mysite.model.services;

import by.mysite.model.dao.UserDao;
import by.mysite.model.entities.User;

public class UserService {
    private static UserService service;
    private UserDao dao;

    public UserService() {
        dao = UserDao.getInstance();
    }

    public static UserService getInstance() {
        return service == null ? new UserService() : service;
    }

    public User getUser(String login, String password) {
        return dao.getUser(login, password);
    }

    public boolean addUser(User user, String password) {
        return dao.addUser(user, password);
    }
}
