package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import bean.Log;
import bean.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class mailexport {
	private static Logger logger = Logger.getLogger(mailexport.class);
	private static String url = "jdbc:mysql://10.66.255.102:3306/maillog?characterEncoding=utf8";
	private static String user = "root";
	private static String password = "Nantu123";
	public static Connection conn;
	public static PreparedStatement ps;
	public static ResultSet rs;
	public static Statement st;
	
	public static void getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			// TODO: handle exception
			StringBuffer stringBuffer = new StringBuffer(e.toString() + "\n");  
	        StackTraceElement[] messages = e.getStackTrace();  
	        int length = messages.length;  
	        for (int i = 0; i < length; i++) {  
	            stringBuffer.append("\t"+messages[i].toString()+"\n");  
	        }  
			logger.error(stringBuffer.toString());
		}
	}
	
	public static ArrayList<Integer> insertlog(Log log) throws SQLException {
		ArrayList<Integer> re = new ArrayList<Integer>();
		getConnection();
		try {
			st = (Statement) conn.createStatement();
			String sql = "insert into "+log.getType()+"(time,info) values('"+log.getTime() + "','" + log.getInfo()+ "')";
			if(log.getDomain()!=null){
				sql= "insert into MailStatus (time,info,domain) values('"+log.getTime() + "','" + log.getInfo()+ "','" + log.getDomain()+ "')";
			}
			st.executeUpdate(sql);
			logger.info("备份"+log.getType()+":成功!");
		} catch (SQLException e) {
			StringBuffer stringBuffer = new StringBuffer(e.toString() + "\n");  
	        StackTraceElement[] messages = e.getStackTrace();  
	        int length = messages.length;  
	        for (int i = 0; i < length; i++) {  
	            stringBuffer.append("\t"+messages[i].toString()+"\n");  
	        }  
			logger.error(stringBuffer.toString());
		}
		st.close();
		conn.close();
		return re;
	}
	

	public static ArrayList<Integer> updateuser(HashMap<String, String> usermap) throws SQLException {
		ArrayList<Integer> re = new ArrayList<Integer>();
		try {
			st = (Statement) conn.createStatement();
			String sql = "update alluser set partylist='" + usermap.get("PartyPath") + "',"+"name="+usermap.get("Name")+ "',"+"Gender="+usermap.get("Gender")+ "',"+"mobile="+usermap.get("mobile")+ "',"+"alias="+usermap.get("Alias")+"' where ExtId='"
					+ usermap.get("ExtId") + "'";
			int result = st.executeUpdate(sql);
			if (result == -1) {
				System.out.println(usermap.get("Alias") + "修改失败");
			} else {
				System.out.println(usermap.get("Alias") + "修改成功");
			}

		} catch (SQLException e) {
			StringBuffer stringBuffer = new StringBuffer(e.toString() + "\n");  
	        StackTraceElement[] messages = e.getStackTrace();  
	        int length = messages.length;  
	        for (int i = 0; i < length; i++) {  
	            stringBuffer.append("\t"+messages[i].toString()+"\n");  
	        }  
			logger.error(stringBuffer.toString());
		}
		st.close();
		return re;
	}
	
	public static ArrayList<Integer> deluser(String alias) throws SQLException {
		ArrayList<Integer> re = new ArrayList<Integer>();
		try {
			st = (Statement) conn.createStatement();
			String sql = "delete from alluser where alias = '"+alias+"'";
			int result = st.executeUpdate(sql);
			if (result == -1) {
				System.out.println(alias + "修改失败");
			} else {
				System.out.println(alias + "修改成功");
			}

		} catch (SQLException e) {
			StringBuffer stringBuffer = new StringBuffer(e.toString() + "\n");  
	        StackTraceElement[] messages = e.getStackTrace();  
	        int length = messages.length;  
	        for (int i = 0; i < length; i++) {  
	            stringBuffer.append("\t"+messages[i].toString()+"\n");  
	        }  
			logger.error(stringBuffer.toString());
		}
		st.close();
		return re;
	}
	
	public static JSONArray query(String logtype) throws SQLException {
		getConnection();
		JSONArray umap=new JSONArray();
		try {
			String sql = "select * from	"+logtype;
			System.out.println(sql);
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String time=rs.getString("time");
				String info=rs.getString("info");
				String no=rs.getString("no");
				JSONObject log=new JSONObject();
				log.element("time", time);
				log.element("info", info);
				log.element("no", no);
				umap.add(log);
			}
		} catch (SQLException e) {
			StringBuffer stringBuffer = new StringBuffer(e.toString() + "\n");  
	        StackTraceElement[] messages = e.getStackTrace();  
	        int length = messages.length;  
	        for (int i = 0; i < length; i++) {  
	            stringBuffer.append("\t"+messages[i].toString()+"\n");  
	        }  
			logger.error(stringBuffer.toString());
		}
		st.close();
		conn.close();
		return umap;
	}
	
	public static JSONArray querymsbytime(String btime,String etime,String domain) throws SQLException {
		getConnection();
		JSONArray umap=new JSONArray();
		try {
			String sql = "select * from	MailStatus where domain ='"+domain+"' and  time between '"+btime+"' and '"+etime+"'";
			System.out.println(sql);
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String time=rs.getTime("time").toString();
				String info=rs.getString("info");
				String no=rs.getString("no");
				JSONObject log=new JSONObject();
				log.element("time", time);
				log.element("info", info);
				log.element("no", no);
				umap.add(log);
			}
		} catch (SQLException e) {
			StringBuffer stringBuffer = new StringBuffer(e.toString() + "\n");  
	        StackTraceElement[] messages = e.getStackTrace();  
	        int length = messages.length;  
	        for (int i = 0; i < length; i++) {  
	            stringBuffer.append("\t"+messages[i].toString()+"\n");  
	        }  
			logger.error(stringBuffer.toString());
		}
		st.close();
		conn.close();
		return umap;
	}
	
	public static User queryuser(String userid) throws SQLException {
		getConnection();
		User u = null;
		try {
			String sql = "select * from	users where userid = '"+userid+"'";
			System.out.println(sql);
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String auth = rs.getString("auth");
				String password=rs.getString("password");
				String username=rs.getString("username");
				u=new User(userid, password, auth, username);
			}
		} catch (SQLException e) {
			StringBuffer stringBuffer = new StringBuffer(e.toString() + "\n");  
	        StackTraceElement[] messages = e.getStackTrace();  
	        int length = messages.length;  
	        for (int i = 0; i < length; i++) {  
	            stringBuffer.append("\t"+messages[i].toString()+"\n");  
	        }  
			logger.error(stringBuffer.toString());
		}
		st.close();
		conn.close();
		return u;
	}
}
