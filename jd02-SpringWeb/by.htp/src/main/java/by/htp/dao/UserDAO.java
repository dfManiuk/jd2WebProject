package by.htp.dao;

import java.util.List;

import by.htp.entity.User;

public interface UserDAO {
	
	User getUser(String login, String password);
    List<User> allUsers();

}
