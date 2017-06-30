package service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import stringutil.StringUtils;

/**
 * Servlet implementation class UploadHandlerServlet
 */
@WebServlet("/UploadHandlerServlet")
public class UploadHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadHandlerServlet() {
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
		request.setCharacterEncoding("UTF-8");
		String uploadfolder = "E:\\Code\\jee\\sshm-maven\\BlogSystem\\WebContent\\upload\\";
		String username = (String) request.getSession().getAttribute("username");
		System.out.println("��ǰ��¼�û���" + username);
		String savedPath = uploadfolder + username;
		// �ж��ļ�·���Ƿ����
		File file = new File(savedPath);
		if (!file.exists()) {
			System.out.println("��ʼ����Ŀ¼��");
			file.mkdir();
		}
		// ����һ���ַ���������ʶ�ϴ��ɹ����
		String msg = "";
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload uploadparser = new ServletFileUpload(factory);
			uploadparser.setHeaderEncoding("UTF-8");
			if (!ServletFileUpload.isMultipartContent(request)) {
				// ���Ǳ����ݣ����մ�ͳ�ķ�ʽ��ȡ����
				return;
			}
			// 4��ʹ��ServletFileUpload�����������ϴ������ݣ�����������ص���һ��List<File>���ϣ�ÿ��Item��Ӧһ������������
			List<FileItem> files = uploadparser.parseRequest(request);
			for (FileItem fileitem : files) {
				// ���fileitem�з�װ������ͨ�������������
				if (fileitem.isFormField()) {
					String name = fileitem.getFieldName();
					// �����ͨ����������ݵ�������������
					String value = fileitem.getString("UTF-8");
					System.out.println(name + "  =   " + value);
				} else {// ���fileitem�����װ�����ϴ����ļ��������ô����ļ��ķ�ʽ����
					String filename = fileitem.getName();
					System.out.println(fileitem);
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					// ע�⣺��ͬ��������ύ���ļ����ǲ�һ���ģ���Щ������ύ�������ļ��Ǵ�����ͻ��˱���·���ģ���Щ�򲻴�����������Ҫ���ϴ��ļ�����ֻ�õ��ļ����Ƽ���
					filename = StringUtils.getFileName(filename);
					// ��ȡfileitem���ϴ��ļ���������
					InputStream is = fileitem.getInputStream();
					// ����һ���ļ������
					File fullpath = new File(savedPath + "\\" + username + ".jpg");
					FileOutputStream fos = new FileOutputStream(fullpath);
					// ����һ��������
					byte[] buffer = new byte[1024];
					// �ж��������е������Ƿ��Ѿ�����ı�ʶ
					int len = 0;
					while ((len = is.read(buffer)) > 0) {
						// ������д�뵽�������Ķ�Ӧ���ļ���
						fos.write(buffer, 0, len);
					}
					is.close();
					fos.close();
					fileitem.delete();
					msg = new String("Upload Success!".getBytes(), "UTF-8");

				}
			}

		} catch (Exception e) {
			msg = new String("Upload Failed!".getBytes(), "UTF-8") + e;
			e.printStackTrace();
		} finally {
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect("info.jsp");
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
