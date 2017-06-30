package db;

import java.sql.Connection;

import org.junit.BeforeClass;
import org.junit.Test;

import bean.User;
import dbhelper.DbHelper;
import dbhelper.QueryRunner;
import handlers.BeanHandler;

public class TestDbUtils {
	
	@BeforeClass
	public static void setup() throws Exception {
		DbHelper.register();
	}
	
	@Test
	public void testConnect() throws Exception {
//		DbHelper.register();
		
		String sql = "select * from user where id=?";
		DbHelper helper = new DbHelper();
		
		Connection conn = helper.getConnection();
		QueryRunner query = new QueryRunner();
		User user = query.query(conn, sql, new BeanHandler<User>(User.class), 1);
		System.out.println(user.toString());
		
	}

}
