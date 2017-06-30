package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.CommonBusiness;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String flag = req.getParameter("flag");
		
		
		CommonBusiness cb = new CommonBusiness();
		try {
			boolean isLogined = cb.login(username, password);
			req.getSession().setAttribute("isLogined", isLogined);
			req.getSession().setAttribute("username", username);
			
			if(flag.equals("admin")) {
				resp.sendRedirect("admin/panel.jsp");
			}else{
				resp.sendRedirect("person.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
