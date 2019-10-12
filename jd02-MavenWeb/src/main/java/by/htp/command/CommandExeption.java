package by.htp.command;

import by.htp.exeption.ProjectException;

public class CommandExeption extends ProjectException {
	
	private static final long serialVersionUID = 1L;
	
	public CommandExeption(String msg) {
		super(msg);
	}
	
	public CommandExeption(String msg, Exception e) {
		super(msg, e);
	}
}
