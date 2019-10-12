package by.htp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface FileCommand {
	public String execute(HttpServletRequest request, HttpServletResponse response, byte[] upload) throws ServletException, IOException;
}
