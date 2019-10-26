package by.htp.command.impl;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.htp.command.ICommand;
import by.htp.controller.JspPageName;
import by.htp.controller.RequestParameterName;
import by.htp.entity.Doctor;
import by.htp.entity.Patient;
import by.htp.service.PatientService;
import by.htp.service.ServiceException;
import by.htp.service.ServiceProvider;
import by.htp.service.UserService;

public class PatientFixingCommand implements ICommand {

	private String	page = JspPageName.USER_PAGE;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserService userService = ServiceProvider.getInstance().getUserService();
		PatientService patientService = ServiceProvider.getInstance().getPatientService();
		
			try {
				
			String fixPatient = request.getParameter(RequestParameterName.FIX_PATIENT);
			String fixUser = request.getParameter(RequestParameterName.FIX_USER);
			if (null != fixPatient && null !=fixUser ) {
				patientService.fixing(fixUser, fixPatient);
			}
				
			List<Doctor> list =	userService.findAllDoctors();
			List<Patient> listPatients = patientService.giveAllPatients();
			
			
			if (list != null) {
				
				request.setAttribute("list", list);
				request.setAttribute("patients", listPatients);
				
				String page = JspPageName.FIXING_PAGE;
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(page);
				dispatcher.forward(request, response);
				
				return page;
				
			} else {

			response.sendRedirect(page);
			}
		} catch (ServiceException e) {
			// TODO 
			e.printStackTrace();
		}
		return page;
	}

}
