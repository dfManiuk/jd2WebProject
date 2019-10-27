import org.junit.Test;
import by.htp.dao.DAOProvider;
import by.htp.dao.PatientDAO;
import by.htp.dao.UserDAO;
import by.htp.entity.Doctor;
import by.htp.entity.Patient;
import by.htp.entity.User;
import by.htp.pool.ConnectionPool;
import by.htp.pool.ConnectionPoolException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.Assert;

public class DaoMethodsTest {
	
	UserDAO userDAO =  DAOProvider.getInstance().getUserDAO();
	PatientDAO patientDAO = DAOProvider.getInstance().getPatientDAO();
	
//	@BeforeClass
	 public static void loadDriver() throws ClassNotFoundException, ConnectionPoolException {
			
		String string = "jdbc:mysql://localhost:3306/test_jd2?useUnicode=true";

		try {
			Field p = ConnectionPool.class.getDeclaredField("url");
	
			p.setAccessible(true);		
			p.set(ConnectionPool.getInstance(), string);		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}			
	}
	
	@Test
	public void testDaoLogination() {
		
	try {
		User user = userDAO.autorization("test_login", "test_password");		
		String name = user.getName();
		Assert.assertEquals(name, "test Test TEST");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
}
	//@Test
	public void testDaoRegistration() {

	try {
		User newUser = new User();
		newUser.setName("test+ Test++ TEST++");
		newUser.setLogin("test_login+");
		newUser.setPassword("test_password+");
		newUser.setPosition("test_position");
		newUser.setSpecialization("test");
		User user = userDAO.registration(newUser);

		Assert.assertEquals(user, newUser);
		
	} catch (Exception e) {	
		e.printStackTrace();
	} 
}
	
	@Test
	public void testDaoNoBaseLogination() {

		try {
			User user = userDAO.autorization("NO_login", "NO_password");		
			String name = user.getName();
			Assert.assertEquals(name, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	@Test
	public void testFindListOfUser() {
		ArrayList<Doctor> list;
		try {
			list = userDAO.findListOfLeaveUser();
			Assert.assertEquals(list.get(0).getName(), "test+++ Test+++ TEST++++");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	@Test
	public void testUserHasPatient() {
		ArrayList<Patient> list;
		int id = 1;
		Doctor doctor = new Doctor();
		doctor.setId(id);
		try {
			list = (ArrayList<Patient>) userDAO.findPatientsFromUser(doctor);
			Assert.assertEquals(list.get(0).getName(), "patient_test_1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	@Test
	public void testFindUserFromName() {
		int testId = 1;
		String name = "test Test TEST";
		try {
			int id = userDAO.find(name);
			Assert.assertEquals(id, testId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	@Test
	public void testFindUserRole() {
		String testRole = "f";
		String name = "test Test TEST";
		try {
			String role = userDAO.userRole(name);
			Assert.assertEquals(testRole, role);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	@Test
	public void testListOfLeaveUsers() {
		ArrayList<Doctor> list;
		try {
			list =  userDAO.findListOfLeaveUser();
			Assert.assertNotNull(list);
			Assert.assertTrue(list.size() > 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNotFindUserFromName() {
		int testId = 0;
		String name = "_not_name_";
		try {
			int id = userDAO.find(name);
			Assert.assertEquals(id, testId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	@Test
	public void testfindsFromName() {
		Patient patient;
		String testPassport = "test_passport_1";
		try {
			patient =  patientDAO.findFromName(testPassport);
			
			Assert.assertEquals(patient.getPassport(), testPassport);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddPatient() {
		String passport = "test_passport_auto_test";
		passport = "test_passport_auto_test" + passport.hashCode()/Math.random() + "" ;
		String name = "test_name_auto_test" ;
		String birth =  "test_birth_auto_test"; 
		String adress = "test_adress_auto_test" ;
		String telephone = "test_telephone_auto_test";;

		try {
			 patientDAO.registration(name, passport, birth, adress, telephone);
			Patient patient =  patientDAO.findFromName(passport);
			Assert.assertEquals(patient.getPassport(), passport);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
