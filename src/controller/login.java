package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import bean.User;
import dao.mailexport;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static Logger logger = Logger.getLogger(login.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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
		String userid=request.getParameter("userid");
		String password=request.getParameter("password");
		JSONObject rsjob=new JSONObject();
		try {
			User u=mailexport.queryuser(userid);
			if(u==null){
				rsjob.element("rs", "unregistered");
			} else if(u.getPassword().equals(password)){
				rsjob.element("rs", "success");
				rsjob.element("userid", userid);
			}else{
				rsjob.element("rs", "fail");
			}
		} catch (SQLException e) {
			StringBuffer stringBuffer = new StringBuffer(e.toString() + "\n");  
	        StackTraceElement[] messages = e.getStackTrace();  
	        int length = messages.length;  
	        for (int i = 0; i < length; i++) {  
	            stringBuffer.append("\t"+messages[i].toString()+"\n");  
	        }  
			logger.error(stringBuffer.toString());
		}
		System.out.println(rsjob.toString());
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(rsjob.toString());
	}

}
