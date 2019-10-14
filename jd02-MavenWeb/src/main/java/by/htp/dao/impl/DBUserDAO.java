package by.htp.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.htp.dao.DAOException;
import by.htp.dao.SQLCommands;
import by.htp.dao.UserDAO;
import by.htp.entity.Doctor;
import by.htp.entity.Patient;
import by.htp.entity.User;
import by.htp.pool.ConnectionPool;
import by.htp.pool.ConnectionPoolException;

public class DBUserDAO implements UserDAO {

	private static final Logger logger = LogManager.getLogger(DBPatientDAO.class.getName());	
	
	@Override
	public User autorization(String login, String password) throws DAOException  {
		
		User user = null;
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		ConnectionPool cPool = ConnectionPool.getInstance();
	    try {
	    	
	    	user = new User();
	    	String sql = SQLCommands.USER_LOGINATION;
	    	
	    	con = cPool.takeConnection();
	    	st = con.prepareStatement(sql);
	    	
	    	st.setString(1, password);
	    	st.setString(2, login);
	    	
	    	rs = st.executeQuery();
    		
	    	if(rs.next()){
	    		user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPosition( rs.getString(3));
				user.setSpecialization(rs.getString(4));
				user.setLogin(rs.getString(5));
				user.setPassword(rs.getString(6));
	    	}
				 return user;
			
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection( con, st, rs);
		}
		//return user;
	}

