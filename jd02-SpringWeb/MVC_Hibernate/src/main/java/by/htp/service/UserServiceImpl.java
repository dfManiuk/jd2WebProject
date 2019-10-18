package by.htp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.htp.dao.UserDAO;
import by.htp.entity.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	 
	@Autowired
    private UserDAO userDAO;

	@Override
	 @Transactional
	public List<User> allUsers() {
		 return userDAO.allUsers();
	}


	@Override
	 @Transactional
	public User getUser(String login, String password) {
		return userDAO.getUser(login, password);
	}
}
