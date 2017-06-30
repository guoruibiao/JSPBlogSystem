package service;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Post;
import biz.CommonBusiness;

/**
 * Servlet implementation class WritePostServlet
 */
@WebServlet("/WritePostServlet")
public class WritePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WritePostServlet() {
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
		request.setCharacterEncoding("UTF-8");
		String username = (String) request.getSession().getAttribute("username");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Date postdate = new Date();
		Integer user_id = null;
		CommonBusiness cb = new CommonBusiness();
		try {
			user_id = cb.getUserInfo(username).getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 组装Post对象
		Post post = new Post();
		post.setTitle(title);
		post.setDigest(content.substring(0, 28));
		post.setContent(content);
		post.setPostdate(postdate);
		post.setUser_id(user_id);

		// 查看发表是否成功
		boolean isPublished = false;
		try {
			isPublished = cb.publishPost(post);
			// 根据发表情况，做出不同的动作
			String msg = "";
			if (isPublished) {
				msg = "恭喜您：" + username + ", 发表文章成功！";
			} else {
				msg = "Sorry：" + username + ", 发表文章失败！";
			}

			request.getSession().setAttribute("msg", msg);
			response.sendRedirect("info.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
