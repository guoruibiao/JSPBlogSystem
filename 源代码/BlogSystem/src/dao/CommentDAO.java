package dao;

import java.sql.Connection;

import bean.Comment;
import bean.Post;
import bean.User;
import dbhelper.DbHelper;
import dbhelper.QueryRunner;
import handlers.BeanHandler;

/**
 * 评论相关DAO实现。
 * @author biao
 *
 */
public class CommentDAO extends AbstractDAO {

	/**
	 * 数据库操作工具类。
	 */
	private DbHelper helper = new DbHelper();

	@SuppressWarnings("finally")
	public boolean add(Comment comment) throws Exception {
		boolean isAdded = false;
		try {
			Connection conn = helper.getConnection();
			String sql = "insert into comment(content, commenttime, user_id, post_id)" + "values(?, ?, ?, ?)";
			Object[] params = { comment.getContent(), comment.getCommenttime(), comment.getUser_id(),
					comment.getPost_id() };
			QueryRunner query = new QueryRunner();
			query.update(conn, sql, params);
			DbHelper.release(conn);

			isAdded = true;
		} catch (Exception e) {
			throw new Exception("添加评论失败！" + e);
		} finally {
			return isAdded;
		}
	}

	@SuppressWarnings("finally")
	public boolean delete(Integer comment_id) throws Exception {
		boolean isDeleted = false;
		try {
			Connection conn = helper.getConnection();
			String sql = "delete from comment where id=?";
			QueryRunner query = new QueryRunner();
			query.update(conn, sql, comment_id);
			DbHelper.release(conn);

			isDeleted = true;
		} catch (Exception e) {
			throw new Exception("删除评论内容失败！" + e);
		} finally {
			return isDeleted;
		}
	}

	@SuppressWarnings("finally")
	public boolean update(Integer comment_id, Comment comment) throws Exception {
		boolean isUpdated = false;
		try {
			Connection conn = helper.getConnection();
			String sql = "update comment set content=? where id=?";
			Object[] params = { comment.getContent(), comment_id };
			QueryRunner query = new QueryRunner();
			query.update(conn, sql, params);
			DbHelper.release(conn);

			isUpdated = true;
		} catch (Exception e) {
			throw new Exception("更新评论内容失败！" + e);
		} finally {
			return isUpdated;
		}
	}

	public User getAuthor(Integer comment_id) throws Exception {
		try {
			Connection conn = helper.getConnection();
			String sql = "select * from user where user.id=(select user_id from comment where comment.id=?)";
			QueryRunner query = new QueryRunner();
			User user = query.query(conn, sql, new BeanHandler<User>(User.class), comment_id);
			DbHelper.release(conn);
			return user != null ? user : null;
		} catch (Exception e) {
			throw new Exception("通过评论获取对应的作者失败！" + e);
		}
	}

	public Post getPost(Integer comment_id) throws Exception {
		try {
			Connection conn = helper.getConnection();
			String sql = "select * from post where post.id=(select post_id from comment where comment.id=?)";
			QueryRunner query = new QueryRunner();
			Post post = query.query(conn, sql, new BeanHandler<Post>(Post.class), comment_id);
			DbHelper.release(conn);

			return post != null ? post : null;
		} catch (Exception e) {
			throw new Exception("通过评论获取对应的文章失败！" + e);
		}
	}

}
