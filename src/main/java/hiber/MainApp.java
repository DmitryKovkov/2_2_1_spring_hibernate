package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("nissan", 2013)));
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      user2.setCar(new Car("Mercedes-Benz", 2023));
      userService.add(user2);

      String model = "Nissan";
      int series = 2013;
      List<User> users = userService.findUserByModelSeriesCar(model, series);
      System.out.printf("ВЛАДЕЛЬЦЫ МАШИНЫ: %s %d %n", model, series);
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+ Optional.ofNullable(user.getCar()).map(car -> car.toString()).orElse("Нет машины"));
         System.out.println();
      }


      context.close();
   }
}
