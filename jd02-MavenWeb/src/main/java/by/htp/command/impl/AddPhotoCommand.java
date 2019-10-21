package by.htp.command.impl;

import java.io.IOException;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.htp.command.FileCommand;
import by.htp.controller.JspPageName;


public class AddPhotoCommand implements FileCommand{
	
	public static byte[] fotobyte; 

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response, byte[] upload)
			throws ServletException, IOException {
							
			try {				
					
				if (upload != null) {
					fotobyte = upload;
					String base64Image = Base64.getEncoder().encodeToString(upload);
					request.setAttribute("base64Image", base64Image);
					
				}
				HttpSession session = request.getSession(true);
				session.getServletContext().getRequestDispatcher("/"+JspPageName.ADD_PATIENT_PAGE).forward(request, response); //TODO '/'-?
			
								
			}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return JspPageName.ADD_PATIENT_PAGE;
		}

		
	}

