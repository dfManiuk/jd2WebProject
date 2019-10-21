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

public class UserEditProfile implements ICommand {

	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	
		System.out.println("USerEditProfile");
		
	
		String login = request.getParameter(RequestParameterName.NICK_NAME);
		String password = request.getParameter(RequestParameterName.PASSWORD);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		
		user.setLogin(login);
		user.setPassword(password);
		
		UserService userService = ServiceProvider.getInstance().getUserService();
		
		System.out.println(user.toString());
		
		try {
			
			user = userService.editProfile(user);
				
			request.setAttribute("User", user);
		
			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.USER_PAGE);
			dispatcher.forward(request, response);
		
		} catch (ServiceException e) {
			
			e.printStackTrace();
		}	
		return JspPageName.MAIN_PAGE;
	}

}
