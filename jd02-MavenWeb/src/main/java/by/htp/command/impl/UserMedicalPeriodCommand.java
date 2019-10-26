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

public class UserMedicalPeriodCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Patient patient = null;
		String page = null;
		Medication medication = null;
		User user;
		byte[] imageBytes = null;
		int userMedicationPeriodInt = 0;
	
		PatientService patientService = ServiceProvider.getInstance().getPatientService();
		
		HttpSession session = request.getSession();
	
		user = (User) session.getAttribute("UserSession");
		patient = (Patient) session.getAttribute("Patient");
				
		String userMedication = request.getParameter("med");
		String userMedicationPeriod = request.getParameter("per");
			
		try {
			userMedicationPeriodInt = Integer.parseInt(userMedicationPeriod);
		} catch (NumberFormatException e0) {
			try {
				
				medication = patientService.getPatientMedications(patient);
				imageBytes = patientService.getPhoto(patient.getIdPatient());
				
				if (patient != null && patient.getIdPatient() !=0 ) {
					
					request.setAttribute("Patient", patient);
					request.setAttribute("Medications", medication);
					request.setAttribute("Medications", medication);
					if (imageBytes != null) {
						String base64Image = Base64.getEncoder().encodeToString(imageBytes);
						request.setAttribute("base64Image", base64Image);
					}
		
					RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.PATIENT_PAGE);
					dispatcher.forward(request, response);

			}
			} catch (ServiceException e1) {
				// TODO Auto-generated catch block
				e0.printStackTrace();
				e1.printStackTrace();
			}
		}
		
		medication = new Medication();
		medication.setPeriodicity(userMedicationPeriodInt);
		medication.setMedication(userMedication);
		
		medication.setIdUser(user.getId());
		medication.setIdPatient(patient.getIdPatient());
		
		try {
			patientService.addMedicationPeriosity(medication);
			medication = patientService.getPatientMedications(patient);
			imageBytes = patientService.getPhoto(patient.getIdPatient());
			
			if (patient != null && patient.getIdPatient() !=0 ) {
								
				request.setAttribute("Patient", patient);
				request.setAttribute("Medications", medication);
				if (imageBytes != null) {
					String base64Image = Base64.getEncoder().encodeToString(imageBytes);
					request.setAttribute("base64Image", base64Image);
				}
				
				page = JspPageName.PATIENT_PAGE;
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(page);
				dispatcher.forward(request, response);
				
				return page;
				
			} else {

			page = JspPageName.USER_PAGE;
			response.sendRedirect(page);
			}
		} catch (ServiceException e) {
			// TODO 
			e.printStackTrace();
		}
		return page;
	}

}
