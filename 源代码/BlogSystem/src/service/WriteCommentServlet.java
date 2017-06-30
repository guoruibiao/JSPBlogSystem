package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.CommonBusiness;

/**
 * Servlet implementation class WriteCommentServlet
 */
@WebServlet("/WriteCommentServlet")
public class WriteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WriteCommentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
		String content = request.getParameter("content");
		String username = (String) request.getSession().getAttribute("username");
		Integer post_id = Integer.valueOf(request.getParameter("post_id"));

		CommonBusiness cb = new CommonBusiness();
		// 根据用户名获取用户ID
		Integer user_id = null;
		try {
			user_id = cb.getUserInfo(username).getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 插入用户评论
		try {
			boolean isCommented = cb.addComment(content, user_id, post_id);
			String msg = "";
			if (isCommented) {
				msg = "评论成功！";
			} else {
				msg = "添加评论失败！";
			}
			request.getSession().setAttribute("msg", msg);
//			localhost:8080/BlogSystem/postdetails.do?postid=3
//			response.sendRedirect("info.jsp");
			// 评论从那个链接过来就转回哪个链接去。
			response.sendRedirect("person.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
