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
				
//				HashMap<Integer, List<String>> map = medication.getProcedurs();
//				
//				for (Integer key : map.keySet()) {
//				    System.out.println("Key: " + key);
//				}
//				for (List<String> value : map.values()) {
//				    System.out.println("Value: " + value.toString().replace("[", "").replace("]", ""));
//			}
//				
//				HashMap<Integer, List<String>> map1 = medication.getMedications();
//				
//				for (Integer key : map1.keySet()) {
//				    System.out.println("Key: " + key);
//				}
//				for (List<String> value : map1.values()) {
//				    System.out.println("Value: " + value.toString().replace("[", "").replace("]", ""));
//				}
				
				
				
				request.setAttribute("Patient", patient);
				request.setAttribute("Medications", medication);
				
				String	page = JspPageName.PATIENT_CARD_PAGE;
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(page);
				dispatcher.forward(request, response);
				
			} else {
				
			}
		}catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			
		return null;
	}

}
