package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbhelper.DbHelper;

/**
 * �ֲ�DbHelperȱʧ��ԭ��SQL��䴦��
 * 
 * @author biao
 *
 */
public class DbUtils {
	private DbHelper helper = new DbHelper();

	public ResultSet find(String sql, Object... params) throws Exception {
		try {
			Connection conn = helper.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					// PreparedStatement�����׳����һ�㣬�Ǿ������õ�ʱ���±��1��ʼ���㡣
					ps.setObject((i+1), params[i]);
				}
			}
			ResultSet rs = ps.executeQuery();
			DbHelper.release(conn);

			return rs != null ? rs : null;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("ִ��DbUtils����ԭ��SQL����" + e);
		}
	}

}
