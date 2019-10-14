package by.htp.service;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.TimeZone;
import by.htp.dao.DAOException;
import by.htp.dao.DAOProvider;
import by.htp.dao.PatientDAO;
import by.htp.dao.UserDAO;
import by.htp.entity.Doctor;
import by.htp.entity.User;

public class UserServiceImpl implements UserService {

private static final UserDataValidator validator = UserDataValidator.getInstance(); 
private static final Logger logger = LogManager.getLogger(UserServiceImpl.class.getName());
	
	@Override
	public User authorization(String login, String password)  throws ServiceException {
				
		UserDAO userDao = DAOProvider.getInstance().getUserDAO();
		PatientDAO patientDAO = DAOProvider.getInstance().getPatientDAO();
		
		final long HOUR = 3600*1000;
		Date dateTempFromMap = null;
		Date dateTempFromUse = null;
		Date timeCurrent = null;
		Date timeCurrentFromUse = null;
		User user;
		int comparator = 0;
		
		try {
			user = userDao.autorization(login, password);		
			timeCurrent = userDao.currentTimestamp();
			
			HashMap<Integer, List<Object>> map = patientDAO.getMedicationWithPeriosityAndTimestamp(user.getId());
					
			Iterator<Entry<Integer, List<Object>>> iterator = map.entrySet().iterator();			
			while (iterator.hasNext()) {
				Entry<Integer, List<Object>> entrySetHashMap = iterator.next();
				
				System.out.println("Key: " + entrySetHashMap.getKey() + " Value: " + entrySetHashMap.getValue());
				
				ArrayList<Object> list = (ArrayList<Object>) entrySetHashMap.getValue();
				
				dateTempFromMap = (Date)list.get(1);
				dateTempFromMap = new Date(dateTempFromMap.getTime()-3*HOUR); 
				dateTempFromUse = new Date(dateTempFromMap.getTime() + (24/(int) list.get(2) * HOUR));
				timeCurrentFromUse = new Date(timeCurrent.getTime()-3*HOUR);
							
				System.out.println(  "dateTempFromUse = " + dateTempFromUse + "<>" +"dateTempFromMap = "+ dateTempFromMap + 
						"<>" +"timeCurrent = " + timeCurrentFromUse.toString());
				
				comparator = dateTempFromUse.compareTo(timeCurrentFromUse);
			if (comparator < 0) {
				System.out.println("����� �������");				
				int idPatient = (Integer) list.get(3);
				
					try {
						patientDAO.updateMedicationTimeLost( (Date)list.get(1), (String)list.get(0), idPatient );
					} catch (DAOException e) {
						logger.error(e.toString());
						e.printStackTrace();
						throw new ServiceException(e);		
					}				
			} 
		}
		}catch(DAOException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new ServiceException(e);
		}
		
		return user;
	}
		
	public String timerParser(final String[] times) throws ParseException {
	    long tm = 0;
	    DateFormat dt = new SimpleDateFormat("HH:mm:ss");
	    Calendar c = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
	    for (String tmp : times) {
	        c.setTime(dt.parse(tmp));
	        tm += c.get(Calendar.SECOND) + 60 * c.get(Calendar.MINUTE) + 3600 * c.get(Calendar.HOUR_OF_DAY);
	    }	    
	    long hh = tm / 3600;
        tm %= 3600;
        long mm = tm / 60;
        tm %= 60;
        long ss = tm;
       String string = (format(hh) + ":" + format(mm) + ":" + format(ss));
       return string;	   
	}
	
	private String format(long s){
        if (s < 10) return "0" + s;
        else return "" + s;
	}
	
	@Override
	public User editProfile(User user) throws ServiceException {
		if (!validator.checEditProfile(user)) {
			throw new ServiceException("Exception");
		}
		System.out.println(user.toString());
		
		
		UserDAO userDao = DAOProvider.getInstance().getUserDAO();
	
		try {
			user = userDao.editProfile(user); 
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
	
		return user;
	}

	@Override
	public User registration(User newUser) throws ServiceException {
		User user  = null;
		if (!validator.check( newUser)) {
			return user;
		}
		UserDAO userDao = DAOProvider.getInstance().getUserDAO();
		
		try {
			user = userDao.registration(newUser);
			
		} catch (DAOException e ) {
			throw new ServiceException(e);
		}
		
		return user;
	}

	@Override
	public List<Doctor> findAllDoctors() throws ServiceException {
		UserDAO userDao = DAOProvider.getInstance().getUserDAO();
		List<Doctor> doctors;
		
		try {
			 doctors = userDao.findListOfUser();
			
		} catch (DAOException e ) {
			throw new ServiceException(e);
		}
		
		return doctors;
	}

	@Override
	public String userRole(String name) throws ServiceException {
		UserDAO userDao = DAOProvider.getInstance().getUserDAO();
		String userRole = null;
		
		try {
			 userRole = userDao.userRole(name);
			
		} catch (DAOException e ) {
			throw new ServiceException(e);
		}
		
		return userRole;
	}

	@Override
	public int findUserfromPatient(int idPatient) throws ServiceException {
		UserDAO userDao = DAOProvider.getInstance().getUserDAO();
		int id = 0;
		
		try {
			 id = userDao.findIdUserForPatient(idPatient);
			
		} catch (DAOException e ) {
			throw new ServiceException(e);
		}
		
		return id;
	}

	@Override
	public void updateUserOutOf(int userId) throws ServiceException {
		UserDAO userDao = DAOProvider.getInstance().getUserDAO();
		
		try {
			userDao.updateUserOutOf(userId);
			
		} catch (DAOException e ) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Doctor> findAllDoctorsToLeave() throws ServiceException {
		UserDAO userDao = DAOProvider.getInstance().getUserDAO();
		List<Doctor> doctors;
		
		try {
			 doctors = userDao.findListOfLeaveUser();
			
		} catch (DAOException e ) {
			throw new ServiceException(e);
		}
		
		return doctors;
	}
	

}
