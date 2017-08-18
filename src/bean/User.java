package bean;

public class User {
	private String userid;
	private String password;
	private String auth;
	private String username;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public User(String userid, String password, String auth, String username) {
		super();
		this.userid = userid;
		this.password = password;
		this.auth = auth;
		this.username = username;
	}
	
}
