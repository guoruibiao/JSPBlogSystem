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
		// ��װPost����
		Post post = new Post();
		post.setTitle(title);
		post.setDigest(content.substring(0, 28));
		post.setContent(content);
		post.setPostdate(postdate);
		post.setUser_id(user_id);

		// �鿴�����Ƿ�ɹ�
		boolean isPublished = false;
		try {
			isPublished = cb.publishPost(post);
			// ���ݷ��������������ͬ�Ķ���
			String msg = "";
			if (isPublished) {
				msg = "��ϲ����" + username + ", �������³ɹ���";
			} else {
				msg = "Sorry��" + username + ", ��������ʧ�ܣ�";
			}

			request.getSession().setAttribute("msg", msg);
			response.sendRedirect("info.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
