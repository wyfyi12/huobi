package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import bean.Log;
import bean.User;
import common.json.DoJson;
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
			if(log.getUserid()!=null){
				sql= "insert into "+log.getType()+" (time,info,userid) values('"+log.getTime() + "','" + log.getInfo()+ "','" + log.getUserid()+ "')";
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
				java.sql.Timestamp timeStamp = rs.getTimestamp("time");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				String time = sdf.format(timeStamp); 
				System.out.println("time:"+time);
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
	
	public static JSONArray querymi(String btime,String etime,String userid) throws SQLException {
		getConnection();
		JSONArray umap=new JSONArray();
		try {
			String sql = "select * from	Mail where userid ='"+userid+"' and  time between '"+btime+"' and '"+etime+"'";
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
	
	public static JSONArray querymsi(String btime,String etime) throws SQLException {
		getConnection();
		JSONArray umap=new JSONArray();
		try {
			String sql = "select * from	Mail time between '"+btime+"' and '"+etime+"'";
			System.out.println(sql);
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				java.sql.Timestamp timeStamp = rs.getTimestamp("time");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				String time = sdf.format(timeStamp);
				String info=rs.getString("info");
				String no=rs.getString("no");
				String userid=rs.getString("userid");
				JSONArray mailja=DoJson.getJSONArrayfromJSONObject(info, "list");
				int get=0;int send=0;
				for(int i=0;i<mailja.size();i++){
					JSONObject mailjob=mailja.getJSONObject(i);
					if(mailjob.getInt("mailtype")==1){
						send++;
					}else{
						get++;
					}
				}
				JSONObject log=new JSONObject();
				log.element("time", time);
				log.element("get", get);
				log.element("send", send);
				log.element("no", no);
				log.element("userid", userid);
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
	
	public static JSONArray querylogin(String btime,String etime,String userid) throws SQLException {
		getConnection();
		JSONArray umap=new JSONArray();
		try {
			String sql = "select * from	Login where userid ='"+userid+"' and  time between '"+btime+"' and '"+etime+"'";
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
	
	public static JSONArray querybatch(String btime,String etime,String type) throws SQLException {
		getConnection();
		JSONArray umap=new JSONArray();
		try {
			String sql = "select * from	BatchJob where info like '%type\":"+type+"%' and  time between '"+btime+"' and '"+etime+"'";
			if(type.equals("all")){
				sql = "select * from	BatchJob where  time between '"+btime+"' and '"+etime+"'";
			}
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
	
	public static JSONArray queryoper(String btime,String etime,String type) throws SQLException {
		getConnection();
		JSONArray umap=new JSONArray();
		try {
			String sql = "select * from	Operation where info like '%type\":"+type+"%' and  time between '"+btime+"' and '"+etime+"'";
			if(type.equals("all")){
				sql = "select * from Operation where  time between '"+btime+"' and '"+etime+"'";
			}
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
				System.out.println(auth+password+username);
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
