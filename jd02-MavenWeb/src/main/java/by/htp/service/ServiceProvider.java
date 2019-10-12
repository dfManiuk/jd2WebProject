package by.htp.service;

public class ServiceProvider {

	private ServiceProvider(){}
	
	private static final ServiceProvider instance = new ServiceProvider();
	
	private UserService userService = new UserServiceImpl();
	
	private PatientService patientService = new PatientServiseImpl();
	
	
	public static ServiceProvider getInstance() {
		return instance;
	}
	
	public UserService getUserService() {
		return userService;
	}
	public PatientService getPatientService() {
		return patientService;
	}
}
