package by.htp.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import by.htp.entity.User;
import java.util.List;

import javax.persistence.Query;


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
	public User getUser(String login) {

		Session session = sessionFactory.getCurrentSession();
		Query query =   session.createQuery("FROM User u where u.login = :login");  
        query.setParameter("login", login);
        User theUser= (User) query.getSingleResult();
        System.out.println(theUser.toString());
         return theUser; 
	}

	@Override
	public User findUserByUsername(String username) {
		System.out.println("IIIIIIIIIIIIIIIIIIIIII" + username);
		
		return sessionFactory.getCurrentSession().get(User.class, username);
		
	}

    
}
