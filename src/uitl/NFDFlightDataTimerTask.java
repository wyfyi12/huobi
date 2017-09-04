package uitl;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import bean.Prop;
import bean.SaveLog;
import common.datetime.DoDate;
import newmail.DoToken;

public class NFDFlightDataTimerTask extends TimerTask {
    private static Logger logger = Logger.getLogger(NFDFlightDataTimerTask.class);
    @Override
    public void run() {
        try {
        	String logtoken=DoToken.gettoken(Prop.getCorpid(), Prop.getLogkey());
    		String txltoken=DoToken.gettoken(Prop.getCorpid(), Prop.getTxlkey());
    		Login.gettodaylogin(logtoken, txltoken);
    		BatchJob.gettodaybatchjob(logtoken);
    		Operation.gettodayoperation(logtoken);
    		for(int i=0;i<Prop.getDomains().size();i++){
    			MailStatus.gettodaymailstatus(logtoken, Prop.getDomains().get(i));
    		}
    		SaveLog.setMslog("success,"+DoDate.getnowdatetime("s"));
    		Mail.gettodaymail(logtoken,txltoken);
    		logger.info(DoDate.getnowdatetime("d")+"备份完成");
        } catch (Exception e) {
        	StringBuffer stringBuffer = new StringBuffer(e.toString() + "\n");  
	        StackTraceElement[] messages = e.getStackTrace();  
	        int length = messages.length;  
	        for (int i = 0; i < length; i++) {  
	            stringBuffer.append("\t"+messages[i].toString()+"\n");  
	        }  
			logger.error(stringBuffer.toString());
        }
    }
     
}
