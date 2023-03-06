package by.mysite.model.services;

import by.mysite.model.dao.FoodDao;
import by.mysite.model.entities.food.FoodItem;

import java.util.List;
import java.util.Objects;

public class FoodService {
    private static FoodService service;
    private FoodDao dao;

    public FoodService() {
        dao = FoodDao.getInstance();
    }

    public static FoodService getInstance() {
        return Objects.isNull(service) ? new FoodService() : service;
    }

    public List<FoodItem> getFoodItemsByType(int foodType) {
        return dao.getFoodItemsByType(foodType);
    }
}
