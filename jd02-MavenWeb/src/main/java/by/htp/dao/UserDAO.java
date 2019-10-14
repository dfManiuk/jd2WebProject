package by.htp.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import by.htp.entity.Doctor;
import by.htp.entity.Patient;
import by.htp.entity.User;
import by.htp.service.ServiceException;

public interface UserDAO {

	 User autorization(String login, String password) throws DAOException;
	 
	 User registration (User newUser) throws DAOException;
	 
	 User editProfile(User user) throws DAOException;
	 
	 int find(String name) throws DAOException;
	 
	 ArrayList<Doctor> findListOfUser()throws DAOException;
	 
	 List<Patient> findPatientsFromUser(Doctor doctor) throws DAOException;
	 
	 String userRole (String name) throws DAOException;
	 
	 int findIdUserForPatient(int idPatient) throws DAOException;
	 
	 void updateUserOutOf(int userId)throws DAOException;
	 
	 Date currentTimestamp() throws DAOException;
	 
	 ArrayList<Doctor> findListOfLeaveUser()throws DAOException;
}		
