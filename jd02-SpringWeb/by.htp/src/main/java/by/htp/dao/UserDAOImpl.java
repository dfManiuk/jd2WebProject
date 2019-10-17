package by.htp.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.htp.config.HibernateConfig;
import by.htp.config.HibernetUtil;
import by.htp.entity.User;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    private SessionFactory sessionFactory;

//   @Autowired
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> allUsers() {
    	System.out.println("DAO");
    	 try (Session session = HibernetUtil.getSessionFactory().openSession()) {
    		  return session.createQuery("from User").list();
         }
      //  return session.createQuery("from User").list();
    }

    
}
