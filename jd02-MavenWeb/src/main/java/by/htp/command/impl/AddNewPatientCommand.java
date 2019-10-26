package by.htp.command.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.htp.command.ICommand;
import by.htp.command.SessionCommands;
import by.htp.controller.JspPageName;
import by.htp.entity.Patient;
import by.htp.entity.User;
import by.htp.service.PatientService;
import by.htp.service.ServiceException;
import by.htp.service.ServiceProvider;
import by.htp.service.UserDataValidator;

public class AddNewPatientCommand implements ICommand {

	private final String error = "Add new Patient command ERROR";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if (request.getParameter("local") != null) {
			session.setAttribute("local", request.getParameter("local"));	
			request.getRequestDispatcher(JspPageName.ADD_PATIENT_PAGE).forward(request, response);
		}
		
		Patient patient = new Patient();	
		patient.setName(request.getParameter("bigName"));
		patient.setPassport(request.getParameter("passportNumber"));
		patient.setData(request.getParameter("dataOfBirth"));
		patient.setAdress(request.getParameter("plase"));
		patient.setTelephone(request.getParameter("telephone"));
		
		PatientService patientService = ServiceProvider.getInstance().getPatientService();
		
		try {
					
		if (UserDataValidator.getInstance().check(patient) != false &&  session != null) {
			
			patient = patientService.registration(patient);
			
			patientService.addPhoto(patient.getIdPatient(), new ByteArrayInputStream(AddPhotoCommand.fotobyte));//TODO Exception
						
			session = request.getSession();
			User user = (User) session.getAttribute(SessionCommands.USER_SESSION);
			request.setAttribute(SessionCommands.USER, user);
					
			request.getRequestDispatcher(JspPageName.USER_PAGE).forward(request, response);;

		}else {
			
			String page = JspPageName.ADD_PATIENT_PAGE;
			response.sendRedirect(page);
		
		}
		}catch (ServiceException e) {
			session.setAttribute("error", error);
		e.printStackTrace();
		}catch (IOException e) {
			session.setAttribute("error", error);
			e.printStackTrace();
			}
	return null;
}
	}
