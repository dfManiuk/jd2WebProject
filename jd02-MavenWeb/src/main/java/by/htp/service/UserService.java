package by.htp.service;

import java.util.List;

import by.htp.entity.Doctor;
import by.htp.entity.User;

public interface UserService {
	
	User authorization(String login, String password) throws ServiceException;
	
	User editProfile(User user) throws ServiceException;
	
	User registration(User newUser)  throws ServiceException;
	
	List<Doctor> findAllDoctors( ) throws ServiceException;
	
	String userRole(String name) throws ServiceException;
	
	int findUserfromPatient(int idPatient) throws ServiceException;
	
	void updateUserOutOf(int userId) throws ServiceException;
	
	List<Doctor> findAllDoctorsToLeave( ) throws ServiceException;
}
