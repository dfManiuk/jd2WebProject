package by.htp.command.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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


public class LoginationCommand implements ICommand {

	 
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
				
		User user = null;
		user = null;
		String page = null;

		String login = request.getParameter(RequestParameterName.NICK_NAME);
		String password = request.getParameter(RequestParameterName.PASSWORD);

		UserService userService = ServiceProvider.getInstance().getUserService();
		
		HttpSession session = request.getSession(true);
		session.setAttribute("local", request.getParameter("local"));
		
		try {
			user = userService.authorization(login, password);

			if (user != null && user.getId() != 0) { 
				
				if (request.getParameter("local") == null) { 
					Cookie cookie = new Cookie("local", "ru");
					response.addCookie(cookie);
				}

				session.setAttribute("UserSession", user);
				request.setAttribute("User", user);
				
				page = JspPageName.USER_PAGE;
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(page);
				dispatcher.forward(request, response);
				
				return page;
				
			} else {
			if (login != null || password != null) {
				request.setAttribute("NullUser", "nullUserRequest");
			}
		
			page = JspPageName.MAIN_PAGE;	
				
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}

}
