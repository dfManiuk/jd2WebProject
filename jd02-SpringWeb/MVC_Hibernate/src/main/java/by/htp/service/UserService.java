package by.htp.service;

import java.util.List;

import by.htp.entity.User;

public interface UserService {
	
	 public List<User> allUsers() ;
	   
	 User getUser(String login, String password);
}
