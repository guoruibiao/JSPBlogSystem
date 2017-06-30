package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biz.WatchesBusiness;

/**
 * Servlet implementation class IncreWatchesServlet
 */
@WebServlet("/IncreWatchesServlet")
public class IncreWatchesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IncreWatchesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Integer post_id = Integer.valueOf(request.getParameter("post_id"));
		WatchesBusiness wb = new WatchesBusiness();
		
		try {
			boolean isUpdated = wb.increWatches(post_id);
			String msg = "";
			if(isUpdated) {
				msg = "Watches updated success.";
			}else{
				msg = "Watches updated failed.";
			}
			
			response.getWriter().write(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
