package service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Post;
import biz.CommonBusiness;

/**
 * Servlet implementation class GetPaginationPostServlet
 */
@WebServlet("/GetPaginationPostServlet")
public class GetPaginationPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetPaginationPostServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Integer currpage = Integer.valueOf(request.getParameter("currpage"));
		Integer next = Integer.valueOf(request.getParameter("next"));
		Integer pagesize = Integer.valueOf(request.getParameter("pagesize"));

		CommonBusiness cb = new CommonBusiness();
		try {
			ArrayList<Post> posts = cb.getPagination(currpage, next, pagesize);
			request.getSession().setAttribute("prevpage", currpage);
			request.getSession().setAttribute("posts", posts);
			// request.getRequestDispatcher("allposts.jsp").forward(request,
			// response);
			response.sendRedirect("allposts.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
