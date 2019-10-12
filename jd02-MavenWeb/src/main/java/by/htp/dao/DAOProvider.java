package by.htp.dao;

import by.htp.dao.impl.DBPatientDAO;
import by.htp.dao.impl.DBUserDAO;
import by.htp.dao.impl.DBValitationDAO;

public class DAOProvider {

	private final static DAOProvider provider = new DAOProvider();

	private UserDAO userDAO = new DBUserDAO();
	private PatientDAO patientDAO = new DBPatientDAO();
	private ValidatorDAO validatorDAO = new DBValitationDAO();

	public PatientDAO getPatientDAO() {
		return patientDAO;
	}

	public void setPatientDAO(PatientDAO patientDAO) {
		this.patientDAO = patientDAO;
	}

	private DAOProvider() {

	}

	public static DAOProvider getInstance() {
		return provider;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public ValidatorDAO getValidatorDAO() {
		return validatorDAO;
	}

}
