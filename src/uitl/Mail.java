package uitl;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import bean.Log;
import common.datetime.DoDate;
import dao.mailexport;
import net.sf.json.JSONObject;
import newmail.DoLog;

public class Mail {
	private static Logger logger = Logger.getLogger(Mail.class);
	public static void gettodaymail(String token) throws SQLException{
		String begin_date=DoDate.getnowdatetime("d");
		String end_date=begin_date;
		JSONObject mail=new JSONObject();
		mail.element("mailtype", "0");
		mail.element("begin_date", begin_date);
		mail.element("end_date", end_date);
		JSONObject logrs=DoLog.getmail(token, mail);
		logger.info("Mail:"+logrs.toString());
		Log log=new Log();
		log.setInfo(logrs.toString());
		log.setTime(DoDate.getnowdatetime("s"));
		log.setType("Mail");
		mailexport.insertlog(log);
	}
}
