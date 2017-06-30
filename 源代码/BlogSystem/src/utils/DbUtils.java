package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbhelper.DbHelper;

/**
 * 弥补DbHelper缺失的原生SQL语句处理。
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
					// PreparedStatement很容易出错的一点，那就是设置的时候下标从1开始计算。
					ps.setObject((i+1), params[i]);
				}
			}
			ResultSet rs = ps.executeQuery();
			DbHelper.release(conn);

			return rs != null ? rs : null;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("执行DbUtils操作原生SQL出错！" + e);
		}
	}

}
