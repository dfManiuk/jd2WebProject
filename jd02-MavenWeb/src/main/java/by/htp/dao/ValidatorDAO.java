package by.htp.dao;
/**
 * @author D.Maniuk on 20.10.2019
 * @version 1.0
 */
public interface ValidatorDAO {
	
	/**
     *Check Specialization
     * 
     * @return boolean
     * @param specialization the specialization of the doctor's specialization (specialization should be on the list of admissible specializations)
     * @throws DAOException - exception thrown in DAO or connection pool
     */
	boolean checkSpecialization(String specialization) throws DAOException;
	
	
	boolean forTest(String sqlCommand)throws DAOException; 
}
