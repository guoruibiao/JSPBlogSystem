package dao;

import java.sql.Connection;
import java.util.ArrayList;

import bean.Comment;
import bean.Post;
import bean.User;
import dbhelper.DbHelper;
import dbhelper.QueryRunner;
import handlers.BeanHandler;
import handlers.BeanListHandler;

public class PostDAO extends AbstractDAO {

	private DbHelper helper = new DbHelper();

	@SuppressWarnings("finally")
	public boolean add(Post post) throws Exception {
		boolean isAdded = false;
		try {
			Connection conn = helper.getConnection();
			Object[] params = { post.getTitle(), post.getDigest(), post.getContent(), post.getPostdate(),
					post.getUser_id() };
			String sql = "insert into post(title, digest, content, postdate, user_id) values(?, ?, ?, ?, ?)";
			QueryRunner query = new QueryRunner();
			query.update(conn, sql, params);
			DbHelper.release(conn);

			isAdded = true;
		} catch (Exception e) {
			// TODO: handle exception
			// isAdded = false;// 其实这行代码太多余了。。。
			System.out.println("添加博客操作出错，信息为：\n" + e);
			throw new Exception("添加文章出错！" + e);
		} finally {
			return isAdded;
		}
	}

	@SuppressWarnings("finally")
	public boolean delete(Integer post_id) throws Exception {
		boolean isDeleted = false;
		try {
			Connection conn = helper.getConnection();
			String sql = "delete from post where id=?";
			QueryRunner query = new QueryRunner();
			query.update(conn, sql, post_id);
			DbHelper.release(conn);
			isDeleted = true;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("删除文章失败！" + e);
		} finally {
			return isDeleted;
		}
	}

	@SuppressWarnings("finally")
	public boolean update(Integer post_id, Post post) throws Exception {
		boolean isUpdated = false;
		try {
			Connection conn = helper.getConnection();
			String sql = "update post set title=?, digest=?, content=?, postdate=? where id=?";
			Object[] params = { post.getTitle(), post.getDigest(), post.getContent(), post.getPostdate(), post_id };
			QueryRunner query = new QueryRunner();
			query.update(conn, sql, params);
			DbHelper.release(conn);
			isUpdated = true;
		} catch (Exception e) {
			throw new Exception("更新文章出错。" + e);
		} finally {
			return isUpdated;
		}
	}

	public User getAuthor(Integer post_id) throws Exception {

		try {
			Connection conn = helper.getConnection();
			String sql = "select * from user where user.id=(select user_id from post where post.id=?)";
			QueryRunner query = new QueryRunner();
			User user = query.query(conn, sql, new BeanHandler<User>(User.class), post_id);
			DbHelper.release(conn);
			return user != null ? user : null;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("获取博客作者失败！" + e);
		}
	}

	public Post getPost(Integer post_id) throws Exception {
		try {
			Connection conn = helper.getConnection();
			String sql = "select * from post where id=?";
			QueryRunner query = new QueryRunner();
			Post post = query.query(conn, sql, new BeanHandler<Post>(Post.class), post_id);
			DbHelper.release(conn);

			return post != null ? post : null;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("根据博客在数据库内的编号获取博客的详细信息操作失败！" + e);
		}
	}

	public ArrayList<Comment> getCommentsByPost(Integer post_id) throws Exception {
		try {
			Connection conn = helper.getConnection();
			String sql = "select * from comment where post_id=?";
			QueryRunner query = new QueryRunner();
			ArrayList<Comment> comments = (ArrayList<Comment>) query.query(conn, sql,
					new BeanListHandler<Comment>(Comment.class), post_id);
			DbHelper.release(conn);

			return comments != null ? comments : null;
		} catch (Exception e) {
			throw new Exception("根据博客在数据库中的编号获取隶属于该博客的评论信息失败！" + e);
		}
	}

	/**
	 * 返回数据库内所有的文章。
	 * 
	 * @return 数据库内所有用户的文章。
	 * @throws Exception
	 */
	public ArrayList<Post> getAllPosts() throws Exception {
		try {
			Connection conn = helper.getConnection();
			String sql = "select * from post";
			QueryRunner query = new QueryRunner();
			ArrayList<Post> posts = (ArrayList<Post>) query.query(conn, sql, new BeanListHandler<Post>(Post.class));
			DbHelper.release(conn);

			return posts != null ? posts : null;
		} catch (Exception e) {
			throw new Exception("获取数据库内所有文章出错！" + e);
		}
	}

	/**
	 * 根据给定的当前页和页面大小返回相关的数据。
	 * 
	 * @param currpage
	 *            当前页页码
	 * @param next
	 *            是否获取下一页的数据，-1代表上一页，1 代表下一页。
	 * @param pagesize
	 *            页面大小，即每页能包含多少条数据。
	 * @return 小于等于页面大小的数据集合。
	 * @throws Exception
	 */
	public ArrayList<Post> getPagination(Integer currpage, Integer next, Integer pagesize) throws Exception {
		try {
			Connection conn = helper.getConnection();
			String sql = "select * from post limit ?, ?";
			Integer left = ((currpage + next - 1) * pagesize) >= 0 ? ((currpage + next - 1) * pagesize) : 0;

			Object[] params = { left, pagesize };
			QueryRunner query = new QueryRunner();
			ArrayList<Post> posts = (ArrayList<Post>) query.query(conn, sql, new BeanListHandler<Post>(Post.class),
					params);
			DbHelper.release(conn);

			return posts != null ? posts : null;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("文章分页数据获取操作失败！" + e);
		}

	}

}
