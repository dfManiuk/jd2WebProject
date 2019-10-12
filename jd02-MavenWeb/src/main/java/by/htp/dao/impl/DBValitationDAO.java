package by.htp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.htp.dao.SQLCommands;
import by.htp.dao.ValidatorDAO;
import by.htp.pool.ConnectionPool;
import by.htp.pool.ConnectionPoolException;

public class DBValitationDAO implements ValidatorDAO {

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
			// TODO: handle exception
		} catch (ConnectionPoolException e) {
			// TODO: handle exception
		}
			
		return true;
	}
	

}
