package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> listUsers();
   List<User> findUserByModelSeriesCar(String model, int series);
   void deleteTable();
   void delete();
}
