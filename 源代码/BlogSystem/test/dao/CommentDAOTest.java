package dao;

import java.util.Date;

import org.junit.Test;

import bean.Comment;
import bean.Post;
import bean.User;

public class CommentDAOTest {

	private CommentDAO commentDAO = new CommentDAO();
	
	@Test
	public void testAdded() throws Exception {
		Comment comment = new Comment();
		comment.setContent("这篇博客的内容还不错哈");
		comment.setCommenttime(new Date());
		comment.setUser_id(1);
		comment.setPost_id(2);
		
		boolean isAdded = commentDAO.add(comment);
		System.out.println(isAdded);
	}
	
	@Test
	public void testUpdated() throws Exception {
		Comment comment = new Comment();
		Integer comment_id = 2;
		comment.setContent("这是更新后的评论内容！");
		boolean isUpdated = commentDAO.update(comment_id, comment);
		System.out.println(isUpdated);
	}
	
	@Test
	public void testDeleted() throws Exception {
		Integer comment_id = 4;
		boolean isDeleted = commentDAO.delete(comment_id);
		System.out.println(isDeleted);
	}
	
	
	@Test
	public void testGetAuthor() throws Exception {
		Integer comment_id = 2;
		User user = (User) commentDAO.getAuthor(comment_id);
		System.out.println(user.toString());
	}
	
	@Test
	public void testGetPost() throws Exception {
		Integer comment_id = 2;
		Post post = commentDAO.getPost(comment_id);
		System.out.println(post.toString());
	}
	
	
}
