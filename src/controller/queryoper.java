package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.mailexport;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class queryms
 */
@WebServlet("/queryoper")
public class queryoper extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public queryoper() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bt=request.getParameter("begintime")+" 00:00:00";
		String et=request.getParameter("endtime")+" 23:59:59";
		String type=request.getParameter("type");
		System.out.println(bt+","+et+","+type);
		JSONArray lja=new JSONArray();
		try {
			lja=mailexport.queryoper(bt, et, type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(lja);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(lja.toString());
	}

}
