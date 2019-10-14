package by.htp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.dao.SQLCommands;
import by.htp.dao.ValidatorDAO;
import by.htp.pool.ConnectionPool;
import by.htp.pool.ConnectionPoolException;

public class DBValitationDAO implements ValidatorDAO {

	private static final Logger logger = LogManager.getLogger(DBValitationDAO.class.getName());	
	
	@Override
	public boolean checkSpecialization(String specialization) {
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		
		ConnectionPool cPool = ConnectionPool.getInstance();
		try {
			String specializationCheck = null;
			String sql = SQLCommands.SPECIALIZATION_CHECK;
	    	
	    	con = cPool.takeConnection();
	    	st = con.prepareStatement(sql);
	    	
	    	st.setString(1, specialization);  	
	    	rs = st.executeQuery();
    		
	    	if(rs.next()){
	    		
			specializationCheck = rs.getString(2);
			
			if (specializationCheck != null) {
				return false;
			}	
	    	}
		} catch (SQLException e) {
			logger.error(e.toString());
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
		}finally {	
			cPool.closeConnection(con, st);
		}
			
		return true;
	}

	@Override
	public boolean forTest(String sqlCommand) {
		Connection con=null;
		PreparedStatement st=null;	
		ConnectionPool cPool = ConnectionPool.getInstance();
		try {
			String sql = sqlCommand;	    	
	    	con = cPool.takeConnection();
	    	st = con.prepareStatement(sql);
	    	st.executeUpdate();
    		
	} catch (SQLException e) {
		logger.error(e.toString());
	} catch (ConnectionPoolException e) {
		logger.error(e.toString());
	}finally {	
		cPool.closeConnection(con, st);
	}
		return true;
	}

}
