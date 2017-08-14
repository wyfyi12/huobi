package uitl;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import bean.Log;
import common.datetime.DoDate;
import dao.mailexport;
import net.sf.json.JSONObject;
import newmail.DoLog;

public class MailStatus {
	private static Logger logger = Logger.getLogger(MailStatus.class);
	public static void gettodaymailstatus(String token,String domain) throws SQLException{
		String begin_date=DoDate.getnowdatetime("d");
		String end_date=begin_date;
		JSONObject mail=new JSONObject();
		mail.element("domain", domain);
		mail.element("begin_date", begin_date);
		mail.element("end_date", end_date);
		JSONObject logrs=DoLog.getmailstatus(token, mail);
		logger.info("MailStatus:"+logrs.toString());
		Log log=new Log();
		log.setInfo(logrs.toString());
		log.setTime(DoDate.getnowdatetime("s"));
		log.setType("MailStatus");
		mailexport.insertlog(log);
	}
}
