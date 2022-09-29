package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
   TypedQuery<User> query = null;

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      try {
         query = this.sessionFactory.getCurrentSession().createQuery("FROM User user WHERE user.car.model = :model AND user.car.series = :series");
         query.setParameter("model", model).setParameter("series", series);
         return query.getSingleResult();
      } catch (NoResultException e) {
         System.out.println("Пользователь не найден");
         return null;
      }


   }
}


