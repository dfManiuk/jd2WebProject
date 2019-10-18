package by.htp.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.htp.config.HibernateConfig;
//import by.htp.config.HibernetUtil;
import by.htp.entity.User;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Repository
public class UserDAOImpl implements UserDAO {
	 
	@Autowired
	private SessionFactory sessionFactory;

    @Override
    public List<User> allUsers() {
    	
    	  Session session = sessionFactory.getCurrentSession();
    		  return session.createQuery("from User").list();
    	
    }

	@Override
	public User getUser(String login, String password) {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$");
		//Session session = HibernetUtil.getSessionFactory().openSession();

		Session session = sessionFactory.getCurrentSession();
//		Query query =   session.createQuery("FROM User u where u.login = :login and u.password = :password");
//       query.setParameter("login", login);
//        query.setParameter("password", password);
//        User theUser= (User) query.getSingleResult();
         return null ;
	}

    
}
