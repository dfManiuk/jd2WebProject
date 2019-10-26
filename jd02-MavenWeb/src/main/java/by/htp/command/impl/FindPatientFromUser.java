package by.htp.command.impl;

import java.io.IOException;
import java.util.ArrayList;
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


public class FindPatientFromUser implements ICommand {
	

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user= null;
		String page = null;
		ArrayList<Patient> list = null;
		ArrayList<Integer> listInt = null;
		int start = 0;
		int delimeter = 10;
		String delim;
		String startS;
		int countPatientsTableLine = 0;
		HttpSession session = null;
		
		if (request.getSession() != null) {
			session = request.getSession();
			user = (User) session.getAttribute("UserSession");	
		} else {
			request.setAttribute("NullUser", "nullUserRequest");
			page = JspPageName.MAIN_PAGE;
				
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}

		
		if (( delim = request.getParameter(RequestParameterName.DELIMETER_LIST_OF_PATIENTS)) != null) {
			delimeter = Integer.parseInt(delim);
		} else if (session.getAttribute("Delimeter") != null) {
			delimeter = (int) session.getAttribute("Delimeter");
		}
	
		if (( startS = (request.getParameter(RequestParameterName.START_LIST_OF_PATIENTS))) != null &&
				Integer.parseInt(request.getParameter(RequestParameterName.START_LIST_OF_PATIENTS)) != 0) {
			start = (Integer.parseInt(startS)) * delimeter;
		} 
		
		PatientService service = ServiceProvider.getInstance().getPatientService();
		
		try {
			
			if (user.getPosition().equalsIgnoreCase("Врач")) {
				countPatientsTableLine = service.countOfPatientsForUser(user);
				list = service.findUserFromPatient(user, start, delimeter);
				listInt = service.findUserFromPatientWithNotCheckMedication(user.getId(), start, delimeter);
		
			} else if (user.getPosition().equalsIgnoreCase("медсестра")) {
				list = service.findUserFromPatientForNurse(start, delimeter);
				countPatientsTableLine = service.countOfPatients();
				listInt = service.findUserFromPatientWithNotCheckMedicationForNurse(start, delimeter);

			} else  {
				countPatientsTableLine = service.countOfPatientsForUser(user);
				list = service.findUserFromPatient(user, start, delimeter);
				listInt = service.findUserFromPatientWithNotCheckMedication(user.getId(), start, delimeter);
			}
			
			if (list.size() !=0 ) { 
				
				int count = (int) Math.floor(countPatientsTableLine/delimeter);	
				
				request.setAttribute("User", user);
				request.setAttribute("Patients", list);
				request.setAttribute("Patients", list);
				request.setAttribute("ListInteger", listInt);
				request.setAttribute("Count", count); 
				request.setAttribute("PageUsed",(int) Math.ceil((start/delimeter)) );
				request.setAttribute("Delimeter",delimeter );
				
				session.setAttribute("Delimeter",delimeter);
				
				page = JspPageName.USER_PAGE;
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(page);
				dispatcher.forward(request, response);
				
				return page;
				
			} else {
				
			request.setAttribute("NullUser", "nullUserRequest");
			page = JspPageName.MAIN_PAGE;
				
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
