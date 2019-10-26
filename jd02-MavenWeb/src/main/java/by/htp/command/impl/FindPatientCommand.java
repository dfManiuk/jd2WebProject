package by.htp.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.htp.command.ICommand;
import by.htp.controller.JspPageName;
import by.htp.controller.RequestParameterName;
import by.htp.entity.Patient;
import by.htp.entity.User;
import by.htp.service.PatientService;
import by.htp.service.ServiceException;
import by.htp.service.ServiceProvider;

public class FindPatientCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = null;
		String page = JspPageName.USER_PAGE;;
		
		String patientData = request.getParameter(RequestParameterName.PATIENT_DATA);
		
		PatientService patientService = ServiceProvider.getInstance().getPatientService();
		HttpSession session = request.getSession();
		user = (User) session.getAttribute("UserSession");
		
		try {
			List<Patient> list = patientService.find(patientData); 

			if (list.size() != 0) { 
				
				request.setAttribute("User", user);
				request.setAttribute("Patients", list);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(page);
				dispatcher.forward(request, response);
				
				return page;
				
			} else {
			 request.setAttribute("User", user);
			 request.setAttribute("Patients", list);	
			 
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
