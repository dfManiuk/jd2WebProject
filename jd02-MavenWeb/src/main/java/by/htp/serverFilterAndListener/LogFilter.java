package by.htp.serverFilterAndListener;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.controller.JspPageName;
import by.htp.controller.RequestParameterName;
import by.htp.entity.User;

public class LogFilter implements Filter{

	private static final Logger logger = LogManager.getLogger(LogFilter.class.getName());
	
	@Override
	public void init(javax.servlet.FilterConfig filterConfig){
		
	}
	
	@Override
	public void destroy() {
	    System.out.println("FILTER:i am destory().........");
	  }
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		
		HttpSession session = null;
		String commandName = null;
		String page = JspPageName.USER_PAGE;
		
		if ((commandName = request.getParameter(RequestParameterName.LANGUAGE)) != null ) {	
						
		if (commandName.equalsIgnoreCase(RequestParameterName.LANGUAGE_CHANGE)) {
		
		String local = request.getParameter("local");
			
		session = ((HttpServletRequest) request).getSession();	
		User user = (User) session.getAttribute("UserSession");	
		request.setAttribute("User", user);
		session.setAttribute("local", local);
		
		Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        String cookieName = "local";
        if(cookies !=null) {
            for(Cookie c: cookies) {
                if(cookieName.equals(c.getName())) {
                    break;
                    
                }
             }
	
            
		request.getRequestDispatcher(page).forward(request, response);
		 
		 logger.info("LogFilter " + request.getParameter("local"));
		}	
	}
		}
		chain.doFilter(request, response);
        
	}

}
