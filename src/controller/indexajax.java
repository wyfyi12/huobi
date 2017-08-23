package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import bean.LoginMap;
import bean.User;
import dao.mailexport;
import net.sf.json.JSONObject;
import uitl.GetIp;
import uitl.UserAuth;

/**
 * Servlet implementation class index
 */
@WebServlet("/indexajax")
public class indexajax extends HttpServlet {
	private static Logger logger = Logger.getLogger(indexajax.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public indexajax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userid=request.getParameter("userid");
		String ulja="";
		String ip=GetIp.getIpAddress(request);
		System.out.println(LoginMap.getLgmap().get(userid).getIp());
		try {
			if(LoginMap.getLgmap()!=null&&ip.equals(LoginMap.getLgmap().get(userid).getIp())&&(System.currentTimeMillis() - LoginMap.getLgmap().get(userid).getTime()) / (1000 * 60)<30){
			User u=mailexport.queryuser(userid);
			ulja=UserAuth.getindexauth(u.getAuth());
			}else{
				JSONObject rs=new JSONObject();
				rs.element("rs", "timeerr");
				ulja=rs.toString();
			}
		} catch (Exception e) {
			StringBuffer stringBuffer = new StringBuffer(e.toString() + "\n");  
	        StackTraceElement[] messages = e.getStackTrace();  
	        int length = messages.length;  
	        for (int i = 0; i < length; i++) {  
	            stringBuffer.append("\t"+messages[i].toString()+"\n");  
	        }  
			logger.error(stringBuffer.toString());
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(ulja);
	}
}
