package by.htp.jsptag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class JSPTagWithBody extends BodyTagSupport {
	
	private static final long serialVersionUID = 1L;

	private int num;
	
	public void setNum(String num) {
		this.num = new Integer(num);
	}
	
	@Override
	public int doStartTag() throws JspException {
        try {
			pageContext.getOut().write("<TABLE BORDER=\"3\"WIDTH=\"100%\">");
			pageContext.getOut().write("<TR><TD>");
		} catch (IOException e) {
			throw new JspException(e);
		}
		
		return EVAL_BODY_INCLUDE;
    }
	@Override
	 public int doAfterBody() throws JspException {
		 	if (num>1) {
				num = num -1;
				try {
					pageContext.getOut().write("</TR></TD><TR><TD>");
				} catch (IOException e) {
					throw new JspException(e);
				}
				return EVAL_BODY_AGAIN;
			} else {
				 return SKIP_BODY;
			}
	 }
	public int doEndTag() throws JspException {
		try {
			pageContext.getOut().write("</TR></TD>");
			pageContext.getOut().write("<TABLE>");
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	    }
	

}
