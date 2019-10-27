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
  * Provides CRUD operations with Patient objects.
  * 
 * @author D.Maniuk on 20.10.2019
 * @version 1.0
 */
public interface PatientDAO {

	/**
	*Finds information about patients in the database by name,
	*address, phone, date of birth or part of all of the above.
	*
	* @param someString this is any string characterizing the user
	* @return list contains the Patients objects
	* @throws DAOException - this is exception of dao and connection pool
	*/
	 ArrayList<Patient> find(String someString) throws DAOException;
	
	/**
	*Finds information on patients for prescribed medical appointments in the database
	*
	* @param someString this is any string characterizing the user
	* @return list contains the Patients objects
	* @throws DAOException - this is exception of dao and connection pool
	*/
	 ArrayList<Patient> findsFromMedication(String someString) throws DAOException;
	
	/**
	*Registration in the patient information database using the patient entity
	*
	* @param newPatient (Patient Object) 
	* @return the new Patient object
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	 Patient registration (Patient newPatient) throws DAOException;
		
	/**
	*Registration in the patient information database 
	*
	* @param name this is name of patient
	* @param passport - this is the number of patient passport,
	* @param birth - patient's birthday
	* @param adress - this is patient's place of residence, 
	* @param telephone - this is telephone number
	* @return the new Patient object
	* @throws DAOException - exception raised in DAO or connection pool
	*/	 	 
	 Patient registration(String name,String passport, String birth, String adress,  String telephone) throws DAOException;
	 
	/**
	*Assigning a patient to a doctor (data are entered into the database with a many-to-many ratio in the database) 
	*
	* @param doctor is the name of the attending physician for the patient
	* @param patient - name of the patient
	* @throws DAOException - exception raised in DAO or connection pool
	*/	 
	 void fixing (String doctor, String patient) throws DAOException;
	 
	/**
	*Detachment of a patient for a doctor (in the database data are entered in a table with a many-to-many ratio) 
	*
	* @param doctor is the name of the attending physician for the patient
	* @param patient - name of the patient
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	 void unFixing (String doctor, String patient) throws DAOException;
	 
	/**
	*The database contains data on appointments: operations, medical appointments, prescribed medications.
	*
	* @param patient - name of the patient
	* @param medication - medical appointment
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	 void medication(Medication medication, String patient) throws DAOException;
	 
	/**
	* @deprecated 
	* @param patient - name of the patient
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	 void setCard(Patient patient) throws DAOException;
	 
	/**
	*Searches for a patient in the database by passport number
	*
	* @param someString - name and surname or date of birth or passport number or place of residence of the patient
	* @return the new .Patient object
	* @throws DAOException - exception raised in DAO or connection pool
	*/	 
	 Patient findFromName(String someString) throws DAOException;
	 
	/**
	*Searches for a patient in a database assigned to a specific user.
	*
	* @param user - Object assigned to patients
	* @return list contains the Patients objects
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	 ArrayList<Patient>  fintPatientFromUser(User user) throws DAOException;
	 
	 /**
	*Searches for medical prescriptions for a specific patient
	*
	* @param patient - Patient object
	* @return the new Patient object
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	 Medication getPatientMedication (Patient patient) throws DAOException;
	
	 /**
	*Amending medical prescriptions for a specific patient
	*
	* @param idUser is the primary key of the user database table
	* @param medication is the Medication object
	* @throws DAOException - exception raised in DAO or connection pool
	*/ 
	 void updateMedication(int idUser, Medication medication) throws DAOException;
	 
	/**
	*Change of medical prescriptions for a specific patient performed by the user with access "nurse"
	* @see User
	* @param medication - the Medication object
	* @throws DAOException - exception raised in DAO or connection pool
	*/ 
	 void updateMedicationFromNurse(Medication medication) throws DAOException;
	 
	 /**
	*Removal of medical prescriptions for a particular patient performed by the user with access “doctor”, “department head”
	* @see User
	* @param medication - the Medication object
	* @throws DAOException - exception raised in DAO or connection pool
	*/ 
	 void deleteMedication(Medication medication) throws DAOException;
	 
	 /**
	*The appointment of new medical appointments for a particular patient performed by the user with access “doctor”, “department head”
	* @see User object
	* @param medication - the Medication object
	* @throws DAOException - exception raised in DAO or connection pool
	*/ 	 
	 void addMedication(Medication medication)throws DAOException; 
	 
