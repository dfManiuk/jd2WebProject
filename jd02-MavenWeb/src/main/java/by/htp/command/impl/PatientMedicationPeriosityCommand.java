package by.htp.command.impl;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.command.ICommand;
import by.htp.controller.JspPageName;
import by.htp.entity.Medication;
import by.htp.entity.Patient;
import by.htp.entity.User;
import by.htp.service.PatientService;
import by.htp.service.ServiceException;
import by.htp.service.ServiceProvider;


public class PatientMedicationPeriosityCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Patient patient = null;
	String page = null;
	Medication medication = null;
	User user;
	byte[] imageBytes = null;
	int id = 0;

	PatientService patientService = ServiceProvider.getInstance().getPatientService();

	if (request.getSession() == null) {
		response.sendRedirect(JspPageName.MAIN_PAGE);
	}
	HttpSession session = request.getSession();

	user = (User) session.getAttribute("UserSession");
	
	String passport =request.getParameter("patient");
	medication = new Medication();
	patient = new Patient();
	id = user.getId();
	String userMedi = request.getParameter("medication");
	
	
	try {
		patient = patientService.findFromName(passport);
		patientService.updateMedicationPeriod(userMedi, patient.getIdPatient(), id);
		
		medication = patientService.getPatientMedications(patient);
		imageBytes = patientService.getPhoto(patient.getIdPatient());
		
		if ( medication != null ) {
							
			request.setAttribute("Patient", patient);
			request.setAttribute("Medications", medication);	
			request.setAttribute("PeriosityChange", userMedi);	
				
			page = JspPageName.PATIENT_PAGE;
			
			if (imageBytes != null) {
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				request.setAttribute("base64Image", base64Image);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
			return page;
			
		} else {

		page = JspPageName.USER_PAGE;
			
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
		}
	} catch (ServiceException e) {
		// TODO
		e.printStackTrace();
	}
	return page;
}
}
