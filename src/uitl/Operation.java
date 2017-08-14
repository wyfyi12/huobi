package uitl;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import bean.Log;
import common.datetime.DoDate;
import dao.mailexport;
import net.sf.json.JSONObject;
import newmail.DoLog;

public class Operation {
	private static Logger logger = Logger.getLogger(Operation.class);
	public static void gettodayoperation(String token) throws SQLException{
		String begin_date=DoDate.getnowdatetime("d");
		String end_date=begin_date;
		JSONObject mail=new JSONObject();
		mail.element("type", "1");
		mail.element("begin_date", begin_date);
		mail.element("end_date", end_date);
		JSONObject logrs=DoLog.getoperation(token, mail);
		logger.info("Operation:"+logrs.toString());
		Log log=new Log();
		log.setInfo(logrs.toString());
		log.setTime(DoDate.getnowdatetime("s"));
		log.setType("Operation");
		mailexport.insertlog(log);
	}
}