	/**
	*Searches for all patients in a database.
	*
	* @return list contains the Patients objects
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	 ArrayList<Patient> getAllPatient() throws DAOException;
	 
	/**
	*Return count of all patient in a database.
	*
	* @return integer 
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	 int countOfpatient( ) throws DAOException;
	
	 /**
	*Return count of all patient in a database.
	*
	* @param user - this is User object
	* @return integer 
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	 int countOfpatientForUser(User user) throws DAOException;
	 
	 /**
	*Searches for a patient in a database assigned to a specific user.
	*
	* @return list contains the Patients objects
	* @param user - the user Medication object
	* @param start - the start is the beginning of the selection in a database
	* @param delimeter - the delimeter is he number of rows in the selection
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	 ArrayList<Patient> fintPatientFromUserWithLimit(User user, int start, int delimeter ) throws DAOException;
	
	 /**
	*Return the byte array of the personal photo
	*
	* @param id - the id is primary key of the patient
	* @return the byte array 
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	 byte[] getPhoto(int id) throws DAOException;

	 /**
	*Add the byte array of the personal photo
	*
	* @param id is primary key of the patient
	* @param  in is InputStream {@link java.io.InputStream}
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	void addPhoto(int id, InputStream in) throws DAOException;
	
	 /**
	*Searches diagnoses for the patient in a database assigned to a specific user.
	*
	* @return list contains the diagnoses for the patient
	* @param idPatient is primary key of the patient
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	ArrayList<String> getDiagnosis(int idPatient) throws DAOException;
	
	/**
	*Establish a diagnosis for a patient in a database assigned to a specific user.
	*
	* @param  UserId is primary key of the patient
	* @param diagnos is diagnosis for a patient
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	void setDiagnosis(String diagnos, int UserId) throws DAOException;
	
	/**
	*Add a diagnosis for a patient in a database assigned to a specific user.
	*
	* @param UserId is primary key of the patient
	* @param diagnos is diagnosis for a patient
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	void addDiagnosis (String diagnos, int UserId) throws DAOException;
	
	/**
	*Return a diagnosis for a patient in a database assigned to a specific user.
	*
	* @return patient diagnosis
	* @param UserId is primary key of the patient
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	String getPatientDiagnosis(int UserId) throws DAOException;
	
	/**
	*Find the identification card of diagnosis for the patient in a database assigned to a specific user.
	* @return Card object
	* @param UserId is primary key of the patient
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	Card getPatientCard (int UserId) throws DAOException;
	
	/**
	*Find primary key of the user for the patient in a database.
	* @return  primary key of the user
	* @param idPatient is primary key of the patient
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	int getIdUserfromPatient(int idPatient) throws DAOException;
	
	/**
	*Mark the patient in a database like written out
	* @param idPatient is primary key of the patient
	* @throws DAOException - exception raised in DAO or connection pool 
	*/
	void rightOut(int idPatient) throws DAOException;
	
	 /**
	*Searches for a patient in a database assigned to a specific user is used for user .
	*
	* @return list contains the Patients objects
	* @param start is the beginning of the selection in a database with access “nurse”
	* @param delimeter is he number of rows in the selection
	* @throws DAOException - exception raised in DAO or connection pool 
	*/
	ArrayList<Patient> fintPatientFromNursWithLimit( int start, int delimeter ) throws DAOException;
	
	/**
	*Find the number of unfulfilled medical medicines 
	* @return  number of unfulfilled medical medicines
	* @param idPatient is primary key of the patient
	* @throws DAOException - exception raised in DAO or connection pool 
	*/
	 int countOfNotFicksingMedication(int idPatient ) throws DAOException;
	 
	 /**
	*Searches for a patient in a database assigned to a specific user with unfulfilled medical medicines .
	*
	* @return list contains the primary key of the user
	* @param  userId is primary key of the user
	* @param  start is the beginning of the selection in a database
	* @param delimeter is he number of rows in the selection
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	 ArrayList<Integer> fintPatientFromUserWithLimitWithNotCheckMedication(int userId, int start, int delimeter ) throws DAOException;
	 
	 /**
	*Searches for a patient in a database assigned to a specific user with unfulfilled medical medicines .
	*
	* @return list contains the primary key of the user
	* @param start is the beginning of the selection in a database with access “nurse”
	* @param delimeter is he number of rows in the selection
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	 ArrayList<Integer> fintPatientFromUserWithLimitWithNotCheckMedicationForNurse(int start, int delimeter ) throws DAOException;

	 /**
	*Add in a database assigned medical medicines which will be done periodically .
	*
	* @param medication - Medication object
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	void addMedicationPeriocity(Medication medication) throws DAOException;
	
	/**
	*Find in the database medicines not given out on time.
	*
	* @return HashMap which contains array of objects of contents ordered by sampling order
	* overdue primary key of the patient, dispense of medicine, time of dispensing of medicine.
	* @param idUser is primary key of the user
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	HashMap<Integer, List<Object>> getMedicationWithPeriosityAndTimestamp  (int idUser) throws DAOException;
	
	/**
	* Marks dispensing as expired
	*
	* @param data - the java.util.Date
	* @param medication - the Medication object
	* @param idPatient - the id is primary key of the Patient object
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	void updateMedicationTimeLost(Date data,String medication, int idPatient) throws DAOException;
	
	/**
	*Point to the frequency of drug dispensing (number of times a day);
	*
	* @param medication - the Medication object
	* @param idPatient is primary key of the User object
	* @param idUser is primary key of the Patient object
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	void updateMedicationPeriod(String medication, int idPatient, int idUser) throws DAOException;
	
	/**
	*Searches in a database written out patients.
	*
	* @return list contains Patient object
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	 ArrayList<Patient> getAllPatientDischarged() throws DAOException;
	 
	 /**
	*Searches in a database all patients being treated.
	*
	* @param start is the beginning of the selection in a database
	* @param delimeter is he number of rows in the selection
	* @return list contains Patient object
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	ArrayList<Patient> getAllPatientLimit(int start, int delimeter)throws DAOException;
	
	/**
	*Searches in a database all written out  patients .
	*
	* @param  start is the beginning of the selection in a database
	* @param  delimeter is he number of rows in the selection
	* @return list contains Patient object
	* @throws DAOException - exception raised in DAO or connection pool
	*/
	Set<Patient> getAllPatientDischargedSet(int start, int delimeter) throws DAOException;
}
