package by.htp.command.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.htp.command.ICommand;
import by.htp.controller.JspPageName;
import by.htp.entity.Medication;
import by.htp.entity.Patient;
import by.htp.service.PatientService;
import by.htp.service.ServiceException;
import by.htp.service.ServiceProvider;

public class AllMedicationCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Patient patient = null;
		Medication medication = null;
		
		PatientService patientService = ServiceProvider.getInstance().getPatientService();
		
		HttpSession session = request.getSession();
		patient = (Patient) session.getAttribute("Patient");

		medication = new Medication();
				
		try {
			medication = patientService.getPatientMedications(patient);
			
			if (patient != null && medication != null ) {
				
				request.setAttribute("Patient", patient);
				request.setAttribute("Medications", medication);
				
				String	page = JspPageName.PATIENT_CARD_PAGE;
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(page);
				dispatcher.forward(request, response);
				
			} else {
				response.sendRedirect(JspPageName.USER_PAGE);
			}
		}catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			
		return null;
	}

}
