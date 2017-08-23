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

public class Mail {
	private static Logger logger = Logger.getLogger(Mail.class);
	public static void gettodaymail(String token,String txltoken) throws SQLException{
		String begin_date=DoDate.getnowdatetime("d");
		String end_date=begin_date;
		String rs=DoUser.getpartyuser("1", "1", txltoken).toString();
		JSONArray userja=DoJson.getJSONArrayfromJSONObject(rs, "userlist");
		for(int i=0;i<userja.size();i++){
		String userid=userja.getJSONObject(i).getString("userid");
		JSONObject mail=new JSONObject();
		mail.element("mailtype", "0");
		mail.element("begin_date", begin_date);
		mail.element("end_date", end_date);
		mail.element("userid", userid);
		JSONObject logrs=DoLog.getmail(token, mail);
		logger.info("Mail:"+logrs.toString());
		Log log=new Log();
		log.setInfo(logrs.toString());
		log.setTime(DoDate.getnowdatetime("s"));
		log.setType("Mail");
		log.setUserid(userid);
		mailexport.insertlog(log);
		}
	}
}
