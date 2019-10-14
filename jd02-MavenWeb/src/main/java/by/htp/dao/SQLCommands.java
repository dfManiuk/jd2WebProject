package by.htp.dao;

public class SQLCommands {

	private SQLCommands() {
	}

	public static final String MEDICATION = "medication";
	public static final String PROCEDURE = "procedure";
	public static final String OPERATION = "operation";
	
	public static final String INSERT_DIAGNOSIS ="INSERT INTO diagnosis (name)  VALUES ( ? );";
	public static final String SELECT_DIAGNOSIS ="SELECT * FROM hospital.diagnosis where name = ? ;";
	public static final String GET_ALL_DIAGNOSIS ="SELECT * FROM hospital.diagnosis;";
	public static final String SELECT_LAST_INSERT_ID_CARD="SELECT MAX(idpatient) AS idpatient FROM card;";
	public static final String SELECT_LAST_INSERT_ID ="SELECT MAX(idpatient) AS idpatient FROM patient;";
	public static final String SET_CARD = "INSERT INTO card (idpatient, `creadeData`) VALUES ((SELECT patient.idpatient FROM patient WHERE patient.passport = ?), current_timestamp());";
	public static final String PATIENT_REGISTRATION = "INSERT INTO patient (firast_last_name, passport, birth, adress, telephone) VALUES (?,?,?,?,?);";
	public static final String PATIENT_FIND = "SELECT * FROM patient where passport LIKE ? or patient.firast_last_name LIKE ? or patient.passport LIKE ? or patient.birth LIKE  ?  or patient.adress LIKE  ?  or patient.telephone LIKE ? and patient.out is true;";
	public static final String PATIENT_FIND_CARDS = "SELECT * FROM hospital.card WHERE idpatient = ? ;";
	public static final String PATIENT_FIND_FROM_NAME = "SELECT idpatient FROM hospital.patient WHERE firast_last_name LIKE  ? AND patient.out is true;";
	public static final String USER_HAS_PATIENT = "INSERT INTO user_has_patient(user_iduser, patient_idpatient ) VALUES (?, ?);";
	public static final String USER_HAS_PATIENT_UNFIX = "delete FROM user_has_patient WHERE user_iduser = ? AND patient_idpatient = ? ;";
	public static final String USER_LOGINATION = "SELECT user.iduser, user.userName, user.userPosition, specialization.specialization, user.login, user.password FROM user LEFT JOIN specialization ON user.idspecialization = specialization.idspecialization WHERE user.password  LIKE ? AND user.login LIKE ? AND user.out is true;";
	public static final String USER_REGISTRATION = "SELECT idspecialization FROM specialization WHERE specialization LIKE ?";
	public static final String USER_REGISTRATION_INSERT = "INSERT INTO user (userName, userPosition, idspecialization, login, password) "
			+ "VALUES (?,?,?,?,?)";
	public static final String FIND_USER_FROM_NAME = "SELECT iduser FROM hospital.user WHERE userName LIKE ? AND user.out is true";
	public static final String SET_MEDICATION = "UPDATE hospital.medication SET medication.procedure = ? , medication.medication = ?, medication.operation = ? WHERE medication.idpatient = ? ;";

