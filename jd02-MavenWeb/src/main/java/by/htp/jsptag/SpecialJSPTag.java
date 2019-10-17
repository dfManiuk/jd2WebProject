package by.htp.jsptag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class SpecialJSPTag extends TagSupport{
	
	private static final long serialVersionUID = 1L;
	
	private JSPSetBean set;
	
	public JSPSetBean getSet() {
		return set;	
	}
	
	public void setSet(JSPSetBean set) {
		this.set = set;
	}

	@Override
	public int doStartTag() throws JspException {
		int size = new Integer(set.getSize());
		String str = "Size = <b>(" + size +")</b>";
		try {
			JspWriter out = pageContext.getOut();
			out.write(str);
			out.write("<table border=\"1\">");
			
			for (int i = 0; i < size; i++) {
				out.write("<tr><td>");
				out.write(set.getElement());
				out.write("</td></tr>");
			}
			out.write("</table>");
		} catch (IOException e) {
			throw new JspException(e);
		}
		
        return SKIP_BODY;
    }
}
