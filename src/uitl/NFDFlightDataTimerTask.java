package uitl;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import common.datetime.DoDate;
import newmail.DoToken;

public class NFDFlightDataTimerTask extends TimerTask {
    private static Logger logger = Logger.getLogger(NFDFlightDataTimerTask.class);
    @Override
    public void run() {
        try {
        	String logtoken=DoToken.gettoken("wm714ea8cd6bcdb5b1", "hAfGD7Ye2O2lEBd_YGJUXjzg4BSGm2f-cyCh-0EUPaI");
    		String txltoken=DoToken.gettoken("wm714ea8cd6bcdb5b1", "xwSMBSC5kVUzQFzs3CP5OO-kXvvRF-uByr4uhSw6v2s");
    		Login.gettodaylogin(logtoken, txltoken);
    		BatchJob.gettodaybatchjob(logtoken);
    		Operation.gettodayoperation(logtoken);
    		MailStatus.gettodaymailstatus(logtoken, "yzsmarts.xyz");
    		MailStatus.gettodaymailstatus(logtoken, "txmail.xyz");
    		Mail.gettodaymail(logtoken);
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
