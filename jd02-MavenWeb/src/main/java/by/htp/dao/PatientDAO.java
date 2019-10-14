package by.htp.dao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import by.htp.entity.Card;
import by.htp.entity.Medication;
import by.htp.entity.Patient;
import by.htp.entity.User;

public interface PatientDAO {

	 ArrayList<Patient> find(String someString) throws DAOException;
	
	 ArrayList<Patient> findsFromMedication(String someString) throws DAOException;
	
	 Patient registration (Patient newPatient) throws DAOException;
	 
	 Patient registration(String name,String passport, String birth, String adress,  String telephone) throws DAOException;
	 
	 void fixing (String doctor, String patient) throws DAOException;
	 
	 void unFixing (String doctor, String patient) throws DAOException;
	 
	 void medication(Medication medication, String patient) throws DAOException;
	 
	 void setCard(Patient patient) throws DAOException;
	 
	 Patient findFromName(String someString) throws DAOException;
	 
	 ArrayList<Patient>  fintPatientFromUser(User user) throws DAOException;
	 
	 Medication getPatientMedication (Patient patient) throws DAOException;
	 
	 void updateMedication(int idUser, Medication medication) throws DAOException;
	 
	 void updateMedicationFromNurse(Medication medication) throws DAOException;
	 
	 void deleteMedication(Medication medication) throws DAOException;
	 
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
}
