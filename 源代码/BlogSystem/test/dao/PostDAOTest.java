package dao;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import bean.Comment;

import bean.Post;
import bean.User;

public class PostDAOTest {

	PostDAO postDAO = new PostDAO();

	@Test
	public void addTest() {
		Post post = new Post();
		post.setTitle("JSTL����");
		String content = "JSTLȫ��ΪJavaServer Pages Standard Tag Library��JSTL����JCP(Java Community Process)���ƶ��ı�׼�淶������Ҫ�ṩ��Java Web������Աһ����׼ͨ�õı�ǩ�����⡣";
		post.setDigest(content.substring(0, 28));
		post.setContent(content);
		Date postdate = new Date();
		post.setPostdate(postdate);
		post.setUser_id(2);

		System.out.println(post.toString());

		boolean isAdded;
		try {
			isAdded = postDAO.add(post);
			System.out.println(isAdded);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void updateTest() throws Exception {
		Post post = new Post();
		String content = "JSTLȫ��ΪJavaServer Pages Standard Tag Library��JSTL����JCP(Java Community Process)���ƶ��ı�׼�淶������Ҫ�ṩ��Java Web������Աһ����׼ͨ�õı�ǩ�����⡣";
		post.setTitle("���º�ı��ⲿ��");
		post.setDigest(content.substring(0, 28));
		post.setContent(content);
		post.setPostdate(new Date());
		post.setUser_id(1);

		boolean isUpdated = postDAO.update(3, post);
		System.out.println(isUpdated);
	}

	@Test
	public void testGetAuthor() throws Exception {
		Integer post_id = 3;
		User user = postDAO.getAuthor(post_id);
		System.out.println(user.toString());
	}

	@Test
	public void testDelete() throws Exception {
		Integer post_id = 1;
		boolean isDeleted = postDAO.delete(post_id);
		System.out.println(isDeleted);
	}

	@Test
	public void testGetPost() throws Exception {
		Integer post_id = 11;
		Post post = postDAO.getPost(post_id);
		System.out.println(post.toString());
	}

	@Test
	public void testGetComments() throws Exception {
		Integer post_id = 11;
		ArrayList<bean.Comment> comments = postDAO.getCommentsByPost(post_id);
		for (Comment comment : comments) {
			System.out.println(comment.toString());
		}
	}

	@Test
	public void testGetAllPosts() throws Exception {
		ArrayList<Post> posts = postDAO.getAllPosts();
		for (Post post : posts) {
			System.out.println(post.toString());
		}
	}

	@Test
	public void testGetPagination() throws Exception {
		Integer currpage = 2;
		Integer next = 0;
		Integer pagesize = 3;

		ArrayList<Post> posts = postDAO.getPagination(currpage, next, pagesize);
		for (Post post : posts) {
			System.out.println(post.toString());
		}
	}
}
