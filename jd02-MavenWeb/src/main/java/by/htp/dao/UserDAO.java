
package by.htp.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import by.htp.entity.Doctor;
import by.htp.entity.Patient;
import by.htp.entity.User;

/**
 * Users Data Access Object interface.
  * Provides CRUD operations with {@link entity.User} objects.
  * 
 * @author D.Maniuk on 20.10.2019
 * @version 1.0
 */
public interface UserDAO {

	 /**
     *Constructs an authorizations {@link entity.User} object from a human-readable login and password.
     * 
     * @return the {@link entity.User} object
     */
	 User autorization(String login, String password) throws DAOException;
	 
	 /**
	 *Creates database entries from the {@link entity.User} object
	 *
	 * @return the new {@link entity.User} object
	 */
	 User registration (User newUser) throws DAOException;
	 
	/**
	*Modifies the rows of the table user database corresponding to the {@link entity.User} object
	*
	* @return the new {@link entity.User} object
	*/
	 User editProfile(User user) throws DAOException;
	 
	/**
	*Finds the primary key of the user table in the database by its name
	*
	* @return id user (primary key) if if the database contains a user with that name
	*/
	 int find(String name) throws DAOException;
	 
	/**
	*Finds information about all active users in the database
	*
	* @return list contains the {@link entity.Doctor} objects
	*/
	 ArrayList<Doctor> findListOfUser()throws DAOException;
	
	/**
	*Finds information about patients assigned to a specific doctor in the database
	*
	* @return list contains the {@link entity.Patient} objects
	*/
	 List<Patient> findPatientsFromUser(Doctor doctor) throws DAOException;
	 
	/**
	*Finds information about a doctor’s specialization in the database
	*
	* @return specialization
	*/
	 String userRole (String name) throws DAOException;
	 
	/**
	*Finds the primary key (id) of the user in the database by the patient’s id assigned to it
	*
	* @return id user if if the database contains a patient with line idPatient
	*/
	 int findIdUserForPatient(int idPatient) throws DAOException;
		
	/**
	*Modifies the user line 'out', after which the user is not used in the system (considered dismissed)
	*
	*/ 
	 void updateUserOutOf(int userId)throws DAOException;
	 
	/**
	*@return currentTimestamp()
	*
	*/ 
	 Date currentTimestamp() throws DAOException;
	 
	/**
	*Finds in the database all dismissed users (users with the string 'out' is false)
	*
	* @return list contains the {@link entity.Doctor} objects
	*/ 
	 ArrayList<Doctor> findListOfLeaveUser()throws DAOException;
}		
