package by.htp.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.htp.dao.DAOException;
import by.htp.dao.DAOProvider;
import by.htp.dao.PatientDAO;
import by.htp.entity.Card;
import by.htp.entity.Medication;
import by.htp.entity.Patient;
import by.htp.entity.User;

public class PatientServiseImpl implements PatientService{

	private static final Logger logger = LogManager.getLogger(PatientServiseImpl.class.getName());
	
	@Override
	public Patient registration(String name,String passport, String birth, String adress,  String telephone) throws ServiceException {
		// Validation
		if(name == null || birth == null || adress == null || telephone == null) {
		//TODO throw new Exception()
	}
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		Patient patient = null;
		try {
			patient = patientDAO.registration(name, passport, birth, adress, telephone);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return patient;
	}

	@Override
	public Patient registration(Patient patient) throws ServiceException {
		
		patient = registration(patient.getName(), patient.getPassport(), patient.getData(), patient.getAdress(), patient.getTelephone());
		
		return patient;
		
		
	}

	@Override
	public ArrayList<Patient> find(String string) {
		ArrayList<Patient> list = null;
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			list = patientDAO.find(string);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());		
		}
		return list;
	}

	@Override
	public void fixing(String doctor, String patient) throws ServiceException {
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			patientDAO.fixing(doctor, patient);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		
	}

	@Override
	public void medication(Medication medication, String patient) throws ServiceException {
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			patientDAO.medication(medication, patient);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
	}

	@Override
	public ArrayList<Patient> findUserFromPatient(User user) throws ServiceException {
		ArrayList<Patient> list = null;
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			list = patientDAO.fintPatientFromUser(user);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public Patient findFromName(String patientsData) throws ServiceException {
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		Patient patient = null;
		try {
			patient = patientDAO.findFromName(patientsData);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return patient;
	}

	@Override
	public Medication getPatientMedications(Patient patient) throws ServiceException {
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		Medication medication = null;
		try {
			medication = patientDAO.getPatientMedication(patient);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return medication;

	}

	@Override
	public void editMedication(int idUser, Medication medication) throws ServiceException {
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			patientDAO.updateMedication(idUser, medication);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
	}

	@Override
	public void deleteMedication(Medication medication) throws ServiceException {
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			patientDAO.deleteMedication(medication);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
	}

	@Override
	public void addMedication(Medication medication) throws ServiceException {
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			patientDAO.addMedication(medication);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		
	}

	@Override
	public ArrayList<Patient> giveAllPatients() throws ServiceException {
		ArrayList<Patient> list = null;
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			list = patientDAO.getAllPatient();
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public void unFixing(String user, String patient) throws ServiceException {
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			patientDAO.unFixing(user, patient);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
	}

	@Override
	public ArrayList<Patient> findUserFromPatient(User user, int start, int delimeter) throws ServiceException {
		ArrayList<Patient> list = null;
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			list = patientDAO.fintPatientFromUserWithLimit(user, start, delimeter);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public int countOfPatients() throws ServiceException {
		int count = 0;
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			count = patientDAO.countOfpatient();
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return count;
	}

	@Override
	public int countOfPatientsForUser(User user) throws ServiceException {
		int count = 0;
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			count = patientDAO.countOfpatientForUser(user);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return count;
	}

	@Override
	public void addPhoto(int id, InputStream in) {
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			patientDAO.addPhoto(id, in);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		
	}

	@Override
	public byte[] getPhoto(int id) throws ServiceException {
		byte[] imageBytes = null;
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			imageBytes = patientDAO.getPhoto(id);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return imageBytes;
	}

	@Override
	public ArrayList<String> getDiagnosis(int id) throws ServiceException {
		ArrayList<String> list = null;
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			list = patientDAO.getDiagnosis(id);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public void setDiagnosis(String diagnos, int patientId) throws ServiceException {
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			patientDAO.setDiagnosis(diagnos, patientId);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
	}

	@Override
	public void addDiagnosis(String diagnos, int patientId) throws ServiceException {
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			patientDAO.addDiagnosis(diagnos, patientId);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		
	}

	@Override
	public String returnDiagnosiString(int patientId) throws ServiceException {
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		String diagnos = null;
		try {
			diagnos = patientDAO.getPatientDiagnosis(patientId);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return diagnos;
	}

	@Override
	public Card getPatientCard(int userId) throws ServiceException {
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		Card card = null;
		try {
			card = patientDAO.getPatientCard(userId);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return card;
	}

	@Override
	public void rightOut(int patientId) throws ServiceException {
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			patientDAO.rightOut(patientId);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
	}

	@Override
	public ArrayList<Patient> findUserFromPatientForNurse(int start, int delimeter) throws ServiceException {
		ArrayList<Patient> list = null;
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			list = patientDAO.fintPatientFromNursWithLimit(start, delimeter);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public ArrayList<Integer> findUserFromPatientWithNotCheckMedication(int id, int start, int delimeter)
			throws ServiceException {
		ArrayList<Integer> list = null;
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			list = patientDAO.fintPatientFromUserWithLimitWithNotCheckMedication(id, start, delimeter);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public ArrayList<Integer> findUserFromPatientWithNotCheckMedicationForNurse(int start, int delimeter)
			throws ServiceException {
		ArrayList<Integer> list = null;
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			list = patientDAO.fintPatientFromUserWithLimitWithNotCheckMedicationForNurse(start, delimeter);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public void addMedicationPeriosity(Medication medication) throws ServiceException {
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			patientDAO.addMedicationPeriocity(medication);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		
	}

	@Override
	public void updateMedicationPeriod(String medication, int idPatient, int idUser) throws ServiceException {
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			patientDAO.updateMedicationPeriod(medication, idPatient, idUser);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		
	}

	@Override
	public ArrayList<Patient> getAllPatientDischarged() throws ServiceException {
		ArrayList<Patient> list = null;
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			list = patientDAO.getAllPatientDischarged();
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public ArrayList<Patient> giveAllPatientsLimit(int start, int delimeter) throws ServiceException {
		ArrayList<Patient> list = null;
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			list = patientDAO.getAllPatientLimit(start, delimeter  );
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public Set<Patient> getAllPatientDischargedSet(int start, int delimeter) throws ServiceException {
		Set<Patient> set = null;
		DAOProvider provider = DAOProvider.getInstance();
		PatientDAO patientDAO = provider.getPatientDAO();
		try {
			set = patientDAO.getAllPatientDischargedSet(start, delimeter);
		} catch (DAOException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return set;
	}

	
		
	

}
