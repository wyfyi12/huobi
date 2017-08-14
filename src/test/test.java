package test;

import common.json.DoJson;
import net.sf.json.JSONArray;
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
		MailStatus.gettodaymailstatus(logtoken, "yzsmarts.xyz");
		Mail.gettodaymail(logtoken);
		String rs=DoUser.getpartyuser("1", "1", txltoken).toString();
		JSONArray userja=DoJson.getJSONArrayfromJSONObject(rs, "userlist");
		for(int i=0;i<userja.size();i++){
			String userid=userja.getJSONObject(i).getString("userid");
			Login.gettodaylogin(logtoken, userid);
		}
		BatchJob.gettodaybatchjob(logtoken);
		Operation.gettodayoperation(logtoken);
	}
}
