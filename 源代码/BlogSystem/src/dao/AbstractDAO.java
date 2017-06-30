package dao;

import dbhelper.DbHelper;

/**
 * DAO抽象类，用于数据库配置的初始化设置。
 * @author biao
 *
 */
public class AbstractDAO {

	/**
	 * 采用静态代码块的形式结合抽象类，来保证每一个DAO的实现类都能及时加载数据库相关的驱动。
	 */
	static {
		try {
			// 加载数据库配置内容。
			DbHelper.register();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// TODO 对于每一个抽象类的实现类而言，每一个操作并不是十分安全的，最好是在finally块中再次释放conn对象，进行二次验证。
	
}
