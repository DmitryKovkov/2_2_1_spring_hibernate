package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    List<User> findUserByModelSeriesCar(String model, int series);
    void deleteTable();
    void delete();
}
