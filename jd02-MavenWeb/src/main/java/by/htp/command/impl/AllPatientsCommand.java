package by.htp.command.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
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
import by.htp.service.PatientService;
import by.htp.service.ServiceException;
import by.htp.service.ServiceProvider;


public class AllPatientsCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int start = 0;
		int delimeter = 10;
		String delim;
		String startS;
		int countPatientsTableLine = 0;
		
		HttpSession session = request.getSession();
		
		if (( delim = request.getParameter(RequestParameterName.DELIMETER_LIST_OF_PATIENTS)) != null) {
			delimeter = Integer.parseInt(delim);
		} else if (session.getAttribute("Delimeter") != null) {
			delimeter = (int) session.getAttribute("Delimeter");
		}
	
		if (( startS = (request.getParameter(RequestParameterName.START_LIST_OF_PATIENTS))) != null &&
				Integer.parseInt(request.getParameter(RequestParameterName.START_LIST_OF_PATIENTS)) != 0) {
			start = (Integer.parseInt(startS)) * delimeter;
		} 
		
	
		PatientService patientService = ServiceProvider.getInstance().getPatientService();
		
		try {
			countPatientsTableLine = patientService.countOfPatients();
			
			List<Patient> list = patientService.giveAllPatientsLimit(start, delimeter);
			
			System.out.println("start^ "+ start);
			System.out.println("delimeter^ "+ delimeter + " delim " + delim);
			System.out.println("Количество пациентов " + countPatientsTableLine);
			
			if (list != null ) { 
				
				Collections.sort(list, new Comparator<Patient>() {
					@Override
					public int compare(Patient arg0, Patient arg1) {
						String s0 = arg0.getName();
						String s1 = arg1.getName();
						return (s0).compareToIgnoreCase(s1);
					}
				});
				int count = (int) Math.floor(countPatientsTableLine/delimeter);		
				
				request.setAttribute("Count", count); 
				request.setAttribute("PageUsed",(int) Math.ceil((start/delimeter)) );
				request.setAttribute("Delimeter",delimeter );
				
				session.setAttribute("Delimeter",delimeter);
				
				request.setAttribute("PatientList", list);
				
				String page = JspPageName.ALL_PATIENT_PAGE;
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
