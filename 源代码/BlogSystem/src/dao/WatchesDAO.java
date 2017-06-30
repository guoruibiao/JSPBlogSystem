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
	 * ���ݸ����Ĳ��͵ı�Ż�ȡ���������Ϣ��
	 * 
	 * @param post_id
	 *            ���ͱ��
	 * @return ���ͱ�Ŷ�Ӧ�Ĳ����������Ϣ��
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
			throw new Exception("��ȡ��ƪ���͵������ʧ�ܣ�" + e);
		}
	}

	/**
	 * ÿ��Ϊ�������ͱ�ŵĲ����������һ��
	 * 
	 * @param post_id
	 *            ���ͱ��
	 * @return ���³ɹ�����TRUE������ʧ�ܷ���False��
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
			throw new Exception("Ϊ�����������һ����ʧ�ܣ�" + e);
		} finally {
			return isUpdated;
		}
	}

	/**
	 * ��ȡ�������ߵ�ǰnƪ���¡�
	 * 
	 * @param n
	 *            ���صĽ������Ŀ�������涨Ӧ����>=1����������
	 * @return �Լ�����ʽ��ŵ�map���ݡ�
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

			// ��ʼ���������
			ArrayList<Map> results = new ArrayList<Map>();
			while (rs.next()) {
				Map map = new HashMap();
				map.put("post_id", rs.getObject("post_id"));
				map.put("watches", rs.getObject("watches"));
				map.put("percent", rs.getObject("percental"));

				results.add(map);
			}
			// �Լ�����ʽ���ؽ����
			return results != null ? results : null;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("��ȡ�������ߵ�ǰNƪ����ʧ��!" + e);
		}
	}

	/**
	 * ����վ��׫д����������ǰ�θ�������Ϣ��
	 * 
	 * @param n
	 *            ������n>=0����Ϊ��������
	 * @return �������߱���Լ�����������
	 * @throws Exception
	 */
	public ArrayList<Map> topNAuthor(Integer n) throws Exception {
		try {
			Connection conn = helper.getConnection();
			DbUtils dbUtils = new DbUtils();
			DbHelper.release(conn);
			String sql = "select `user`.id, `user`.name, count(user_id) from post join user on `user`.id = post.user_id group by user_id order by count(user_id) desc limit ?";
			ResultSet rs = dbUtils.find(sql, n);

			// ������ȡ���Ľ��������
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
			throw new Exception("��ȡ��վ׫д����������ǰn��������Ϣʧ�ܣ�" + e);
		}
	}

}
