
package by.htp.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import by.htp.entity.Doctor;
import by.htp.entity.Patient;
import by.htp.entity.User;

/**
 * Users Data Access Object interface.
  * Provides CRUD operations with User objects.
  * 
 * @author D.Maniuk on 20.10.2019
 * @version 1.0
 */
public interface UserDAO {

	 /**
     *Constructs an authorizations User object from a human-readable login and password.
     * 
     * @return the User objects
     * @param login the nickname of the user
     * @param password this is password
     * @throws DAOException - exception thrown in DAO or connection pool
     */
	 User autorization(String login, String password) throws DAOException;
	 
	 /**
	 *Creates database entries from the User object
	 *
	 * @return the newUser object
	 * @param newUser - new user object with collected data - name, position, position, username and password
	 * @throws DAOException - exception thrown in DAO or connection pool
	 */
	 User registration (User newUser) throws DAOException;
	 
	/**
	*Modifies the rows of the table user database corresponding to the User object
	*
	* @return the new User object
	* @param user - user object for which changes will be made (id cannot be changed)
	* @throws DAOException - exception thrown in DAO or connection pool
	*/
	 User editProfile(User user) throws DAOException;
	 
	/**
	*Finds the primary key of the user table in the database by its name
	*
	* @return id user (primary key) if if the database contains a user with that name
	* @param name - whose username is to be found
	* @throws DAOException - exception thrown in DAO or connection pool
	*/
	 int find(String name) throws DAOException;
	 
	/**
	*Finds information about all active users in the database
	*
	* @return list contains the Doctor objects
	* @throws DAOException - exception thrown in DAO or connection pool
	*/
	 ArrayList<Doctor> findListOfUser()throws DAOException;
	
	/**
	*Finds information about patients assigned to a specific doctor in the database
	*
	* @return list contains the Patient objects
	* @param doctor object patients will be found
	* @throws DAOException - exception thrown in DAO or connection pool
	*/
	 List<Patient> findPatientsFromUser(Doctor doctor) throws DAOException;
	 
	/**
	*Finds information about a doctor’s specialization in the database
	*
	* @return specialization
	* @param name - username
	* @throws DAOException - exception thrown in DAO or connection pool
	*/
	 String userRole (String name) throws DAOException;
	 
	/**
	*Finds the primary key (id) of the user in the database by the patient’s id assigned to it
	*
	* @return id user if if the database contains a patient with line idPatient
	* @param idPatient is primary key of the patient
	* @throws DAOException - exception thrown in DAO or connection pool
	*/
	 int findIdUserForPatient(int idPatient) throws DAOException;
		
	/**
	*Modifies the user line 'out', after which the user is not used in the system (considered dismissed)
	*
	* @param userId is primary key of the user
	* @throws DAOException - exception thrown in DAO or connection pool
	*/ 
	 void updateUserOutOf(int userId)throws DAOException;
	 
	/**
	* @return currentTimestamp()
	* @throws DAOException - exception thrown in DAO or connection pool
	*/ 
	 Date currentTimestamp() throws DAOException;
	 
	/**
	*Finds in the database all dismissed users (users with the string 'out' is false)
	*
	* @return list contains the Doctor objects
	* @throws DAOException - exception thrown in DAO or connection pool
	*/ 
	 ArrayList<Doctor> findListOfLeaveUser()throws DAOException;
}		
