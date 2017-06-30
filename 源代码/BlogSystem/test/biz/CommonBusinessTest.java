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
		post.setTitle("CommonBusiness���·������");
		String content = "CommonBusiness ��������ձ��ҵ���������Ƶ�һ��ҵ���࣬��������˳����Ĺ���һ������ϵͳ����������ܵ���ط�������Ȼ�ˣ������������ԶԶ�����ģ�����Ҫ�ܶ�������ҵ���ദ���õõ���ʱ���ٽ������Ӱɡ�";
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
		System.out.println("���Ե����");
		for(Tag tag: tags) {
			System.out.println(tag.toString());
		}
	}
}
