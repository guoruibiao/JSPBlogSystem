package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bean.Watches;
import dbhelper.DbHelper;
import dbhelper.QueryRunner;
import handlers.BeanHandler;
import utils.DbUtils;

public class WatchesDAO extends AbstractDAO {

	private DbHelper helper = new DbHelper();

	/**
	 * 根据给定的博客的编号获取其浏览量信息。
	 * 
	 * @param post_id
	 *            博客编号
	 * @return 博客编号对应的博文浏览量信息。
	 * @throws Exception
	 */
	public Integer getWatches(Integer post_id) throws Exception {
		try {
			Connection conn = helper.getConnection();
			String sql = "select * from watches where post_id=?";
			QueryRunner query = new QueryRunner();
			Watches watches = query.query(conn, sql, new BeanHandler<Watches>(Watches.class), post_id);
			DbHelper.release(conn);

			return watches != null ? watches.getWatches() : 0;
		} catch (Exception e) {
			throw new Exception("获取单篇博客的浏览量失败！" + e);
		}
	}

	/**
	 * 每次为给定博客编号的博客浏览量加一。
	 * 
	 * @param post_id
	 *            博客编号
	 * @return 更新成功返回TRUE，更新失败返回False。
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public boolean increWatches(Integer post_id) throws Exception {
		boolean isUpdated = false;
		try {
			Connection conn = helper.getConnection();
			String sql = "update watches set watches=watches+1 where post_id=?";
			QueryRunner query = new QueryRunner();
			query.update(conn, sql, post_id);
			DbHelper.release(conn);

			isUpdated = true;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("为博客浏览量加一操作失败！" + e);
		} finally {
			return isUpdated;
		}
	}

	/**
	 * 获取浏览量最高的前n篇文章。
	 * 
	 * @param n
	 *            返回的结果集条目数，按规定应该是>=1的正整数。
	 * @return 以集合形式存放的map数据。
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Map> topN(Integer n) throws Exception {
		try {
			Connection conn = helper.getConnection();
			String sql = "select post_id, watches,  watches/(select sum(watches) from watches) as percental from watches group by watches desc limit ?";
			DbUtils dbUtils = new DbUtils();
			ResultSet rs = dbUtils.find(sql, n);
			DbHelper.release(conn);

			// 开始解析结果集
			ArrayList<Map> results = new ArrayList<Map>();
			while (rs.next()) {
				Map map = new HashMap();
				map.put("post_id", rs.getObject("post_id"));
				map.put("watches", rs.getObject("watches"));
				map.put("percent", rs.getObject("percental"));

				results.add(map);
			}
			// 以集合形式返回结果集
			return results != null ? results : null;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("获取浏览量最高的前N篇文章失败!" + e);
		}
	}

	/**
	 * 返回站内撰写文章数最多的前Ｎ个作者信息。
	 * 
	 * @param n
	 *            理论上n>=0，且为正整数。
	 * @return 返回作者编号以及其文章数。
	 * @throws Exception
	 */
	public ArrayList<Map> topNAuthor(Integer n) throws Exception {
		try {
			Connection conn = helper.getConnection();
			DbUtils dbUtils = new DbUtils();
			DbHelper.release(conn);
			String sql = "select `user`.id, `user`.name, count(user_id) from post join user on `user`.id = post.user_id group by user_id order by count(user_id) desc limit ?";
			ResultSet rs = dbUtils.find(sql, n);

			// 解析获取到的结果及数据
			ArrayList<Map> results = new ArrayList<Map>();
			while (rs.next()) {
				Map map = new HashMap();
				map.put("user_id", rs.getInt(1));
				map.put("name", rs.getString("name"));
				map.put("post_number", rs.getObject(3));

				results.add(map);
			}

			return results.size() != 0 ? results : null;
		} catch (Exception e) {
			throw new Exception("获取本站撰写文章数最多的前n个博主信息失败！" + e);
		}
	}

}
