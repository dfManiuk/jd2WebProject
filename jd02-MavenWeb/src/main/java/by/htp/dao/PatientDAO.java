package by.htp.dao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import by.htp.entity.Card;
import by.htp.entity.Medication;
import by.htp.entity.Patient;
import by.htp.entity.User;

/**
 * Patients Data Access Object interface.
  * Provides CRUD operations with {@link entity.Patient} objects.
  * 
 * @author D.Maniuk on 20.10.2019
 * @version 1.0
 */
public interface PatientDAO {

	/**
	*Finds information about patients in the database by name,
	*address, phone, date of birth or part of all of the above.
	*
	* @return list contains the {@link entity.Patients} objects
	*/
	 ArrayList<Patient> find(String someString) throws DAOException;
	
	/**
	*Finds information on patients for prescribed medical appointments in the database
	*
	* @return list contains the {@link entity.Patients} objects
	*/
	 ArrayList<Patient> findsFromMedication(String someString) throws DAOException;
	
	/**
	*Registration in the patient information database using the patient entity
	*
	* @return the new {@link entity.Patient} object
	*/
	 Patient registration (Patient newPatient) throws DAOException;
		
	/**
	*Registration in the patient information database 
	*
	* @return the new {@link entity.Patient} object
	*/	 	 
	 Patient registration(String name,String passport, String birth, String adress,  String telephone) throws DAOException;
	 
	/**
	*Assigning a patient to a doctor (data are entered into the database with a many-to-many ratio in the database) 
	*
	*/	 
	 void fixing (String doctor, String patient) throws DAOException;
	 
	/**
	*Detachment of a patient for a doctor (in the database data are entered in a table with a many-to-many ratio) 
	*
	*/
	 void unFixing (String doctor, String patient) throws DAOException;
	 
	/**
	*The database contains data on appointments: operations, medical appointments, prescribed medications.
	*
	*/
	 void medication(Medication medication, String patient) throws DAOException;
	 
	/**
	*@deprecated 
	*
	*/
	 void setCard(Patient patient) throws DAOException;
	 
	/**
	*Searches for a patient in the database by passport number
	*
	* @return the new {@link entity.Patient} object
	*/	 
	 Patient findFromName(String someString) throws DAOException;
	 
	/**
	*Searches for a patient in a database assigned to a specific user.
	*
	* @return list contains the {@link entity.Patients} objects
	*/
	 ArrayList<Patient>  fintPatientFromUser(User user) throws DAOException;
	 
	 /**
	*Searches for medical prescriptions for a specific patient
	*
	* @return the new {@link entity.Patient} object
	*/
	 Medication getPatientMedication (Patient patient) throws DAOException;
	
	 /**
	*Amending medical prescriptions for a specific patient
	*
	*@param the primary key of the user database table
	*@param the {@link entity.Medication} object
	*/ 
	 void updateMedication(int idUser, Medication medication) throws DAOException;
	 
	/**
	*Change of medical prescriptions for a specific patient performed by the user with access "nurse"
	*@see {@link entity.User}
	*@param the {@link entity.Medication} object
	*/ 
	 void updateMedicationFromNurse(Medication medication) throws DAOException;
	 
	 /**
	*Removal of medical prescriptions for a particular patient performed by the user with access “doctor”, “department head”
	*@see {@link entity.User}
	*@param the {@link entity.Medication} object
	*/ 
	 void deleteMedication(Medication medication) throws DAOException;
	 
	 /**
	*The appointment of new medical appointments for a particular patient performed by the user with access “doctor”, “department head”
	*@see {@link entity.User}
	*@param the {@link entity.Medication} object
	*/ 	 
	 void addMedication(Medication medication)throws DAOException; 
	 
	 ArrayList<Patient> getAllPatient() throws DAOException;
	 
	 int countOfpatient( ) throws DAOException;
	 
	 int countOfpatientForUser(User user ) throws DAOException;
	 
	 ArrayList<Patient> fintPatientFromUserWithLimit(User user, int start, int delimeter ) throws DAOException;
	 
	 byte[] getPhoto(int id) throws DAOException;

	void addPhoto(int id, InputStream in) throws DAOException;
	
	ArrayList<String> getDiagnosis(int idPatient) throws DAOException;
	
	void setDiagnosis(String diagnos, int UserId) throws DAOException;
	
	void addDiagnosis (String diagnos, int UserId) throws DAOException;
	
	String getPatientDiagnosis(int UserId) throws DAOException;
	
	Card getPatientCard (int UserId) throws DAOException;
	
	int getIdUserfromPatient(int idPatient) throws DAOException;
	
	void rightOut(int idPatient) throws DAOException;
	
	ArrayList<Patient> fintPatientFromNursWithLimit( int start, int delimeter ) throws DAOException;
	
	 int countOfNotFicksingMedication(int idPatient ) throws DAOException;
	 
	 ArrayList<Integer> fintPatientFromUserWithLimitWithNotCheckMedication(int userId, int start, int delimeter ) throws DAOException;
	 
	 ArrayList<Integer> fintPatientFromUserWithLimitWithNotCheckMedicationForNurse(int start, int delimeter ) throws DAOException;

	void addMedicationPeriocity(Medication medication) throws DAOException;
	
	HashMap<Integer, List<Object>> getMedicationWithPeriosityAndTimestamp  (int idUser) throws DAOException;
	
	void updateMedicationTimeLost(Date data,String medication, int idPatient) throws DAOException;
	
	void updateMedicationPeriod(String medication, int idPatient, int idUser) throws DAOException;
	
	 ArrayList<Patient> getAllPatientDischarged() throws DAOException;

	ArrayList<Patient> getAllPatientLimit(int start, int delimeter)throws DAOException;
	
	Set<Patient> getAllPatientDischargedSet(int start, int delimeter) throws DAOException;
}
