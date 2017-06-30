package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.CommonBusiness;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		String confirm = (String) request.getParameter("confirm");
		String email = (String) request.getParameter("email");
		boolean sex = Boolean.valueOf(request.getParameter("sex"));

		String msg = "";

		if (password.equals(confirm)) {
			CommonBusiness cb = new CommonBusiness();
			try {
				boolean isRegistered = cb.register(username, password, email, sex);
				msg = username + "注册成功！当前状态为：" + isRegistered;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			msg = "注册程序失败，有可能是两次密码不一致哦。";
		}

		request.getSession().setAttribute("msg", msg);
		request.getRequestDispatcher("/info.jsp").forward(request, response);

	}

}
