package dao;

import java.util.ArrayList;

import org.junit.Test;

import bean.Comment;
import bean.Post;
import bean.User;

public class UserDAOTest {

	private UserDAO userDAO = new UserDAO();

	@Test
	public void testLogin() throws Exception {
		String username = "root";
		String password = "root";
		boolean isLogined = userDAO.login(username, password);
		System.out.println(isLogined);
	}

	@Test
	public void testRegister() throws Exception {
		User user = new User();
		user.setName("小丽");
		user.setPassword("root");
		user.setEmail("marksinoberg@gmail.com");
		user.setSex(false);

		boolean isRegistered = userDAO.register(user);
		System.out.println(isRegistered);

	}

	@Test
	public void testGetPosts() throws Exception {
		ArrayList<Post> posts = userDAO.getPosts("小丽");
		for (Post post : posts) {
			System.out.println(post.toString());
		}

	}

	@Test
	public void testGetComments() throws Exception {
		String username = "小丽";
		ArrayList<bean.Comment> comments = userDAO.getComments(username);
		for (Comment comment : comments) {
			System.out.println(comment.toString());
		}
	}

	@Test
	public void testGetInfo() throws Exception {
		String username = "小丽";
		User user = userDAO.getInfo(username);
		System.out.println(user.toString());
	}

	@Test
	public void testGetNameById() throws Exception {
		Integer user_id = 1;
		String username = userDAO.getNameById(user_id);
		System.out.println(username);
	}

	@Test
	public void testValidate() throws Exception {
		String username = "fuck";
		boolean isExists = userDAO.validate(username);
		System.out.println(isExists);
	}

	@Test
	public void testGetPostsPagination() throws Exception {
		Integer currpage = 1;
		Integer next = 0;
		Integer pagesize = 3;

		ArrayList<Post> posts = userDAO.getPostsPagination("root", currpage, next, pagesize);
		for (Post post : posts) {
			System.out.println(post.toString());
		}
	}

}
