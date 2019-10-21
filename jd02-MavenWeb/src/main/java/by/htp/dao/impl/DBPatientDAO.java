package by.htp.dao.impl;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.htp.dao.DAOException;
import by.htp.dao.DAOProvider;
import by.htp.dao.PatientDAO;
import by.htp.dao.SQLCommands;
import by.htp.entity.Card;
import by.htp.entity.Medication;
import by.htp.entity.Patient;
import by.htp.entity.User;
import by.htp.pool.ConnectionPool;
import by.htp.pool.ConnectionPoolException;

public class DBPatientDAO implements PatientDAO {

	private static final Logger logger = LogManager.getLogger(DBPatientDAO.class.getName());	

	@Override
	public Patient registration(Patient newPatient) throws DAOException {
		return newPatient;
		// TODO Auto-generated method stub
	}

	@Override
	public Patient registration(String name, String passport, String birth, String adress, String telephone)
			throws DAOException {

		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement stInsert = null;
		Patient patient = null;
		ResultSet rs = null;
		try {
			patient = new Patient();
			String sqString = SQLCommands.PATIENT_REGISTRATION;
			String sqStringCard = SQLCommands.SET_CARD;
			con = cPool.takeConnection();
			stInsert = con.prepareStatement(sqString, stInsert.RETURN_GENERATED_KEYS);
			stInsert.setString(1, name);
			stInsert.setString(2, passport);
			stInsert.setString(3, birth);
			stInsert.setString(4, adress);
			stInsert.setString(5, telephone);
			int affectedRows = stInsert.executeUpdate();
			if (affectedRows == 0) {
				logger.error("Creating user failed, no rows affected.");
				throw new DAOException("Creating user failed, no rows affected.");
	        }
			rs = stInsert.getGeneratedKeys();
			if (rs.next()){
			    patient.setIdPatient(rs.getInt(1));
			}
			stInsert.close();
			stInsert = con.prepareStatement(sqStringCard, stInsert.RETURN_GENERATED_KEYS);
			stInsert.setString(1, passport);
			int affectedRows2 = stInsert.executeUpdate();
			if (affectedRows2 == 0) {
				logger.error("Creating user failed, no rows affected.");
				throw new DAOException("Creating user failed, no rows affected.");
	        }
			rs = stInsert.getGeneratedKeys();
			if (rs.next()){
			    patient.setIdCard(rs.getInt(1));
			}
			stInsert.close();
			
			patient.setName(name);
			patient.setAdress(adress);
			patient.setData(birth);
			patient.setTelephone(telephone);
			
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);			
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {	
			cPool.closeConnection(con, stInsert);
		}
		return patient;
	}

