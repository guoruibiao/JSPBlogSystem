package dao;

import java.util.ArrayList;

import org.junit.Test;

import bean.Tag;

public class TagDAOTest {

	private TagDAO tagDAO = new TagDAO();

	@Test
	public void testAdd() throws Exception {
		Tag tag = new Tag();
		tag.setName("PHP");

		boolean isAdded = tagDAO.add(tag);
		System.out.println(isAdded);
	}
	
	@Test
	public void testDeleted() throws Exception {
		// 测试通过tag_id来删除标签
//		Integer tag_id = 1;
//		boolean isDeleted = tagDAO.delete(tag_id);
//		System.out.println(isDeleted);
		
	    // 测试通过标签名称来删除标签
		String tagname = "Java开发";
		boolean isDeleted = tagDAO.delete(tagname);
		System.out.println(isDeleted);
	}
	
	
	@Test
	public void testUpdate() throws Exception {
		String tagname = "Java";
		Integer tag_id = 3;
		Tag tag = new Tag();
		tag.setName(tagname);
		boolean isUpdated = tagDAO.update(tag_id, tag);
		System.out.println(isUpdated);
		
	}
	
	@Test
	public void testGetAllTags() throws Exception {
		ArrayList<Tag> tags = tagDAO.getAllTags();
		for(Tag tag: tags) {
			System.out.println(tag.toString());
		}
	}

}
