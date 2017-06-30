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
		// ���ǰ�˵�ajax����Ϊecharts׼��ͼ�����ݡ�
		/**
		 * �涨flagĿǰ������ֵ�����������ʹ��article���棻��������ʹ��author���档 �������������Ϊ��չ�
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
				out.write("`����`��æ�����Ժ����ԣ�");
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
				out.write("`����`��æ�����Ժ����ԣ�");
			}
			break;
		default:
			// �ɽ����Ѻ���ʾ������
			
			out.write("��̨��δ��չ����Ҫ��ҵ������ϵ<a href='https://github.com/guoruibiao'>����Ա</a>Ӵ��");
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
