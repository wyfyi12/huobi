package uitl;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import bean.Log;
import common.datetime.DoDate;
import common.json.DoJson;
import dao.mailexport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import newmail.DoLog;
import newmail.DoUser;

public class Login {
	private static Logger logger = Logger.getLogger(Login.class);
	public static void gettodaylogin(String token,String txltoken) throws SQLException{
		String begin_date=DoDate.getnowdatetime("d");
		String end_date=begin_date;
		String rs=DoUser.getpartyuser("1", "1", txltoken).toString();
		JSONArray userja=DoJson.getJSONArrayfromJSONObject(rs, "userlist");
		for(int i=0;i<userja.size();i++){
			String userid=userja.getJSONObject(i).getString("userid");
			JSONObject mail=new JSONObject();
			mail.element("userid", userid);
			mail.element("begin_date", begin_date);
			mail.element("end_date", end_date);
			JSONObject logrs=DoLog.getlogin(token, mail);
			logger.info("Login:"+userid+":"+logrs.toString());
			Log log=new Log();
			log.setInfo(logrs.toString());
			log.setTime(DoDate.getnowdatetime("s"));
			log.setType("Login");
			mailexport.insertlog(log);
		}
	}
}
