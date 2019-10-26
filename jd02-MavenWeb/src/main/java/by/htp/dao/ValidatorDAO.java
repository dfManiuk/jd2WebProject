package by.htp.dao;

public interface ValidatorDAO {
	
	boolean checkSpecialization(String specialization) throws DAOException;
	
	boolean forTest(String sqlCommand)throws DAOException; 
}
