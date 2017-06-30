package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Comment;
import bean.Post;
import bean.User;
import dbhelper.DbHelper;
import dbhelper.QueryRunner;
import handlers.BeanHandler;
import handlers.BeanListHandler;
import utils.DbUtils;
import utils.EncryptUtils;

public class UserDAO extends AbstractDAO {

	public DbHelper helper = new DbHelper();

	/**
	 * �û���¼�жϡ������ⲿ�������ֶν��м��ܴ���
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            ����
	 * @return ����û���������ƥ�䣬�򷵻�TRUE�����򷵻�False��
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public boolean login(String username, String password) throws Exception {
		boolean isLogined = false;
		try {
			password = EncryptUtils.encode(password);
			String sql = "select * from user where name=?";
			QueryRunner query = new QueryRunner();
			Connection conn = helper.getConnection();
			Object[] params = { username };
			User user = query.query(conn, sql, new BeanHandler<User>(User.class), params);
			DbHelper.release(conn);
			if (user.getPassword().equals(password)) {
				isLogined = true;
			}
		} catch (Exception e) {
			throw new Exception("�û���¼����" + e);
		} finally {
			return isLogined;
		}
	}

	/**
	 * �û�ע����ش��������ⲿ���������ֶν��м��ܴ����ײ��Զ�������
	 * 
	 * @param user
	 *            �������û���Ϣ���û�����
	 * @return �ɹ�ע���򷵻�TRUE�����򷵻�False��
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public boolean register(User user) throws Exception {
		boolean isSuccess = false;
		try {
			String username = user.getName();
			String password = user.getPassword();
			String email = user.getEmail();
			Boolean sex = user.getSex();

			password = EncryptUtils.encode(password);

			String sql = "insert into user(name, password, email, sex) values(?, ?, ?, ?)";
			Object[] params = { username, password, email, sex };
			QueryRunner query = new QueryRunner();
			Connection conn = helper.getConnection();
			query.update(conn, sql, params);
			DbHelper.release(conn);

			isSuccess = true;
		} catch (Exception e) {
			isSuccess = false;
			throw new Exception("�û�ע�����" + e);
		} finally {
			return isSuccess;
		}
	}

	public ArrayList<Post> getPosts(String username) throws Exception {
		try {
			Connection conn = helper.getConnection();
			String sql = "select * from post where user_id = (select user.id from user where name=?)";
			QueryRunner query = new QueryRunner();
			ArrayList<Post> posts = (ArrayList<Post>) query.query(conn, sql, new BeanListHandler<Post>(Post.class),
					username);
			DbHelper.release(conn);
			return posts != null ? posts : null;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("��ȡ�û����в�������ʧ�ܣ�" + e);
		}
	}

	public ArrayList<Post> getPostsPagination(String username, Integer currpage, Integer next, Integer pagesize)
			throws Exception {
		try {
			Connection conn = helper.getConnection();
			String sql = "select * from post where user_id = (select user.id from user where name=?) limit ?, ?";
			Integer left = ((currpage + next - 1) * pagesize) >= 0 ? ((currpage + next - 1) * pagesize) : 0;
			Object[] params = {username, left, pagesize };

			QueryRunner query = new QueryRunner();
			ArrayList<Post> posts = (ArrayList<Post>) query.query(conn, sql, new BeanListHandler<Post>(Post.class),
					params);
			DbHelper.release(conn);

			return posts != null ? posts : null;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("��ȡ�û����в�������ʧ�ܣ�" + e);
		}
	}

	public ArrayList<Comment> getComments(String username) throws Exception {
		try {
			Connection conn = helper.getConnection();
			String sql = "select * from comment where user_id=(select id from user where name=?)";
			QueryRunner query = new QueryRunner();
			ArrayList<Comment> comments = (ArrayList<Comment>) query.query(conn, sql,
					new BeanListHandler<Comment>(Comment.class), username);
			DbHelper.release(conn);

			return comments != null ? comments : null;
		} catch (Exception e) {
			throw new Exception("�����û�����ȡ���û�����������ʧ�ܣ�" + e);
		}
	}

	/**
	 * �����û�����ȡ�û�����ϸ��Ϣ��
	 * 
	 * @param username
	 *            �û���
	 * @return �����û�����Ӧ����ϸ�û���Ϣ��
	 * @throws Exception
	 */
	public User getInfo(String username) throws Exception {
		try {
			Connection conn = helper.getConnection();
			String sql = "select * from user where name=?";
			QueryRunner query = new QueryRunner();
			User user = query.query(conn, sql, new BeanHandler<User>(User.class), username);
			DbHelper.release(conn);

			return user != null ? user : null;
		} catch (Exception e) {
			throw new Exception("�����û�����ȡ�û�����ϸ��Ϣ����" + e);
		}
	}

	/**
	 * �����û������ݿ��еı�Ż�ȡ���û�����
	 * 
	 * @param user_id
	 *            �û������ݿ��ж�Ӧ�ı�š�
	 * @return �û���Ŷ�Ӧ���û�����Ϣ��
	 * @throws Exception
	 */
	public String getNameById(Integer user_id) throws Exception {
		try {
			Connection conn = helper.getConnection();
			String sql = "select * from user where id=?";
			QueryRunner query = new QueryRunner();
			User user = query.query(conn, sql, new BeanHandler<User>(User.class), user_id);
			DbHelper.release(conn);

			return user != null ? user.getName() : null;
		} catch (Exception e) {
			throw new Exception("ͨ���û���Ż�ȡ�û�����Ϣ����ʧ�ܣ�" + e);
		}
	}

	/**
	 * ���ݸ������û����жϵ�ǰϵͳ�Ƿ�����ͬ���ֵ��û����ڡ�
	 * 
	 * @param username
	 *            �����Ĵ�ע���û�����
	 * @return ���ϵͳ�ڲ����ڴ��û������򷵻�true�����ϵͳ���Ѵ���ͬ���û�������false��
	 * @throws Exception
	 */
	public boolean validate(String username) throws Exception {
		try {
			Connection conn = helper.getConnection();
			String sql = "select count(*) from user where name=?";
			DbUtils db = new DbUtils();
			ResultSet rs = db.find(sql, username);
			while (rs.next()) {
				return rs.getInt(1) == 0;
			}
			rs.close();
			DbHelper.release(conn);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("��ѯϵͳ������ͬ���û�����ʧ�ܣ�" + e);
		}

		return false;
	}

	// TODO �û���Ϣ�޸���صĲ�������

}
