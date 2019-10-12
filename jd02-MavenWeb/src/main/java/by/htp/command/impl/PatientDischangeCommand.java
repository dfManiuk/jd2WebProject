package by.htp.command.impl;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.htp.command.ICommand;
import by.htp.controller.JspPageName;
import by.htp.controller.RequestParameterName;
import by.htp.entity.Medication;
import by.htp.entity.Patient;
import by.htp.entity.User;
import by.htp.service.PatientService;
import by.htp.service.ServiceException;
import by.htp.service.ServiceProvider;

public class PatientDischangeCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = null;
		Patient patient = null;
		String page = null;
		Medication medication = null;
		byte[] imageBytes = null;
		
		
		HttpSession session = request.getSession();
		user = (User) session.getAttribute("UserSession");
		
		String passport = request.getParameter(RequestParameterName.NAME);
		
		PatientService patientService = ServiceProvider.getInstance().getPatientService();
		
		try {
			patient = patientService.findFromName(passport);
			medication = patientService.getPatientMedications(patient);
			imageBytes = patientService.getPhoto(patient.getIdPatient());
			List<String> diagnosisList = patientService.getDiagnosis(user.getId());
			
			if (patient != null && patient.getIdPatient() !=0) { 
								
				request.setAttribute("Patient", patient);
				request.setAttribute("Medications", medication);
				request.setAttribute("List", diagnosisList);
				
				if (imageBytes != null) {
					String base64Image = Base64.getEncoder().encodeToString(imageBytes);
					request.setAttribute("base64Image", base64Image);
				}
				
					page = "jsp/dischangePage.jsp";
											
				RequestDispatcher dispatcher = request.getRequestDispatcher(page);
				dispatcher.forward(request, response);
				
				return page;
				
			} else {

			page = JspPageName.USER_PAGE;
				
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}

}
