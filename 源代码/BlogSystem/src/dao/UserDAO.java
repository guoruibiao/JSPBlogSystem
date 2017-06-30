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
	 * 用户登录判断。无需外部对密码字段进行加密处理。
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return 如果用户名与密码匹配，则返回TRUE，否则返回False。
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
			throw new Exception("用户登录出错。" + e);
		} finally {
			return isLogined;
		}
	}

	/**
	 * 用户注册相关处理。无需外部对于密码字段进行加密处理，底层自动化处理。
	 * 
	 * @param user
	 *            包含了用户信息的用户对象。
	 * @return 成功注册则返回TRUE，否则返回False。
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
			throw new Exception("用户注册出错！" + e);
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
			throw new Exception("获取用户所有博客内容失败！" + e);
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
			throw new Exception("获取用户所有博客内容失败！" + e);
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
			throw new Exception("根据用户名获取该用户的所有评论失败！" + e);
		}
	}

	/**
	 * 根据用户名获取用户的详细信息。
	 * 
	 * @param username
	 *            用户名
	 * @return 返回用户名对应的详细用户信息。
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
			throw new Exception("根据用户名获取用户的详细信息出错。" + e);
		}
	}

	/**
	 * 根据用户在数据库中的编号获取其用户名。
	 * 
	 * @param user_id
	 *            用户在数据库中对应的编号。
	 * @return 用户编号对应的用户名信息。
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
			throw new Exception("通过用户编号获取用户名信息操作失败！" + e);
		}
	}

	/**
	 * 根据给定的用户名判断当前系统是否有相同名字的用户存在。
	 * 
	 * @param username
	 *            给定的待注册用户名。
	 * @return 如果系统内不存在此用户名，则返回true；如果系统内已存在同名用户，返回false。
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
			throw new Exception("查询系统内有无同名用户操作失败！" + e);
		}

		return false;
	}

	// TODO 用户信息修改相关的操作处理。

}