	public static final String COUNTS_OF_PATIENT = "SELECT COUNT(*) FROM hospital.patient WHERE patient.firast_last_name LIKE  ?  or patient.passport LIKE  ? or patient.birth LIKE  ?  or patient.adress LIKE  ?  or patient.telephone = ? AND patient.out is true;";
	public static final String COUNTS_OF_PATIENT_MEDICATIONS = "SELECT COUNT(*) FROM hospital.patient, hospital.medication WHERE hospital.patient.idpatient = hospital.medication.idpatient AND (SELECT medication.medication LIKE  ? or medication.operation LIKE  ? or medication.procedure LIKE ?);";
	public static final String PATIENT_FIND_FROM_MEDICATION = "SELECT * FROM hospital.patient, hospital.medication, hospital.card "
			+ "WHERE hospital.patient.idpatient = hospital.medication.idpatient AND hospital.patient.idpatient = hospital.card.idpatient AND "
			+ "(SELECT medication.medication LIKE  ? or medication.operation LIKE  ? or medication.procedure LIKE ?);";
	public static final String USER_EDIT_PROFILE = "UPDATE user SET userPosition = ? , login = ? ,password = ?, user.idspecialization = (select specialization.idspecialization from specialization where specialization = ? ) WHERE iduser = ? ;";
	public static final String SPECIALIZATION_CHECK = "SELECT * FROM hospital.specialization WHERE specialization = ? ;";
	public static final String FIND_USER_HAVE_PATIENT = "SELECT * FROM patient LEFT JOIN user_has_patient ON patient.idpatient = user_has_patient.patient_idpatient WHERE  user_has_patient.user_iduser = ? AND patient.out is true ;";
	public static final String FIND_USER_ROLE = "SELECT userPosition FROM hospital.user WHERE userName = ? AND user.out is true;";
	public static final String GET_MEDICATIONS = "SELECT * FROM hospital.medication WHERE idPatient = ? ;";
	public static final String UPDATE_PROCEDURE = "UPDATE medication SET medication.procedure = '<CHANGE> (выполненно)' WHERE medication.idPatient = ? AND medication.idUser = ? AND medication.procedure = '<CHANGE>' ;";
	public static final String UPDATE_MEDICATIONS = "UPDATE medication SET medication.medication = '<CHANGE> (выполненно)' WHERE medication.idPatient = ? AND medication.idUser = ? AND medication.medication = '<CHANGE>' ;";
	public static final String UPDATE_OPERATIONS = "UPDATE medication SET medication.operation = '<CHANGE> (выполненно)' WHERE medication.idPatient = ? AND medication.idUser = ? AND medication.operation = '<CHANGE>' ;";
	public static final String DEL_MEDICATIONS = "DELETE FROM medication WHERE medication.idPatient = ? AND medication.idUser = ? AND medication.<CHANGE> = ? ;";
	public static final String ADD_MEDICATIONS = "INSERT INTO medication VALUES (? , ? , ? , ? , ?, current_timestamp(), 0);";
	public static final String FIND_USER = "SELECT * FROM user JOIN specialization ON user.idspecialization = specialization.idspecialization WHERE user.out is true;";
	public static final String FIND_ALL_PATIENT = "SELECT * FROM hospital.patient WHERE patient.out is true ;";
	public static final String NURS_UPDATE_MEDICATION = "UPDATE medication SET medication.procedure = '<CHANGE>(выполненно)' WHERE medication.idPatient = ? AND medication.procedure = '<CHANGE>' ;";
	public static final String COUNT_OF_PATIENT = "SELECT count(*) FROM hospital.patient WHERE patient.out is true;";
	public static final String SELECT_PATIENT_LIMIT = "SELECT * FROM patient LEFT JOIN user_has_patient ON patient.idpatient = user_has_patient.patient_idpatient "
			+ "WHERE patient.out is true and user_has_patient.user_iduser = ? LIMIT ? , ? ";
	public static final String COUNT_OF_PATIENT_FOR_USER = "SELECT count(*) FROM hospital.patient LEFT JOIN user_has_patient  "
			+ "ON patient.idpatient = user_has_patient.patient_idpatient where user_has_patient.user_iduser = ? AND patient.out is true ;";
	public static final String ADD_PHOTO = "UPDATE card SET cardcol = ? WHERE idpatient = ? ;";
	public static final String GET_PHOTO = "SELECT cardcol FROM card where idpatient = ? ; ";
	public static final String UPDATE_PATIENT_DIAGNOSIS = "UPDATE card SET idepicrisis = (SELECT diagnosis.iddiagnosis FROM hospital.diagnosis WHERE diagnosis.name = ?) WHERE card.idpatient = ? ;";
	public static final String RETURN_DIAGNOSIS = "SELECT hospital.diagnosis.name FROM hospital.diagnosis WHERE iddiagnosis = (SELECT idepicrisis FROM patient LEFT JOIN card ON patient.idpatient = card.idpatient WHERE patient.idpatient = ?);";
	public static final String RETURN_CARD = "SELECT * FROM hospital.card WHERE  idpatient = ? ;";
	public static final String FIND_USER_ID_FOR_PATIENT = "SELECT user_iduser FROM hospital.user_has_patient WHERE patient_idpatient = ? ;";
	public static final String RIGHT_OUT_PATIENT = "UPDATE patient SET patient.out = false WHERE patient.idpatient = ? ;";
	public static final String SELECT_PATIENT_LIMIT_FROM_NURSE = "SELECT * FROM patient WHERE patient.out is true LIMIT ? , ? ;"; 
	public static final String SELECT_NOT_FICSING_MEDICATION = "SELECT count(*) FROM hospital.medication WHERE idPatient = ? AND medication.procedure NOT LIKE '%выполненно%' OR medication.medication NOT LIKE '%выполненно%' ; ";
	public static final String SELECT_PATIENT_TWO_HAVE_NOT_CHECK_MEDICATION = "SELECT idpatient FROM patient LEFT JOIN user_has_patient ON patient.idpatient = user_has_patient.patient_idpatient "+
		    "WHERE  patient.out is true AND (SELECT count(*) FROM hospital.medication WHERE medication.idPatient = patient.idPatient  AND medication.procedure NOT LIKE '%выполненно%' OR  medication.idPatient = patient.idPatient AND medication.medication NOT LIKE '%выполненно%')  > 0  "+
		    "and user_has_patient.user_iduser = ? LIMIT ? , ? ;";
	public static final String SELECT_PATIENT_TWO_HAVE_NOT_CHECK_MEDICATION_FOR_NURSE = " SELECT n.idpatient FROM patient n LEFT JOIN medication s ON s.idPatient = n.idPatient  WHERE  n.out is true AND  s.procedure NOT LIKE '%выполненно%'  OR s.medication NOT LIKE '%выполненно%'  LIMIT ? , ? ;";
	public static final String SET_MEDICATION_WITH_PERIOCITY = " INSERT INTO hospital.medication (idPatient, idUser, medication, medication_time , medication_per) VALUES (? , ? , ? ,current_timestamp(), ? ); ";
	public static final String GET_MEDICATION_WITH_PERIOCITY_AND_TIMESTAMP = "SELECT idPatient, medication, medication_time, medication_per  FROM hospital.medication WHERE medication_per > 1 AND idUser = ? AND medication LIKE '%выполненно%';";
	public static final String CURRENT_TIMESTAMP = "SELECT current_timestamp();";
	public static final String UPDATE_MEDICATION_WHERE_TIME_LOST = "UPDATE hospital.medication SET medication = REPLACE(medication, '(выполненно)', '') WHERE idPatient = ? AND medication = ? ;";
	public static final String UPDATE_MEDICATION_REDIOD = "UPDATE hospital.medication SET medication_per = 1  WHERE idPatient = ? AND idUser = ? AND medication  = ? ;";
	public static final String FIND_ALL_USERS_TO_LEAVE = "SELECT * FROM user JOIN specialization ON user.idspecialization = specialization.idspecialization WHERE user.out is false;";
	public static final String FIND_ALL_PATIENT_DISCHANGED = "SELECT * FROM hospital.patient WHERE patient.out is false ;";
	public static final String FIND_ALL_PATIENT_LIMIT = "SELECT * FROM hospital.patient WHERE patient.out is true LIMIT ? , ? ;";
	public static final String FIND_ALL_PATIENT_DISCHANGED_LIMIT = "SELECT * FROM hospital.patient WHERE patient.out is false LIMIT ? , ? ;";
}

