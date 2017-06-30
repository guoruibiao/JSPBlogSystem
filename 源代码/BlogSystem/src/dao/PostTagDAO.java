package dao;

import java.sql.Connection;
import java.util.ArrayList;

import bean.Post;
import bean.Tag;
import dbhelper.DbHelper;
import dbhelper.QueryRunner;
import handlers.BeanListHandler;

public class PostTagDAO extends AbstractDAO {

	private DbHelper helper = new DbHelper();

	public ArrayList<Post> getPosts(Integer tag_id) throws Exception {
		try {
			Connection conn = helper.getConnection();
			String sql = "select * from post where id in (select post_id from post_has_tag where tag_id=?)";
			QueryRunner query = new QueryRunner();
			ArrayList<Post> posts = (ArrayList<Post>) query.query(conn, sql, new BeanListHandler<Post>(Post.class),
					tag_id);
			DbHelper.release(conn);

			return posts != null ? posts : null;
		} catch (Exception e) {
			throw new Exception("通过标签获取该标签下的所有文章失败！" + e);
		}
	}

	public ArrayList<Tag> getTags(Integer post_id) throws Exception {
		try {
			Connection conn = helper.getConnection();
			String sql = "select * from tag where tag.id in (select tag_id from post_has_tag where post_id=?)";
			QueryRunner query = new QueryRunner();
			ArrayList<Tag> tags = (ArrayList<Tag>) query.query(conn, sql, new BeanListHandler<Tag>(Tag.class), post_id);
			DbHelper.release(conn);

			return tags != null ? tags : null;
		} catch (Exception e) {
			throw new Exception("根据文章的编号查询隶属于该文章的标签失败！" + e);
		}
	}

}
