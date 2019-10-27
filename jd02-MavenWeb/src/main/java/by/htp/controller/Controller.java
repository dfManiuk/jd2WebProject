package by.htp.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.htp.command.CommandHelper;
import by.htp.command.FileCommand;
import by.htp.command.ICommand;


public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LogManager.getLogger(Controller.class);	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath()).append(request.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String commandName;
		if ((commandName = request.getParameter(RequestParameterName.COMMAND_NAME)) != null) {
			
			ICommand command = CommandHelper.getInstance().getCommand(commandName);
			command.execute(request, response);
		} else if ((commandName = request.getParameter(RequestParameterName.LANGUAGE))  != null & 
				!RequestParameterName.LANGUAGE_CHANGE.equals(commandName)){
			ICommand command = CommandHelper.getInstance().getCommand(commandName);
			command.execute(request, response);
		} else if (!RequestParameterName.LANGUAGE_CHANGE.equals(commandName)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletContext servletContext = this.getServletConfig().getServletContext();
			File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
			factory.setRepository(repository);
			ServletFileUpload upload = new ServletFileUpload(factory);
			byte[] data = null;
			try {
				@SuppressWarnings("unchecked")
				List<FileItem> items = upload.parseRequest(request);
				for (FileItem fileItem : items) {
					if ((commandName = fileItem.getFieldName()).equals(RequestParameterName.COMMAND_FILE)) {
					
					Iterator<FileItem> iter = items.iterator();
					while (iter.hasNext()) {
						FileItem item = iter.next();
						if (!item.isFormField()) {
							InputStream uploadedStream = item.getInputStream();
							uploadedStream.close();
							data = item.get();
							FileCommand command = CommandHelper.getInstance().getCommandFile(RequestParameterName.COMMAND_FILE);
							command.execute(request, response, data);						
						}
					}
					}					
				}
			} catch (FileUploadException e) {
				logger.error(e.toString());
				e.printStackTrace();
			} 
		}
	}
}
