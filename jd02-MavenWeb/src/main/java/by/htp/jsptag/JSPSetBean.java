package by.htp.jsptag;

import java.util.Iterator;
import java.util.Set;

public class JSPSetBean {

	private Iterator it;	
	private Set set;
	
	public JSPSetBean(Set set) {
		this.set = set;
	}
	
	public String getSize() {
		it = set.iterator();
		return Integer.toString(set.size());
	}
	
	public String getElement() {
		return it.next().toString();
	}
	
}