	@Override
	public ArrayList<Patient> find(String patientsData) throws DAOException {

		patientsData = "%"+ patientsData + "%";
		HashSet<Patient> set = null;
		ArrayList<Patient> list = null;
		ConnectionPool cPool = ConnectionPool.getInstance();
		String sqlString = SQLCommands.PATIENT_FIND;
		Connection con = null;
		Patient patient;
		PreparedStatement stInsert = null;
		ResultSet rs = null;
		try {
			set = new HashSet<Patient>();
			con = cPool.takeConnection();
			stInsert = con.prepareStatement(sqlString);
			stInsert.setString(1, patientsData);
			stInsert.setString(2, patientsData);
			stInsert.setString(3, patientsData);
			stInsert.setString(4, patientsData);
			stInsert.setString(5, patientsData);
			stInsert.setString(6, patientsData);
			rs = stInsert.executeQuery();
			while (rs.next()) {
				patient = new Patient();
				patient.setIdPatient(rs.getInt(1));
				patient.setName(rs.getString(2));
				patient.setPassport(rs.getString(3));
				patient.setData(rs.getString(4));
				patient.setAdress(rs.getString(5));
				patient.setTelephone(rs.getString(6));
				set.add(patient);
			}

			list = new ArrayList<Patient>(set);
			return list;

		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, stInsert, rs);
		}

	}

	@Override
	public void fixing(String doctor, String patient) throws DAOException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		int idUser = daoProvider.getUserDAO().find(doctor);
		int idPatient = 0;
		ConnectionPool cPool = ConnectionPool.getInstance();
		try {
			Connection con = cPool.takeConnection();
			String sqlStringFindid = SQLCommands.PATIENT_FIND_FROM_NAME;

			PreparedStatement st = con.prepareStatement(sqlStringFindid);
			st.setString(1, patient);
			ResultSet rs = st.executeQuery();

			rs = st.executeQuery();
			while (rs.next()) {
				idPatient = rs.getInt(1);
			}

			String sqlFixingString = SQLCommands.USER_HAS_PATIENT;
			st = con.prepareStatement(sqlFixingString);
			st.setInt(1, idUser);
			st.setInt(2, idPatient);
			st.executeUpdate();
			cPool.closeConnection(con, st, rs);
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public void medication(Medication medication, String patient) throws DAOException {
		ConnectionPool cPool = ConnectionPool.getInstance();
		DAOProvider daoProvider = DAOProvider.getInstance();
		Patient objectPatient = daoProvider.getPatientDAO().findFromName(patient);
		Connection con = null;
		PreparedStatement st = null;
		try {

			con = cPool.takeConnection();
			String sql = SQLCommands.SET_MEDICATION;
			st = con.prepareStatement(sql);
			st.setString(1, medication.getProcedure());
			st.setString(2, medication.getMedication());
			st.setString(3, medication.getOperation());
			st.setInt(4, objectPatient.getIdPatient());
			st.executeUpdate();

		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		}catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, st);
		}
	}

	@Override
	public ArrayList<Patient> findsFromMedication(String patientsData) throws DAOException {
		String patientsDataAll = patientsData + "%";
		ConnectionPool cPool = ConnectionPool.getInstance();
		String sqlStringCount = SQLCommands.COUNTS_OF_PATIENT_MEDICATIONS;
		String sqlString = SQLCommands.PATIENT_FIND_FROM_MEDICATION;
		Connection con = null;
		PreparedStatement stInsertCount;
		ArrayList<Patient> patients = null;
		ResultSet rs2;
		PreparedStatement stInsert = null;
		int count = 0; // couunt of patient medication
		try {
			con = cPool.takeConnection();
			stInsertCount = con.prepareStatement(sqlStringCount);
			stInsertCount.setString(1, patientsDataAll);
			stInsertCount.setString(2, patientsDataAll);
			stInsertCount.setString(3, patientsDataAll);

			ResultSet rs = stInsertCount.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			patients = new ArrayList<Patient>(count);

			stInsert = con.prepareStatement(sqlString);
			stInsert.setString(1, patientsDataAll);
			stInsert.setString(2, patientsDataAll);
			stInsert.setString(3, patientsDataAll);

			rs2 = stInsert.executeQuery();
			while (rs2.next()) {
				Patient patient = new Patient();
				patient.setIdPatient(rs2.getInt(1));
				patient.setName(rs2.getString(2));
				patient.setPassport(rs2.getString(3));
				patient.setData(rs2.getString(4));
				patient.setAdress(rs2.getString(5));
				patient.setTelephone(rs2.getString(6));
				patient.setIdCard(rs2.getInt(9));
				patients.add(patient);
			}
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, stInsert);
		}
		return patients;
	}

	@Override
	public void setCard(Patient patient) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Patient findFromName(String patientsData) throws DAOException {
		ConnectionPool cPool = ConnectionPool.getInstance();
		String sqlString = "SELECT * FROM patient WHERE patient.passport = ? ;";
		Connection con = null;
		Patient patient = null;
		ResultSet rs = null;
		PreparedStatement stInsert = null;
		try {
			con = cPool.takeConnection();
			patient = new Patient();
			stInsert = con.prepareStatement(sqlString);
			stInsert.setString(1, patientsData);
			rs = stInsert.executeQuery();
			
			if (rs.next()) {
				patient.setIdPatient(rs.getInt(1));
				patient.setName(rs.getString(2));
				patient.setPassport(rs.getString(3));
				patient.setData(rs.getString(4));
				patient.setAdress(rs.getString(5));
				patient.setTelephone(rs.getString(6));
			}
			return patient;

		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, stInsert, rs);
		}

	}

	@Override
	public ArrayList<Patient> fintPatientFromUser(User user) throws DAOException {

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
			stInsert.setInt(1, user.getId());
			rs = stInsert.executeQuery();

			while (rs.next()) {
				patient = new Patient();
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
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, stInsert, rs);
		}
	}

	@Override
	public Medication getPatientMedication(Patient patient) throws DAOException {
		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Medication medication = null;
		try {
			con = cPool.takeConnection();
			String sql = SQLCommands.GET_MEDICATIONS;
			st = con.prepareStatement(sql);
			st.setInt(1, patient.getIdPatient());
			rs = st.executeQuery();
			medication = new Medication();
			while (rs.next()) {
				int tempIdUser = rs.getInt(2);
				String procedure = rs.getString(3);
				String mediction = rs.getString(4);
				String operation = rs.getString(5);
				if (procedure != null) {
					medication.setProcedure(tempIdUser, procedure);
				}
				if (medication != null) {
					medication.setMedication(tempIdUser, mediction);
				}
				if (operation != null) {
					medication.setOperation(tempIdUser, operation);
				}
			}
			return medication;
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, st, rs);
		}

	}

	@Override
	public void updateMedication(int idUser, Medication medication) throws DAOException {
		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement st = null;
		String sqlUpdate = null;
		try {
			con = cPool.takeConnection();
			if (medication.getProcedure() != null) {
				sqlUpdate = SQLCommands.UPDATE_PROCEDURE.replaceAll("<CHANGE>", medication.getProcedure().trim());
			} else if (medication.getMedication() != null) {
				sqlUpdate = SQLCommands.UPDATE_MEDICATIONS.replaceAll("<CHANGE>", medication.getMedication().trim());
			} else if (medication.getOperation() != null) {
				sqlUpdate = SQLCommands.UPDATE_OPERATIONS.replaceAll("<CHANGE>", medication.getOperation().trim());
			}

			st = con.prepareStatement(sqlUpdate);
			st.setInt(1, medication.getIdPatient());
			st.setInt(2, idUser);
			st.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, st);
		}

	}

	@Override
	public void deleteMedication(Medication medication) throws DAOException {
		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = cPool.takeConnection();
			int idPatient = medication.getIdPatient();
			int idUser = medication.getIdUser();
			String mdc;
			if ((mdc = medication.getMedication()) != null) {
				String sqlUpdate = SQLCommands.DEL_MEDICATIONS.replaceAll("<CHANGE>", SQLCommands.MEDICATION);
				st = con.prepareStatement(sqlUpdate);
				st.setInt(1, idPatient);
				st.setInt(2, idUser);
				st.setString(3, mdc.trim());
				st.executeUpdate();
			} else if ((mdc = medication.getProcedure()) != null) {
				String sqlUpdate = SQLCommands.DEL_MEDICATIONS.replaceAll("<CHANGE>", SQLCommands.PROCEDURE);
				st = con.prepareStatement(sqlUpdate);
				st.setInt(1, idPatient);
				st.setInt(2, idUser);
				st.setString(3, mdc.trim());
				st.executeUpdate();
			} else if ((mdc = medication.getOperation()) != null) {
				String sqlUpdate = SQLCommands.DEL_MEDICATIONS.replaceAll("<CHANGE>", SQLCommands.OPERATION);
				st = con.prepareStatement(sqlUpdate);
				st.setInt(1, idPatient);
				st.setInt(2, idUser);
				st.setString(3, mdc.trim());
				st.executeUpdate();
			}

		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, st);
		}

	}

	@Override
	public void addMedication(Medication medication) throws DAOException {
		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = cPool.takeConnection();
			String sqlUpdate = SQLCommands.ADD_MEDICATIONS;

			st = con.prepareStatement(sqlUpdate);
			st.setInt(1, medication.getIdPatient());
			st.setInt(2, medication.getIdUser());
			st.setString(3, medication.getProcedure());
			st.setString(4, medication.getMedication());
			st.setString(5, medication.getOperation());
			st.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, st);
		}

	}

	@Override
	public ArrayList<Patient> getAllPatient() throws DAOException {
		ArrayList<Patient> list = null;
		ConnectionPool cPool = ConnectionPool.getInstance();
		String sqlString = SQLCommands.FIND_ALL_PATIENT;
		Connection con = null;
		Patient patient;
		PreparedStatement stInsert = null;
		ResultSet rs = null;

		try {
			list = new ArrayList<Patient>();
			con = cPool.takeConnection();
			stInsert = con.prepareStatement(sqlString);
			rs = stInsert.executeQuery();

			while (rs.next()) {
				patient = new Patient();
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
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, stInsert, rs);
		}

	}

	@Override
	public void unFixing(String doctor, String patient) throws DAOException {
		DAOProvider daoProvider = DAOProvider.getInstance();
		int idUser = daoProvider.getUserDAO().find(doctor);
		int idPatient = 0;
		ConnectionPool cPool = ConnectionPool.getInstance();
		try {
			Connection con = cPool.takeConnection();
			String sqlStringFindid = SQLCommands.PATIENT_FIND_FROM_NAME;

			PreparedStatement st = con.prepareStatement(sqlStringFindid);
			st.setString(1, patient);
			ResultSet rs = st.executeQuery();

			rs = st.executeQuery();
			while (rs.next()) {
				idPatient = rs.getInt(1);
			}

			String sqlFixingString = SQLCommands.USER_HAS_PATIENT_UNFIX;
			st = con.prepareStatement(sqlFixingString);
			st.setInt(1, idUser);
			st.setInt(2, idPatient);
			st.executeUpdate();
			cPool.closeConnection(con, st, rs);
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		}

	}

	@Override
	public void updateMedicationFromNurse(Medication medication) throws DAOException {
		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = cPool.takeConnection();
			String sqlUpdate = SQLCommands.NURS_UPDATE_MEDICATION.replaceAll("<CHANGE>", medication.getProcedure().trim());

			st = con.prepareStatement(sqlUpdate);
			st.setInt(1, medication.getIdPatient());
			st.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, st);
		}
	}

	@Override
	public int countOfpatient() throws DAOException {
		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		int count = 0;

		try {
			con = cPool.takeConnection();
			st = con.prepareStatement(SQLCommands.COUNT_OF_PATIENT);
			rs = st.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} finally {
			cPool.closeConnection(con, st);
		}
		return count;
	}

	@Override
	public ArrayList<Patient> fintPatientFromUserWithLimit(User user, int srart, int delimeter) throws DAOException {
		ArrayList<Patient> list = null;
		ConnectionPool cPool = ConnectionPool.getInstance();
		String sqlString = SQLCommands.SELECT_PATIENT_LIMIT;
		Connection con = null;
		Patient patient;
		PreparedStatement stInsert = null;
		ResultSet rs = null;

		try {
			list = new ArrayList<Patient>();
			con = cPool.takeConnection();
			stInsert = con.prepareStatement(sqlString);
			stInsert.setInt(1, user.getId());
			stInsert.setInt(2, srart);
			stInsert.setInt(3, delimeter);
			rs = stInsert.executeQuery();

			while (rs.next()) {
				patient = new Patient();
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
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, stInsert, rs);
		}
	}

	@Override
	public int countOfpatientForUser(User user) throws DAOException {
		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement stInsert = null;
		ResultSet rs = null;
		int count = 0;
		String sqlString = SQLCommands.COUNT_OF_PATIENT_FOR_USER;
		try {
			con = cPool.takeConnection();
			stInsert = con.prepareStatement(sqlString);
			stInsert.setInt(1, user.getId());
			rs = stInsert.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, stInsert);
		}
		return count;
	}


	@Override
	public byte[] getPhoto(int id) throws DAOException {
		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		String sqlString = SQLCommands.GET_PHOTO;
		PreparedStatement stInsert = null;
		ResultSet rs = null;
		 InputStream imgData = null;
		 ByteArrayOutputStream daoStream = null;
		 byte[] buffer = new byte[4096];
		 int bytesRead = -1;
		byte[]  imageBytes = null;
		try {
			con = cPool.takeConnection();
			stInsert = con.prepareStatement(sqlString);
			stInsert.setInt(1, id);
			rs = stInsert.executeQuery();
			if (rs.next()) {
				imgData = rs.getBinaryStream(1);
				daoStream = new ByteArrayOutputStream();
				  if (imgData == null) {			
					  BufferedImage originalImage = ImageIO.read(new File("E:/Archive/jd02-WebProject/WebContent/images/Nophoto.png"));
					  ByteArrayOutputStream baos = new ByteArrayOutputStream();
				        ImageIO.write(originalImage, "png", baos);
				        baos.flush();
				        imageBytes = baos.toByteArray();
				        baos.close();
					  return imageBytes; 				      
					}
				
				while ((bytesRead = imgData.read(buffer)) != -1) {
					daoStream.write(buffer, 0, bytesRead);
				}
				imageBytes = daoStream.toByteArray();

			}		 
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		}
		finally {
			cPool.closeConnection(con, stInsert);
		}
		return imageBytes;
	}

	@Override
	public void addPhoto(int id, InputStream in) throws DAOException {
		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		String sqlString = SQLCommands.ADD_PHOTO;
		PreparedStatement stInsert = null;
		
		try {	
			con = cPool.takeConnection();
			stInsert = con.prepareStatement(sqlString);
			stInsert.setInt(2, id);
			stInsert.setBlob(1, in);
			stInsert.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		}
		finally {
			cPool.closeConnection(con, stInsert);
		}
		
	}

	@Override
	public ArrayList<String> getDiagnosis(int idPatient) throws DAOException {
		ArrayList<String> list = null;
		ConnectionPool cPool = ConnectionPool.getInstance();
		String sqlString = SQLCommands.GET_ALL_DIAGNOSIS;
		Connection con = null;
		PreparedStatement stInsert = null;
		ResultSet rs = null;
		String diagnosisName;
		try {
			list = new ArrayList<String>();
			con = cPool.takeConnection();
			stInsert = con.prepareStatement(sqlString);
			rs = stInsert.executeQuery();

			while (rs.next()) {
				diagnosisName = rs.getString(2);
				list.add(diagnosisName);
			}

			return list;
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, stInsert, rs);
		}
	}

	@Override
	public void setDiagnosis(String diagnos, int userId) throws DAOException {
		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement stInsert = null;
		ResultSet rs = null;
		String tempDiagnosis = null;
		int idDiagnosis = 0;
		String sqlString = SQLCommands.SELECT_DIAGNOSIS;
		String sqlStringAdd = SQLCommands.INSERT_DIAGNOSIS;
		
		try {
			con = cPool.takeConnection();
			stInsert = con.prepareStatement(sqlString);
			stInsert.setString(1, diagnos);
			rs = stInsert.executeQuery();
			if (rs.next() == false) {
				stInsert.close();
				stInsert = con.prepareStatement(sqlStringAdd);
				stInsert.setString(1, diagnos);
				stInsert.executeUpdate();
			} else {
				if (rs.next()) {
				idDiagnosis = rs.getInt(1);
				tempDiagnosis = rs.getString(2);
			}
			}						
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, stInsert);
		}
		
	}

	@Override
	public void addDiagnosis(String diagnos, int userId) throws DAOException {
		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		String sqlString = SQLCommands.UPDATE_PATIENT_DIAGNOSIS;
		PreparedStatement stInsert = null;
		
		try {	
			con = cPool.takeConnection();
			stInsert = con.prepareStatement(sqlString);
			stInsert.setString(1, diagnos);
			stInsert.setInt(2, userId);
			stInsert.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		}
		finally {
			cPool.closeConnection(con, stInsert);
		}		
	}

	@Override
	public String getPatientDiagnosis(int userId) throws DAOException {
		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement stInsert = null;
		ResultSet rs = null;
		String sqlString = SQLCommands.RETURN_DIAGNOSIS;
		String diagnosis = null;
		try {
			con =  cPool.takeConnection();
			stInsert = con.prepareStatement(sqlString);
			stInsert.setInt(1, userId);
			rs = stInsert.executeQuery();
			if (rs.next()) {
				diagnosis = rs.getString(1);
			}						
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, stInsert);
		}
		return diagnosis;
	}

	@Override
	public Card getPatientCard(int userId) throws DAOException {
		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement stInsert = null;
		ResultSet rs = null;
		String sqlString = SQLCommands.RETURN_CARD;
		Card card = new Card();
		try {
			con =  cPool.takeConnection();
			stInsert = con.prepareStatement(sqlString);
			stInsert.setInt(1, userId);
			rs = stInsert.executeQuery();
			while (rs.next()) {
				card.setIdPatient(rs.getInt(1)); 
				card.setDate(rs.getDate(2));
				card.setEpicris(rs.getString(4));
			}
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, stInsert);
		}
		return card;
	}

	@Override
	public int getIdUserfromPatient(int idPatient) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void rightOut(int idPatient) throws DAOException {
		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement stInsert = null;
		String sqlString = SQLCommands.RIGHT_OUT_PATIENT;
		try {
			con =  cPool.takeConnection();
			stInsert = con.prepareStatement(sqlString);
			stInsert.setInt(1, idPatient);
			stInsert.executeUpdate();
			
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, stInsert);
		}		
	}

	@Override
	public ArrayList<Patient> fintPatientFromNursWithLimit(int start, int delimeter) throws DAOException {
		ArrayList<Patient> list = null;
		ConnectionPool cPool = ConnectionPool.getInstance();
		String sqlString = SQLCommands.SELECT_PATIENT_LIMIT_FROM_NURSE;
		Connection con = null;
		Patient patient;
		PreparedStatement stInsert = null;
		ResultSet rs = null;

		try {
			list = new ArrayList<Patient>();
			con = cPool.takeConnection();
			stInsert = con.prepareStatement(sqlString);
			stInsert.setInt(1, start);
			stInsert.setInt(2, delimeter);
			rs = stInsert.executeQuery();

			while (rs.next()) {
				patient = new Patient();
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
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, stInsert, rs);
		}
	}

	@Override
	public int countOfNotFicksingMedication(int idPatient) throws DAOException {
		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement stInsert = null;
		ResultSet rs = null;
		int count = 0;
		String sqlString = SQLCommands.SELECT_NOT_FICSING_MEDICATION;
		try {
			con = cPool.takeConnection();
			stInsert = con.prepareStatement(sqlString);
			stInsert.setInt(1, idPatient);
			rs = stInsert.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, stInsert);
		}
		return count;
	}

	@Override
	public ArrayList<Integer> fintPatientFromUserWithLimitWithNotCheckMedication(int userid, int start, int delimeter)
			throws DAOException {
		ArrayList<Integer> list = null;
		ConnectionPool cPool = ConnectionPool.getInstance();
		String sqlString = SQLCommands.SELECT_PATIENT_TWO_HAVE_NOT_CHECK_MEDICATION;
		Connection con = null;
		PreparedStatement stInsert = null;
		ResultSet rs = null;

		try {
			list = new ArrayList<Integer>();
			con = cPool.takeConnection();
			stInsert = con.prepareStatement(sqlString);
			stInsert.setInt(1, userid);
			stInsert.setInt(2, start);
			stInsert.setInt(3, delimeter);
			rs = stInsert.executeQuery();

			while (rs.next()) {
				list.add(rs.getInt(1));
			}

			return list;
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, stInsert, rs);
		}
	}

	@Override
	public ArrayList<Integer> fintPatientFromUserWithLimitWithNotCheckMedicationForNurse(int start, int delimeter)
			throws DAOException {
		ArrayList<Integer> list = null;
		ConnectionPool cPool = ConnectionPool.getInstance();
		String sqlString = SQLCommands.SELECT_PATIENT_TWO_HAVE_NOT_CHECK_MEDICATION_FOR_NURSE;
		Connection con = null;
		PreparedStatement stInsert = null;
		ResultSet rs = null;

		try {
			list = new ArrayList<Integer>();
			con = cPool.takeConnection();
			stInsert = con.prepareStatement(sqlString);
			stInsert.setInt(1, start);
			stInsert.setInt(2, delimeter);
			rs = stInsert.executeQuery();

			while (rs.next()) {
				list.add(rs.getInt(1));
			}

			return list;
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, stInsert, rs);
		}
	}

	@Override
	public void addMedicationPeriocity(Medication medication) throws DAOException {
		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = cPool.takeConnection();
			String sqlUpdate = SQLCommands.SET_MEDICATION_WITH_PERIOCITY;

			st = con.prepareStatement(sqlUpdate);
			st.setInt(1, medication.getIdPatient());
			st.setInt(2, medication.getIdUser());
			st.setString(3, medication.getMedication());
			st.setInt(4, medication.getPeriodicity());
			st.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, st);
		}
		
	}

	@Override
	public HashMap<Integer, List<Object>> getMedicationWithPeriosityAndTimestamp(int idUser) throws DAOException {
		HashMap<Integer, List<Object>> map = null;
		ArrayList<Object> list = null;
		ConnectionPool cPool = ConnectionPool.getInstance();
		String sqlString = SQLCommands.GET_MEDICATION_WITH_PERIOCITY_AND_TIMESTAMP;
		Connection con = null;
		PreparedStatement stInsert = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = cPool.takeConnection();
			stInsert = con.prepareStatement(sqlString);
			stInsert.setInt(1, idUser);	
			rs = stInsert.executeQuery();
			
			map = new HashMap<Integer, List<Object>>();
			
			while (rs.next()) {	
				list = new ArrayList<Object>();
				list.add(rs.getString(2));
				list.add(rs.getTimestamp(3));
				list.add(rs.getInt(4));
				list.add(rs.getInt(1));
				map.put(count, list);
				count++;
			}			
			return map;
			
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, stInsert, rs);
		}
	}

	@Override
	public void updateMedicationTimeLost(Date data, String medication, int idPatient) throws DAOException {
		
		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement stInsert = null;
		String sqlString = SQLCommands.UPDATE_MEDICATION_WHERE_TIME_LOST;
		try {		

			con =  cPool.takeConnection();
			stInsert = con.prepareStatement(sqlString);
			stInsert.setInt(1, idPatient);
			stInsert.setString(2, medication);
			stInsert.executeUpdate();
			
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, stInsert);
		}		
		
	}

	@Override
	public void updateMedicationPeriod(String medication, int idPatient, int idUser) throws DAOException {
		ConnectionPool cPool = ConnectionPool.getInstance();
		Connection con = null;
		PreparedStatement stInsert = null;
		medication = medication.replaceFirst(" ", "");
		String sqlString = SQLCommands.UPDATE_MEDICATION_REDIOD;

		try {				
			con =  cPool.takeConnection();
			stInsert = con.prepareStatement(sqlString);
			stInsert.setInt(1, idPatient);
			stInsert.setInt(2, idUser);
			stInsert.setString(3, medication);
			stInsert.executeUpdate();	
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, stInsert);
		}		
		
	}

	@Override
	public ArrayList<Patient> getAllPatientDischarged() throws DAOException {
		ArrayList<Patient> list = null;
		ConnectionPool cPool = ConnectionPool.getInstance();
		String sqlString = SQLCommands.FIND_ALL_PATIENT_DISCHANGED;
		Connection con = null;
		Patient patient;
		PreparedStatement stInsert = null;
		ResultSet rs = null;

		try {
			list = new ArrayList<Patient>();
			con = cPool.takeConnection();
			stInsert = con.prepareStatement(sqlString);
			rs = stInsert.executeQuery();

			while (rs.next()) {
				patient = new Patient();
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
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, stInsert, rs);
		}
	}

	@Override
	public ArrayList<Patient> getAllPatientLimit(int start, int delimeter) throws DAOException {
		ArrayList<Patient> list = null;
		ConnectionPool cPool = ConnectionPool.getInstance();
		String sqlString = SQLCommands.FIND_ALL_PATIENT_LIMIT;
		Connection con = null;
		Patient patient;
		PreparedStatement stInsert = null;
		ResultSet rs = null;

		try {
			list = new ArrayList<Patient>();
			con = cPool.takeConnection();
			stInsert = con.prepareStatement(sqlString);
			stInsert.setInt(1, start);
			stInsert.setInt(2, delimeter);
			rs = stInsert.executeQuery();

			while (rs.next()) {
				patient = new Patient();
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
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, stInsert, rs);
		}
	}

	@Override
	public Set<Patient> getAllPatientDischargedSet(int start, int delimeter) throws DAOException {
		Set<Patient> set = null;
		ConnectionPool cPool = ConnectionPool.getInstance();
		String sqlString = SQLCommands.FIND_ALL_PATIENT_DISCHANGED_LIMIT;
		Connection con = null;
		Patient patient;
		PreparedStatement stInsert = null;
		ResultSet rs = null;

		try {
			set = new HashSet<Patient>();
			con = cPool.takeConnection();
			stInsert = con.prepareStatement(sqlString);
			stInsert.setInt(1, start);
			stInsert.setInt(2, delimeter);
			rs = stInsert.executeQuery();

			while (rs.next()) {
				patient = new Patient();
				patient.setIdPatient(rs.getInt(1));
				patient.setName(rs.getString(2));
				patient.setPassport(rs.getString(3));
				patient.setData(rs.getString(4));
				patient.setAdress(rs.getString(5));
				patient.setTelephone(rs.getString(6));
				set.add(patient);
			}

			return set;
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			logger.error(e.toString());
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			cPool.closeConnection(con, stInsert, rs);
		}
	}	
}

	


