package uitl;

import java.net.URLEncoder;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserAuth {
	public static String getindexauth(String auth) throws Exception{
		JSONArray authja=new JSONArray();
		if(auth.contains("1")){
			JSONObject indexul=new JSONObject();
			indexul.element("li", "<li><a href='#'  onclick='history.go(0)' >"+URLEncoder.encode("首页", "utf-8")+"</a></li>");
			authja.add(indexul);
		}
		if(auth.contains("2")){
			JSONObject indexul=new JSONObject();
			indexul.element("li", "<li><a href='#s' onclick='mailstatus()'  >"+URLEncoder.encode("邮件概况", "utf-8")+"</a></li>");
			authja.add(indexul);
		}
		if(auth.contains("3")){
			JSONObject indexul=new JSONObject();
			indexul.element("li", "<li><a href='#' onclick='mailinfo()'  >"+URLEncoder.encode("邮件记录", "utf-8")+"</a></li>");
			authja.add(indexul);
		}
		if(auth.contains("4")){
			JSONObject indexul=new JSONObject();
			indexul.element("li", "<li><a href='#' onclick='loginlog()'  >"+URLEncoder.encode("登陆记录", "utf-8")+"</a></li>");
			authja.add(indexul);
		}
		if(auth.contains("5")){
			JSONObject indexul=new JSONObject();
			indexul.element("li", "<li><a href='#' onclick='oprlog()'  >"+URLEncoder.encode("操作记录", "utf-8")+"</a></li>");
			authja.add(indexul);
		}
		if(auth.contains("6")){
			JSONObject indexul=new JSONObject();
			indexul.element("li", "<li><a href='#' onclick='batchlog()'  >"+URLEncoder.encode("批量任务", "utf-8")+"</a></li>");
			authja.add(indexul);
		}
		return authja.toString();
	}
}
