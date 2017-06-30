package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.CommonBusiness;

/**
 * Servlet implementation class PostModifyServlet
 */
@WebServlet("/PostModifyServlet")
public class PostModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
//		Integer post_id = Integer.valueOf(request.getParameter("postid"));
//		CommonBusiness cb = new CommonBusiness();
//		cb.upda
		// TODO 哈哈哈，修改功能还没来得及写呢
		String msg = "修改文章这是个TODO项 :)";
		request.getSession().setAttribute("msg", msg);
		response.sendRedirect("info.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
