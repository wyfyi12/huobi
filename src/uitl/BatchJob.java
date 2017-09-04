package uitl;


import org.apache.log4j.Logger;

import bean.Log;
import bean.SaveLog;
import common.datetime.DoDate;
import dao.mailexport;
import net.sf.json.JSONObject;
import newmail.DoLog;

public class BatchJob {
	private static Logger logger = Logger.getLogger(BatchJob.class);
	public static void gettodaybatchjob(String token) throws Exception{
		String begin_date=DoDate.getnowdatetime("d");
		String end_date=begin_date;
		JSONObject mail=new JSONObject();
		mail.element("begin_date", begin_date);
		mail.element("end_date", end_date);
		JSONObject logrs=DoLog.getbatchjob(token, mail);
		logger.info("BatchJob:"+logrs.toString());
		Log log=new Log();
		log.setInfo(logrs.toString());
		log.setTime(DoDate.getnowdatetime("s"));
		log.setType("BatchJob");
		mailexport.insertlog(log);
		SaveLog.setBllog("success,"+DoDate.getnowdatetime("s"));
	}
	
}
