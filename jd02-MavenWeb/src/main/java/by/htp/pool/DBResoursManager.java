package by.htp.pool;
import java.util.ResourceBundle;

public class DBResoursManager {
	
	private final static DBResoursManager instance = new DBResoursManager();
	
	private ResourceBundle bundle = ResourceBundle.getBundle("db");

	public static DBResoursManager getInstance() {
		return instance;
	}

	public String getValue(String key) {
		return bundle.getString(key);
	}
}
