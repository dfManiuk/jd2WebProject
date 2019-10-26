package by.htp.service;

import by.htp.dao.DAOException;
import by.htp.dao.DAOProvider;
import by.htp.dao.ValidatorDAO;
import by.htp.entity.Patient;
import by.htp.entity.User;

public class UserDataValidator {
	
private static final UserDataValidator instance = new UserDataValidator();
	
	private UserDataValidator() {}
	
	private static final String PATTERN_USERNAME = "/^[a-z0-9_-]{3,16}$";
	private static final String PATTERN_PASSWORD ="/^[a-z0-9_-]{4,18}$";
	
	public boolean check(User user) throws ServiceException {
		
		String login = user.getLogin();
		String password = user.getPassword();
		String specialization = user.getSpecialization();
		
		if ( login.isEmpty() || password.isEmpty()) {
			return false;
		}
		
		if (user.getPosition().equalsIgnoreCase("врач") & user.getPosition().equalsIgnoreCase("медсестра") 
			& user.getPosition().equalsIgnoreCase("заведующий")) {
			return false;
		}
		ValidatorDAO validatorDAO = DAOProvider.getInstance().getValidatorDAO();
		
		if (!login.matches(PATTERN_USERNAME) && !password.matches(PATTERN_PASSWORD)) {
			try {
				if (!validatorDAO.checkSpecialization(specialization)) {
					return true;
				}
			} catch (DAOException e) {
				throw new ServiceException(e);
			
			}
		}
		return false;
	}
	
	public static UserDataValidator getInstance() {
		return instance;
	}

	public boolean checEditProfile(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean check(Patient patient) {
		String name = patient.getName();	
		String data = patient.getData();
		String passport = patient.getPassport();
	
		if ( name != null || passport != null || data != null ) {
			return true;
		}

		return false;
	}
}
