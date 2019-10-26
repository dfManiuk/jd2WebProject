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
	 
	/**
	*Searches for all patients in a database.
	*
	* @return list contains the {@link entity.Patients} objects
	*/
	 ArrayList<Patient> getAllPatient() throws DAOException;
	 
	/**
	*Return count of all patient in a database.
	*
	* @return integer 
	*/
	 int countOfpatient( ) throws DAOException;
	
	 /**
	*Return count of all patient in a database.
	*
	* @param the {@link entity.Medication} object
	* @return integer 
	*/
	 int countOfpatientForUser(User user) throws DAOException;
	 
	 /**
	*Searches for a patient in a database assigned to a specific user.
	*
	* @return list contains the {@link entity.Patients} objects
	* @param the user {@link entity.Medication} object
	* @param the start is the beginning of the selection in a database
	* @param the delimeter is he number of rows in the selection
	*/
	 ArrayList<Patient> fintPatientFromUserWithLimit(User user, int start, int delimeter ) throws DAOException;
	
	 /**
	*Return the byte array of the personal photo
	*
	* @param the id is primary key of the patient
	* @return the byte array 
	*/
	 byte[] getPhoto(int id) throws DAOException;

	 /**
	*Add the byte array of the personal photo
	*
	* @param the id is primary key of the patient
	* @param the in is InputStream {@link java.io.InputStream}
	*/
	void addPhoto(int id, InputStream in) throws DAOException;
	
	 /**
	*Searches diagnoses for the patient in a database assigned to a specific user.
	*
	* @return list contains the diagnoses for the patient
	* @param the id is primary key of the patient
	*/
	ArrayList<String> getDiagnosis(int idPatient) throws DAOException;
	
	/**
	*Establish a diagnosis for a patient in a database assigned to a specific user.
	*
	* @param the id is primary key of the patient
	* @param the diagnos is diagnosis for a patient
	*/
	void setDiagnosis(String diagnos, int UserId) throws DAOException;
	
	/**
	*Add a diagnosis for a patient in a database assigned to a specific user.
	*
	* @param the id is primary key of the patient
	* @param the diagnos is diagnosis for a patient
	*/
	void addDiagnosis (String diagnos, int UserId) throws DAOException;
	
	/**
	*Return a diagnosis for a patient in a database assigned to a specific user.
	*
	* @param the id is primary key of the patient
	*/
	String getPatientDiagnosis(int UserId) throws DAOException;
	
	/**
	*Find the identification card of diagnosis for the patient in a database assigned to a specific user.
	* @return  {@link entity.Card} object
	* @param the id is primary key of the patient
	*/
	Card getPatientCard (int UserId) throws DAOException;
	
	/**
	*Find primary key of the user for the patient in a database.
	* @return  primary key of the user
	* @param the id is primary key of the patient
	*/
	int getIdUserfromPatient(int idPatient) throws DAOException;
	
	/**
	*Mark the patient in a database like written out
	* @param the id is primary key of the patient
	*/
	void rightOut(int idPatient) throws DAOException;
	
	 /**
	*Searches for a patient in a database assigned to a specific user is used for user .
	*
	* @return list contains the {@link entity.Patients} objects
	* @param the start is the beginning of the selection in a database with access “nurse”
	* @param the delimeter is he number of rows in the selection
	*/
	ArrayList<Patient> fintPatientFromNursWithLimit( int start, int delimeter ) throws DAOException;
	
	/**
	*Find the number of unfulfilled medical medicines 
	* @return  number of unfulfilled medical medicines
	* @param the id is primary key of the patient
	*/
	 int countOfNotFicksingMedication(int idPatient ) throws DAOException;
	 
	 /**
	*Searches for a patient in a database assigned to a specific user with unfulfilled medical medicines .
	*
	* @return list contains the primary key of the user
	* @param the id is primary key of the user
	* @param the start is the beginning of the selection in a database
	* @param the delimeter is he number of rows in the selection
	*/
	 ArrayList<Integer> fintPatientFromUserWithLimitWithNotCheckMedication(int userId, int start, int delimeter ) throws DAOException;
	 
	 /**
	*Searches for a patient in a database assigned to a specific user with unfulfilled medical medicines .
	*
	* @return list contains the primary key of the user
	* @param the id is primary key of the user
	* @param the start is the beginning of the selection in a database with access “nurse”
	* @param the delimeter is he number of rows in the selection
	*/
	 ArrayList<Integer> fintPatientFromUserWithLimitWithNotCheckMedicationForNurse(int start, int delimeter ) throws DAOException;

	 /**
	*Add in a database assigned medical medicines which will be done periodically .
	*
	*@param the {@link entity.Medication} object
	*/
	void addMedicationPeriocity(Medication medication) throws DAOException;
	
	/**
	*Find in the database medicines not given out on time.
	*
	* @return HashMap which contains array of objects of contents ordered by sampling order
	* overdue primary key of the patient, dispense of medicine, time of dispensing of medicine.
	* @param the id is primary key of the user
	*/
	HashMap<Integer, List<Object>> getMedicationWithPeriosityAndTimestamp  (int idUser) throws DAOException;
	
	/**
	*Marks dispensing as expired ;
	*
	*@param the {@see java.util.Date} 
	*@param the {@link entity.Medication} object
	*@param the {@link entity.Medication} object
	* @param the id is primary key of the {@link entity.Patient} object
	*/
	void updateMedicationTimeLost(Date data,String medication, int idPatient) throws DAOException;
	
	/**
	*Point to the frequency of drug dispensing (number of times a day);
	*
	*@param the {@link entity.Medication} object
	* @param the id is primary key of the {@link entity.User} object
	* @param the id is primary key of the {@link entity.Patient} object
	*/
	void updateMedicationPeriod(String medication, int idPatient, int idUser) throws DAOException;
	
	/**
	*Searches in a database written out patients.
	*
	* @return list contains  {@link entity.Patient} object
	*/
	 ArrayList<Patient> getAllPatientDischarged() throws DAOException;
	 
	 /**
	*Searches in a database all patients being treated.
	*
	* @param the start is the beginning of the selection in a database
	* @param the delimeter is he number of rows in the selection
	* @return list contains  {@link entity.Patient} object
	*/
	ArrayList<Patient> getAllPatientLimit(int start, int delimeter)throws DAOException;
	
	/**
	*Searches in a database all written out  patients .
	*
	* @param the start is the beginning of the selection in a database
	* @param the delimeter is he number of rows in the selection
	* @return list contains  {@link entity.Patient} object
	*/
	Set<Patient> getAllPatientDischargedSet(int start, int delimeter) throws DAOException;
}
