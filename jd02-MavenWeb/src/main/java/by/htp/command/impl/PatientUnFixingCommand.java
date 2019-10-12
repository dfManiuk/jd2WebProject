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

public class PatientUnFixingCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UserService userService = ServiceProvider.getInstance().getUserService();
		PatientService patientService = ServiceProvider.getInstance().getPatientService();
		
			try {
				
			String unFixPatient = request.getParameter(RequestParameterName.UN_FIX_PATIENT);
			String unFixUser = request.getParameter(RequestParameterName.UN_FIX_USER);
			patientService.unFixing(unFixUser, unFixPatient);
				
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

		   String	page = JspPageName.USER_PAGE;
				
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
