package biz;

import java.util.ArrayList;
import java.util.Date;


import org.junit.Test;

import bean.Post;
import bean.Tag;

public class CommonBusinessTest {

	@Test
	public void testLogin() throws Exception {
		String username = "root";
		String password = "root";
		CommonBusiness cb = new CommonBusiness();
		boolean isLogined = cb.login(username, password);
		System.out.println(isLogined);
	}

	@Test
	public void testRegister() throws Exception {
		String username = "root";
		String password = "root";
		String email = "1064319632@qq.com";
		boolean sex = true;

		CommonBusiness cb = new CommonBusiness();
		boolean isRegistered = cb.register(username, password, email, sex);
		System.out.println(isRegistered);
	}
	
	@Test
	public void testPublishPost() throws Exception {
		Post post = new Post();
		post.setTitle("CommonBusiness文章发表测试");
		String content = "CommonBusiness 是针对于普遍的业务需求而设计的一个业务类，里面包含了常见的关于一个博客系统的最基本功能的相关方法，当然了，仅仅有这个是远远不够的，还需要很多其他的业务类处理，用得到的时候再进行增加吧。";
		post.setDigest(content.substring(0, 28));
		post.setContent(content);
		post.setPostdate(new Date());
		post.setUser_id(3);
		
		CommonBusiness cb = new CommonBusiness();
		boolean isPublished = cb.publishPost(post);
		System.out.println(isPublished);
	}

	
	@Test
	public void testGetTagsByPost() throws Exception {
		Integer post_id = 14;
		CommonBusiness cb = new CommonBusiness();
		ArrayList<Tag> tags = cb.getTagsByPost(post_id);
		System.out.println("可以到这里！");
		for(Tag tag: tags) {
			System.out.println(tag.toString());
		}
	}
}
