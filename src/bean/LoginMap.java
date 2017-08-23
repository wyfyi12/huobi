package bean;

import java.util.HashMap;

public class LoginMap {
	private static HashMap<String, Login> lgmap;

	public static HashMap<String, Login> getLgmap() {
		return lgmap;
	}

	public static void setLgmap(HashMap<String, Login> lgmap) {
		LoginMap.lgmap = lgmap;
	}
	
}
