package by.htp.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.htp.command.ICommand;
import by.htp.controller.JspPageName;
import by.htp.entity.Doctor;
import by.htp.service.ServiceException;
import by.htp.service.ServiceProvider;
import by.htp.service.UserService;

public class AllDissmisedUsersCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UserService userService = ServiceProvider.getInstance().getUserService();
		
		try {
			List<Doctor> list = userService.findAllDoctors();
			List<Doctor> listDissmis = userService.findAllDoctorsToLeave();

			if (list != null ) { 
				request.setAttribute("DoctorList", list);
				request.setAttribute("DoctorDissmisList", listDissmis);
				
				String page = JspPageName.ALL_USER_PAGE;
				RequestDispatcher dispatcher = request.getRequestDispatcher(page);
				dispatcher.forward(request, response);
				
				return page;
				
			} else {
			String page = JspPageName.MAIN_PAGE;	
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			
			}
		} catch (ServiceException e) {
			// TODO 
			e.printStackTrace();
		}
		return JspPageName.MAIN_PAGE;	
	}
	

}
