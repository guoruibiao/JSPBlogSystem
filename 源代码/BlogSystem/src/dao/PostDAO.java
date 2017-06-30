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
			// isAdded = false;// ��ʵ���д���̫�����ˡ�����
			System.out.println("��Ӳ��Ͳ���������ϢΪ��\n" + e);
			throw new Exception("������³���" + e);
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
			throw new Exception("ɾ������ʧ�ܣ�" + e);
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
			throw new Exception("�������³���" + e);
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
			throw new Exception("��ȡ��������ʧ�ܣ�" + e);
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
			throw new Exception("���ݲ��������ݿ��ڵı�Ż�ȡ���͵���ϸ��Ϣ����ʧ�ܣ�" + e);
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
			throw new Exception("���ݲ��������ݿ��еı�Ż�ȡ�����ڸò��͵�������Ϣʧ�ܣ�" + e);
		}
	}

	/**
	 * �������ݿ������е����¡�
	 * 
	 * @return ���ݿ��������û������¡�
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
			throw new Exception("��ȡ���ݿ����������³���" + e);
		}
	}

	/**
	 * ���ݸ����ĵ�ǰҳ��ҳ���С������ص����ݡ�
	 * 
	 * @param currpage
	 *            ��ǰҳҳ��
	 * @param next
	 *            �Ƿ��ȡ��һҳ�����ݣ�-1������һҳ��1 ������һҳ��
	 * @param pagesize
	 *            ҳ���С����ÿҳ�ܰ������������ݡ�
	 * @return С�ڵ���ҳ���С�����ݼ��ϡ�
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
			throw new Exception("���·�ҳ���ݻ�ȡ����ʧ�ܣ�" + e);
		}

	}

}
