package test;

import common.json.DoJson;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import newmail.DoLog;
import newmail.DoToken;
import newmail.DoUser;
import uitl.BatchJob;
import uitl.Login;
import uitl.Mail;
import uitl.MailStatus;
import uitl.Operation;

public class test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String logtoken=DoToken.gettoken("wm714ea8cd6bcdb5b1", "hAfGD7Ye2O2lEBd_YGJUXjzg4BSGm2f-cyCh-0EUPaI");
		String txltoken=DoToken.gettoken("wm714ea8cd6bcdb5b1", "xwSMBSC5kVUzQFzs3CP5OO-kXvvRF-uByr4uhSw6v2s");
		//MailStatus.gettodaymailstatus(logtoken, "yzsmarts.xyz");
		//Mail.gettodaymail(logtoken);
		//String rs=DoUser.getpartyuser("1", "1", txltoken).toString();
//		JSONArray userja=DoJson.getJSONArrayfromJSONObject(rs, "userlist");
//		for(int i=0;i<userja.size();i++){
//			String userid=userja.getJSONObject(i).getString("userid");
//			JSONObject mail=new JSONObject();
//			mail.element("userid", userid);
//			mail.element("begin_date", "2017-08-21");
//			mail.element("end_date", "2017-08-21");
//			JSONObject logrs=DoLog.getlogin(logtoken, mail);
//			//Login.gettodaylogin(logtoken, userid);
//			System.out.println(logrs.toString());
//		}
		//BatchJob.gettodaybatchjob(logtoken);
		//Operation.gettodayoperation(logtoken);
		JSONObject mail=new JSONObject();
		mail.element("type", "1");
		mail.element("begin_date",  "2017-08-21");
		mail.element("end_date",  "2017-08-21");
		JSONObject logrs=DoLog.getoperation(logtoken, mail);
		System.out.println(logrs.toString());
	}
}
