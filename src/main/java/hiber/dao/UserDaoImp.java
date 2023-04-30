package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<User> findUserByModelSeriesCar(String model, int series) {
      Session session = sessionFactory.getCurrentSession();
      List<Car> cars = session
              .createQuery("FROM Car WHERE model = :model and series = :series")
              .setParameter("model", model)
              .setParameter("series", series)
              .getResultList();
      List<User> users = new ArrayList<>();
      for (Car car: cars ) {
         users.add(car.getUser());
      }
      return users;
   }

   @Override
   public void deleteTable() {
      Session session = sessionFactory.getCurrentSession();
      session.createQuery("DELETE FROM Car").executeUpdate();
   }

   @Override
   public void delete() {
      Session session = sessionFactory.getCurrentSession();
      Car car = session.get(Car.class, (long) 18);
      session.delete(car);
   }

}