	@Override
	public User registration(User newUser) throws DAOException  {
				
		String login = newUser.getLogin();
		String password = newUser.getPassword(); 
		String name = newUser.getName();
		String position = newUser.getPosition();
		String specialization = newUser.getSpecialization();
		int idSpecialization = 0;
		
		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement st = null ;
		ResultSet rs = null;
		
		try {
			String sql =SQLCommands.USER_REGISTRATION; //user specialization
			con = cPool.takeConnection();
			st = con.prepareStatement(sql);
			st.setString(1, specialization);
			rs = st.executeQuery();
			while(rs.next()){
				idSpecialization =	rs.getInt(1);
			}
			String sqString = SQLCommands.USER_REGISTRATION_INSERT;
			PreparedStatement stInsert = con.prepareStatement(sqString);
			stInsert.setString(1, name);
			stInsert.setString(2, position);
			stInsert.setInt(3, idSpecialization);
			stInsert.setString(4, login);
			stInsert.setString(5, password);
			stInsert.executeUpdate();	
			
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, st, rs);
		}
		return newUser; 
				
	}

	@Override
	public int find(String name) throws DAOException {
		int idUser = 0;
		
		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql =SQLCommands.FIND_USER_FROM_NAME;
			con = cPool.takeConnection();
			st = con.prepareStatement(sql);
			st.setString(1, name);
			rs = st.executeQuery();
			while(rs.next()){
				idUser=rs.getInt(1);
			}
			
			return idUser;
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} 	finally {
			cPool.closeConnection(con, st, rs);
		}	
		System.out.println(idUser);
		return idUser;	
	}

	@Override
	public User editProfile(User user){
		if (user != null) {	
		System.out.println("DAOedit");
		Connection con = null;
		PreparedStatement stIn = null;
				
		ConnectionPool cPool = ConnectionPool.getInstance();
		
	    try {
	    
	    	String sql = SQLCommands.USER_EDIT_PROFILE;
	    	con = cPool.takeConnection();
	    	stIn = con.prepareStatement(sql);
	  
			stIn.setString(1, user.getPosition());
			stIn.setString(2, user.getLogin());
			stIn.setString(3, user.getPassword());
			stIn.setString(4, user.getSpecialization());
			stIn.setInt(5, user.getId());
			
			stIn.executeUpdate();
							    
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} finally {
			 cPool.closeConnection(con, stIn);
		}
	    String login = user.getLogin();
	    String password = user.getPassword();
	 
			try {
				user = autorization(login, password);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e.toString());
		} 
	    }
		return user;
	    	
	}

	@Override
	public ArrayList<Doctor> findListOfUser() throws DAOException {
		ConnectionPool cPool = ConnectionPool.getInstance();
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Doctor doctor = null;
		ArrayList<Doctor> list = null;
		try {
			String sql =SQLCommands.FIND_USER;
			con = cPool.takeConnection();
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			list = new ArrayList<Doctor>();
			while(rs.next()){
				doctor = new Doctor();
				doctor.setId(rs.getInt(1));
				doctor.setName(rs.getString(2));
				doctor.setPosition( rs.getString(3));
				doctor.setSpecialization(rs.getString(9));
		
				doctor.setPatients(findPatientsFromUser(doctor));
				
				list.add(doctor);
			}
			
			return list;
		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		} 	finally {
			cPool.closeConnection(con, st, rs);
		}	
		
		return list;	
	}

	@Override
	public List<Patient> findPatientsFromUser(Doctor doctor) throws DAOException {
		ArrayList<Patient> list = null;
		ConnectionPool cPool = ConnectionPool.getInstance();
		String sqlString = SQLCommands.FIND_USER_HAVE_PATIENT;
		Connection con = null;
		Patient patient;
		PreparedStatement stInsert = null;
		ResultSet rs = null;
		
		try {
			list = new ArrayList<Patient>();
			con = cPool.takeConnection();
			stInsert = con.prepareStatement(sqlString);
			stInsert.setInt(1, doctor.getId());
			rs = stInsert.executeQuery();
			
			while (rs.next()) {
				patient= new Patient();
				patient.setIdPatient(rs.getInt(1));
				patient.setName(rs.getString(2));
				patient.setPassport(rs.getString(3));
				patient.setData(rs.getString(4));
				patient.setAdress(rs.getString(5));
				patient.setTelephone(rs.getString(6));
				list.add(patient);
			}

		return list;
	} catch (SQLException e) {
		logger.error(e.toString());
		e.printStackTrace();
	} catch (ConnectionPoolException e) {
		logger.error(e.toString());
		e.printStackTrace();
	} finally {
		cPool.closeConnection(con, stInsert, rs);
	}
		return list;
	}

	@Override
	public String userRole(String name) throws DAOException {
		String role =null;
		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql =SQLCommands.FIND_USER_ROLE;
			con = cPool.takeConnection();
			st = con.prepareStatement(sql);
			st.setString(1, name);
			rs = st.executeQuery();
			if(rs.next()){
				role=rs.getString(1);
			}			
		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		} 	finally {
			cPool.closeConnection(con, st, rs);
		}		
		return role;	
	}

	@Override
	public int findIdUserForPatient(int idPatient) throws DAOException {
		int id = 0;
		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql =SQLCommands.FIND_USER_ID_FOR_PATIENT;
			con = cPool.takeConnection();
			st = con.prepareStatement(sql);
			st.setInt(1, idPatient);
			rs = st.executeQuery();
			if(rs.next()){
				id=rs.getInt(1);
			}			
		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		} 	finally {
			cPool.closeConnection(con, st, rs);
		}		
		return id;	
	}

	@Override
	public void updateUserOutOf(int userId) throws DAOException {
		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement st = null;
		try {
			String sql = SQLCommands.FIND_USER_ROLE;
			con = cPool.takeConnection();
			st = con.prepareStatement(sql);
			st.setInt(1, userId);
			st.executeUpdate();
						
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		finally {
			cPool.closeConnection(con, st);
		}		
		
	}

	@Override
	public Date currentTimestamp() throws DAOException {

		Calendar UTCCALENDAR = Calendar.getInstance (TimeZone.getTimeZone (ZoneOffset.UTC));
		Date date = null;
		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql =SQLCommands.CURRENT_TIMESTAMP;
			con = cPool.takeConnection();
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			if(rs.next()){
				 date = (rs.getTimestamp(1, UTCCALENDAR));
				 date = new Date(date.getTime());
			}			
		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		} 	finally {
			cPool.closeConnection(con, st, rs);
	}
		return date;	
}

	@Override
	public ArrayList<Doctor> findListOfLeaveUser() throws DAOException {
		ConnectionPool cPool = ConnectionPool.getInstance();
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Doctor doctor = null;
		ArrayList<Doctor> list = null;
		try {
			String sql =SQLCommands.FIND_ALL_USERS_TO_LEAVE;
			con = cPool.takeConnection();
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			list = new ArrayList<Doctor>();
			while(rs.next()){
				doctor = new Doctor();
				doctor.setId(rs.getInt(1));
				doctor.setName(rs.getString(2));
				doctor.setPosition( rs.getString(3));
				doctor.setSpecialization(rs.getString(9));				
				list.add(doctor);
			}
			
			return list;
		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		} 	finally {
			cPool.closeConnection(con, st, rs);
		}	
		
		return list;	
	}	
}


