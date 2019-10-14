package by.htp.command.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.htp.command.ICommand;
import by.htp.controller.JspPageName;
import by.htp.entity.Patient;
import by.htp.service.PatientService;
import by.htp.service.ServiceException;
import by.htp.service.ServiceProvider;

public class AllDischangedPatientsCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PatientService patientService = ServiceProvider.getInstance().getPatientService();
				
		try {
		
			List<Patient> listDischangedPatients = patientService.getAllPatientDischarged();

			if (listDischangedPatients != null ) { 
				Collections.sort(listDischangedPatients, new Comparator<Patient>() {
					@Override
					public int compare(Patient arg0, Patient arg1) {
						String s0 = arg0.getName();
						String s1 = arg1.getName();
						return (s0).compareToIgnoreCase(s1);
					}
				});
							
				request.setAttribute("PatientDischanged", listDischangedPatients);
				
				String page = JspPageName.ALL_PATIENT_DISCHANGED_PAGE;
				RequestDispatcher dispatcher = request.getRequestDispatcher(page);
				dispatcher.forward(request, response);
				
				return page;
				
			} else {
			String page = JspPageName.MAIN_PAGE;	
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JspPageName.MAIN_PAGE;	
	}
	

}
