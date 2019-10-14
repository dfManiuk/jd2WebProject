package by.htp.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Set;

import by.htp.entity.Card;
import by.htp.entity.Medication;
import by.htp.entity.Patient;
import by.htp.entity.User;


public interface PatientService {
	
	ArrayList<Patient> find (String somethinString) throws ServiceException;

	Patient registration(String name,String passport, String data, String adress, String telephone) throws ServiceException;
	
	Patient registration(Patient patient) throws ServiceException;
	
	void fixing (String user ,String patient) throws ServiceException;
	
	void medication (Medication medication, String patient) throws ServiceException;
	
	ArrayList<Patient> findUserFromPatient (User user) throws ServiceException;
	
	ArrayList<Patient> findUserFromPatient (User user, int start, int delimeter) throws ServiceException;
	
	Patient findFromName(String patientsData) throws ServiceException;
	
	Medication getPatientMedications(Patient patient) throws ServiceException;
	
	void editMedication(int idUser, Medication medication) throws ServiceException;
	
	void deleteMedication(Medication medication) throws ServiceException;
	
	void addMedication(Medication medication) throws ServiceException;
	
	ArrayList<Patient> giveAllPatients() throws ServiceException;
	
	void unFixing (String user ,String patient) throws ServiceException;
	
	int countOfPatients( )throws ServiceException;
	
	int countOfPatientsForUser(User user) throws ServiceException;
	
	void addPhoto(int id, InputStream isFoto) throws ServiceException;
	
	byte[] getPhoto(int id) throws ServiceException;
	
	ArrayList<String> getDiagnosis(int id) throws ServiceException;
	
	void setDiagnosis(String diagnos, int patientId) throws ServiceException;
	
	void addDiagnosis(String diagnos, int patientId) throws ServiceException;
	
	String returnDiagnosiString (int patientId) throws ServiceException;
	
	Card getPatientCard (int patientId) throws ServiceException;
	
	void rightOut (int patientId) throws ServiceException;
	
	ArrayList<Patient> findUserFromPatientForNurse (int start, int delimeter) throws ServiceException;
	
	ArrayList<Integer> findUserFromPatientWithNotCheckMedication (int id, int start, int delimeter) throws ServiceException;
	
	ArrayList<Integer> findUserFromPatientWithNotCheckMedicationForNurse ( int start, int delimeter) throws ServiceException;
	
	void addMedicationPeriosity(Medication medication) throws ServiceException;
	
	void updateMedicationPeriod(String medication, int idPatient, int idUser)throws ServiceException;
	
	ArrayList<Patient> getAllPatientDischarged() throws ServiceException;
	
	ArrayList<Patient> giveAllPatientsLimit(int start, int delimeter) throws ServiceException;
	
	Set<Patient> getAllPatientDischargedSet(int start, int delimeter) throws ServiceException;
}
