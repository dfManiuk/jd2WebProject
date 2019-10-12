package by.htp.command.impl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.htp.command.ICommand;
import by.htp.controller.JspPageName;
import by.htp.entity.User;
import by.htp.service.ServiceException;
import by.htp.service.ServiceProvider;
import by.htp.service.UserService;

public class UserDismissalCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = null;
		
		if (request.getSession() == null) {
			response.sendRedirect(JspPageName.MAIN_PAGE);
		}
		HttpSession session = request.getSession();
		user = (User) session.getAttribute("UserSession");
		
		UserService userService = ServiceProvider.getInstance().getUserService();
		
		try {

			userService.updateUserOutOf(user.getId());
			
			if (user.getPosition().equals("заведующий")) { 						
				
				request.setAttribute("User", user);
				RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.USER_PAGE);
				dispatcher.forward(request, response);					
			} else {

			RequestDispatcher dispatcher = request.getRequestDispatcher(JspPageName.USER_PAGE);
			dispatcher.forward(request, response);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "jsp/dischangePage.jsp";

	}

}
