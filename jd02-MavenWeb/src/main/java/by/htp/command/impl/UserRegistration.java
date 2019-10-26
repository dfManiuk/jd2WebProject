package by.htp.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.htp.command.ICommand;
import by.htp.controller.JspPageName;
import by.htp.controller.RequestParameterName;
import by.htp.entity.User;
import by.htp.service.ServiceException;
import by.htp.service.ServiceProvider;
import by.htp.service.UserService;

public class UserRegistration implements ICommand {


	final String page = JspPageName.REGISTRATION; 
	final String pageUser = JspPageName.USER_PAGE;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	User newUser=null;

	String login = request.getParameter(RequestParameterName.NICK_NAME);
	String password = request.getParameter(RequestParameterName.PASSWORD);
	String name = request.getParameter(RequestParameterName.NAME);
	String position = request.getParameter(RequestParameterName.POSITION);
	String specialization = request.getParameter(RequestParameterName.SPECIALIZATION);
	
	newUser = new User();
	newUser.setName(name);
	newUser.setLogin(login);
	newUser.setPassword(password);
	newUser.setPosition(position);
	newUser.setSpecialization(specialization);
	
	HttpSession session = request.getSession(true);
	if (request.getParameter("local") != null) {
		session.setAttribute("local", request.getParameter("local"));	
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
	UserService userService = ServiceProvider.getInstance().getUserService();
	
	try {
		newUser = userService.registration(newUser);
		
		if (null != newUser ) {
			
			session.setAttribute("User", newUser);
			request.setAttribute("User", newUser);
				
		RequestDispatcher dispatcher = request.getRequestDispatcher(pageUser);
		dispatcher.forward(request, response);
		} else {
			request.setAttribute("NullUser", "nullUserRequest");
 			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}

	} catch (ServiceException e) {	
		e.printStackTrace();
	} 
	
	return pageUser;
}
	}


