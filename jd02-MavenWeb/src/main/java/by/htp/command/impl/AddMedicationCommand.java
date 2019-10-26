package by.htp.command.impl;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.htp.command.ICommand;
import by.htp.command.SessionCommands;
import by.htp.controller.JspPageName;
import by.htp.entity.Medication;
import by.htp.entity.Patient;
import by.htp.entity.User;
import by.htp.service.PatientService;
import by.htp.service.ServiceException;
import by.htp.service.ServiceProvider;

public class AddMedicationCommand implements ICommand {

	private final String error = "Add medication ERROR";
	private final String options = "options";
	private final String med = "med";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Patient patient;
		Medication medication;
		User user;
		byte[] imageBytes;
	
		PatientService patientService = ServiceProvider.getInstance().getPatientService();
		
		HttpSession session = request.getSession();
	
		user = (User) session.getAttribute(SessionCommands.USER_SESSION);
		patient = (Patient) session.getAttribute(SessionCommands.PATIENT);
				
		String userOptions = request.getParameter(options);
		String userMedi = request.getParameter(med);
			
		medication = new Medication();
		if (userOptions.equals(SessionCommands.PROCEDURE_MEDICATION) ) {
			medication.setProcedure(userMedi);
		} else if (userOptions.equals(SessionCommands.MEDICATION_MEDICATION) ) {
			medication.setMedication(userMedi);
		} else if (userOptions.equals(SessionCommands.OPERATION_MEDICATION)) {
			medication.setOperation(userMedi);
		}
		medication.setIdUser(user.getId());
		medication.setIdPatient(patient.getIdPatient());
		
		try {
			patientService.addMedication(medication);
			medication = patientService.getPatientMedications(patient);
			imageBytes = patientService.getPhoto(patient.getIdPatient());
			
			if (patient.getIdPatient() !=0 ) {
								
				request.setAttribute(SessionCommands.PATIENT, patient);
				request.setAttribute(SessionCommands.MEDICATIONS, medication);
				
				if (imageBytes != null) {
					String base64Image = Base64.getEncoder().encodeToString(imageBytes);
					request.setAttribute(SessionCommands.BASE64IMAGE, base64Image);
				}
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.PATIENT_PAGE);
				dispatcher.forward(request, response);
				
				return JspPageName.PATIENT_PAGE;
				
			} else {				
			response.sendRedirect(JspPageName.USER_PAGE);
			}
		} catch (ServiceException e) {
			session.setAttribute("error", error);
			e.printStackTrace();
		}
		return JspPageName.USER_PAGE;
	}
	

}
