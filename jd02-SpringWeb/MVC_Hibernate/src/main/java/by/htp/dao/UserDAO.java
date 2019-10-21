package by.htp.dao;

import java.util.List;

import by.htp.entity.User;

public interface UserDAO {
	
	User getUser(String login);
	
    List<User> allUsers();
    
    User findUserByUsername(String username);

}
