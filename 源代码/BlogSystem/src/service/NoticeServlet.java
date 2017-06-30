package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import biz.WatchesBusiness;

/**
 * Servlet implementation class NoticeServlet
 */
@WebServlet("/NoticeServlet")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 针对前端的ajax请求，为echarts准备图表数据。
		/**
		 * 规定flag目前有两个值，对于浏览量使用article代替；对于作者使用author代替。 其他的情况可作为拓展项。
		 */
		String flag = request.getParameter("flag").toLowerCase();
		Integer topn = Integer.valueOf(request.getParameter("topn"));
		WatchesBusiness wb = new WatchesBusiness();
		PrintWriter out = response.getWriter();
		switch (flag) {
		case "post":
			try {
				ArrayList<Map> results = wb.topN(topn);
				String json = JSON.toJSONString(results);
				out.write(json);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out.write("`服务`繁忙，请稍后重试！");
			}
			break;
		case "author":
			try {
				ArrayList<Map> results = wb.topNAuthor(topn);
				String json = JSON.toJSONString(results);
				out.write(json);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out.write("`服务`繁忙，请稍后重试！");
			}
			break;
		default:
			// 可进行友好提示。。。
			
			out.write("后台尚未拓展您需要的业务，请联系<a href='https://github.com/guoruibiao'>管理员</a>哟！");
			break;
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
