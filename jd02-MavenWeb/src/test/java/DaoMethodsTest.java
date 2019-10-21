import org.junit.Test;
import by.htp.dao.DAOProvider;
import by.htp.dao.UserDAO;
import by.htp.entity.Doctor;
import by.htp.entity.User;
import by.htp.pool.ConnectionPool;
import by.htp.pool.ConnectionPoolException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import org.junit.Assert;

public class DaoMethodsTest {
	
	
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
		UserDAO userDAO =  DAOProvider.getInstance().getUserDAO();
	try {
		User user = userDAO.autorization("test_login", "test_password");		
		String name = user.getName();
		Assert.assertEquals(name, "test Test TEST");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
}
//	@Test
	public void testDaoRegistration() {
		UserDAO userDAO =  DAOProvider.getInstance().getUserDAO();
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
		UserDAO userDAO =  DAOProvider.getInstance().getUserDAO();
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
	public void findListOfUser() {
		ArrayList<Doctor> list;
		UserDAO userDAO =  DAOProvider.getInstance().getUserDAO();
		try {
			list = userDAO.findListOfLeaveUser();

			Assert.assertEquals(list.get(0).getName(), "test++ Test+++ TEST+++");
			Assert.assertEquals(list.get(1).getName(), "test+++ Test+++ TEST++++");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	
	
	/* @Test
	public void testDaoNoBase() {
		
		try {
			FileInputStream in = new FileInputStream("src/test/java/db.properties");
			Properties props = new Properties();
			props.load(in);
		
			props.setProperty("db.url", "THIS LINE GENERATED JUNIT TEST");
			in.close();
			
			FileOutputStream out = new FileOutputStream("src/test/java/db.properties");
			props.store(out, null);
			out.close();
			
			User user = userDAO.autorization("test_login", "test_password");		
			String name = user.getName();
			UserDAO userDAONoBase =  DAOProvider.getInstance().getUserDAO();
			User userNobase = userDAONoBase.autorization("test_login", "test_password");	
			
			Assert.assertEquals(name, userNobase.getName());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	catch(ServiceException ex) {
			ex.printStackTrace();
		} catch(DAOException ex) {
			ex.printStackTrace();
		} finally {
			FileInputStream in2;
			try {
				in2 = new FileInputStream("src/test/java/db.properties");
			
			Properties props2 = new Properties();
			props2.load(in2);
		
			props2.setProperty("db.url", "jdbc:mysql://localhost:3306/test_jd2?useUnicode=true&serverTimezone=UTC");
			in2.close();
			
			FileOutputStream out2 = new FileOutputStream("src/test/java/db.properties");
			props2.store(out2, null);
			out2.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} */


}
