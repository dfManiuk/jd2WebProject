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
import by.htp.entity.Card;
import by.htp.entity.Medication;
import by.htp.entity.Patient;
import by.htp.entity.User;
import by.htp.service.PatientService;
import by.htp.service.ServiceException;
import by.htp.service.ServiceProvider;
import by.htp.service.UserService;

public class PatientDiagnosisCommand implements  ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = null;
		Patient patient = null;
		byte[] imageBytes = null;
		Medication medication = null;
		String userRole = null;
		Card card = null;
		
		HttpSession session = request.getSession();
		user = (User) session.getAttribute("UserSession");
		
		String diagnos = request.getParameter(RequestParameterName.DIANGOS);
		String passport = request.getParameter(RequestParameterName.PASSPORT);
		
		PatientService patientService = ServiceProvider.getInstance().getPatientService();
		UserService userService = ServiceProvider.getInstance().getUserService();
		
		try {
	
			userRole = userService.userRole(user.getName());
			patient = patientService.findFromName(passport);
			imageBytes = patientService.getPhoto(patient.getIdPatient());
			patientService.setDiagnosis(diagnos, patient.getIdPatient());
			patientService.addDiagnosis(diagnos, patient.getIdPatient());
			medication = patientService.getPatientMedications(patient);
			List<String> diagnosisList = patientService.getDiagnosis(patient.getIdPatient());
			card = patientService.getPatientCard(patient.getIdPatient());
			
			
			if (diagnos != null && userRole.equals("врач") || userRole.equals("заведующий")) { 						
					
				if (imageBytes != null) {
					String base64Image = Base64.getEncoder().encodeToString(imageBytes);
					request.setAttribute("base64Image", base64Image);
			}
				boolean flag = true;
				card.setEpicris(diagnos);
				request.setAttribute("Medications", medication);
				request.setAttribute("Patient", patient);	
				request.setAttribute("List", diagnosisList);
				request.setAttribute("Flag", flag);
				request.setAttribute("Diagnos", diagnos);
				request.setAttribute("Card", card);
				RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.DISCHANGE_PAGE);
				dispatcher.forward(request, response);
			
				
			} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.DISCHANGE_PAGE);
			dispatcher.forward(request, response);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "jsp/dischangePage.jsp";
	}

}
