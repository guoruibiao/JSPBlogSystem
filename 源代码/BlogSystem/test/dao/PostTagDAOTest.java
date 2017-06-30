package dao;

import java.util.ArrayList;

import org.junit.Test;

import bean.Post;
import bean.Tag;

public class PostTagDAOTest {
	
	private PostTagDAO postTagDAO = new PostTagDAO();	
	
	@Test
	public void testGetPosts() throws Exception {
		System.out.println("¿ªÊ¼²éÑ¯...");
		Integer tag_id = 3;
		ArrayList<Post> posts = postTagDAO.getPosts(tag_id);
		for(Post post: posts) {
			System.out.println(post.toString());
		}
	}
	
	@Test
	public void testGetTags() throws Exception {
		Integer post_id = 2;
		ArrayList<Tag> tags = postTagDAO.getTags(post_id);
		for(Tag tag: tags) {
			System.out.println(tag.toString());
		}
	}

}
