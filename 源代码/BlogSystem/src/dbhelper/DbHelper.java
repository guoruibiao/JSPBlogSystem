package dbhelper;

import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import utils.XMLUtils;

public class DbHelper implements DataSource {

	/**
	 * 鐢ㄤ簬鏆傛椂鐨勫瓨鍌ㄦ暟鎹簱淇℃伅鐨凪ap
	 */
	private static Map<String, String> map = new HashMap<String, String>();

	/*
	 * 鏃㈢劧鏄竴涓暟鎹簱杩炴帴姹狅紝灏卞繀椤绘湁璁稿鐨勮繛鎺ワ紝鎵�浠ラ渶瑕佷娇鐢ㄤ竴涓泦鍚堢被淇濆瓨杩欎簺杩炴帴 (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#getLogWriter()
	 */
	public static LinkedList<Connection> connpool = new LinkedList<Connection>();

	/**
	 * 閫氳繃XMLUtils瀹炵幇瀵归厤缃枃浠剁殑淇℃伅璇诲彇锛屽苟瀹炵幇DriverManager鐨勪娇鐢�
	 * 
	 * @throws Exception
	 */
	static {
		try {
			Document document = XMLUtils.getDocument();
			NodeList nodes = document.getElementsByTagName("database");
			for (int i = 0; i < nodes.getLength(); i++) {
				Element element = (Element) nodes.item(i);
				switch (element.getAttribute("name")) {
				case "mysql":
					NodeList childs = element.getChildNodes();
					for (int j = 0; j < childs.getLength(); j++) {
						Node node = childs.item(j);
						String nodename = node.getNodeName();
						String nodevalue = node.getTextContent();
						map.put(nodename, nodevalue);
					}
					break;
				case "sqlserver":
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * 棰勫厛娉ㄥ唽鏁版嵁婧� 锛屼繚璇佹暟鎹彲浠ユ甯哥殑鑾峰彇
	 * 
	 * @throws Exception
	 */
	public static void register() throws Exception {
//		String DRIVER = map.get("driver");
//		String URL = map.get("url");
//		String USER = map.get("user");
//		String PASSWORD = map.get("password");
//		Integer DATABASE_CONNECTION_POOL_SIZE = Integer.valueOf(map.get("poolsize"));
		String DRIVER = "com.mysql.jdbc.Driver";
		String URL = "jdbc:mysql://localhost:3306/blogsystem";
		String USER = "root";
		String PASSWORD = "mysql";
		Integer DATABASE_CONNECTION_POOL_SIZE = 20;
		System.out.println(map.toString());
		System.out.println("db pool size: "+map.get("poolsize"));
		// 棰勫厛 娣诲姞椹卞姩淇℃伅
		Class.forName(DRIVER);

		for (int index = 0; index < DATABASE_CONNECTION_POOL_SIZE; index++) {
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			connpool.add(conn);
		}
	}

	/**
	 * 閲婃斁鏁版嵁搴撹繛鎺ュ璞�
	 * 
	 * @param conn
	 *            缁欏畾鐨勬暟鎹簱杩炴帴瀵硅薄
	 */
	public static void release(Connection conn) {
		try {
			if (conn != null) {
				connpool.add(conn);
			}
		} catch (Exception e) {
			throw new RuntimeException("閲婃斁鏁版嵁搴撹繛鎺ュ璞℃椂鍑洪敊锛� :\n" + e);
		}
	}

	/**
	 * 閲婃斁鏁版嵁搴撹繛鎺ュ璞′互鍙婃暟鎹簱鏌ヨ瀵硅薄
	 * 
	 * @param conn
	 *            鏁版嵁搴撹繛鎺ュ璞�
	 * @param stmt
	 *            鏁版嵁搴撴煡璇㈣鍙�
	 */
	public static void release(Connection conn, Statement stmt) {

		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (Exception e) {
			throw new RuntimeException("閲婃斁鏁版嵁搴撴煡璇㈠璞tatement鏃跺嚭閿欙紒 :\n" + e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		release(conn);
	}

	public static void release(Connection conn, Statement stmt, ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (Exception e) {
			throw new RuntimeException(" 鍏抽棴鏁版嵁搴撶粨鏋滈泦瀵硅薄鏃跺嚭閿欙紒:\n" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (Exception e) {
			throw new RuntimeException("閲婃斁鏁版嵁搴撴煡璇㈠璞tatement鏃跺嚭閿欙紒 :\n" + e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		release(conn);
	}

	/**
	 * 浠庢暟鎹簱杩炴帴姹犱腑鑾峰彇鏁版嵁搴撹繛鎺ュ璞★紝鍚屾椂缁存姢濂芥睜鐨勫唴瀹瑰疄鏃舵洿鏂�
	 */
	@Override
	public Connection getConnection() throws SQLException {

		if (connpool.size() <= 0) {
			throw new RuntimeException("鏁版嵁搴撳繖锛岃寰呬細鍐嶈瘯璇曞惂锛�");
		}
		// 闇�瑕佹敞鎰忕殑鏄紝涓嶈兘浣跨敤get鏂瑰紡锛堣繖涓柟娉曠煡璇嗚繑鍥炰竴涓紩鐢ㄨ�屽凡锛�,
		// 搴旇鍦ㄨ幏鍙栫殑鍚屾椂锛屽垹闄ゆ帀杩欎釜閾炬帴锛屼箣鍚庡啀杩樺洖鏉�.鐜板湪娉ㄦ剰鏄繑杩樼粰鏁版嵁搴撹繛鎺ユ睜锛侊紒锛�
		Connection conn = connpool.removeFirst();
		MyConnection myconn = new MyConnection(conn);

		// 浠庤繖閲屽紑濮嬭繑鍥炵殑灏辨槸涓�涓暟鎹簱杩炴帴姹犲璞＄殑conn
		return myconn;
	}

	///////////////////////////////////////////////////////////////////////// datasource鎺ュ彛鐨勫疄鐜版柟娉曞紑濮�

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	///////////////////////////////////////////////////////////////////////// datasource鎺ュ彛鐨勫疄鐜版柟娉曠粨鏉�

	/**
	 * 鍖呰璁捐妯″紡瀹炵幇娴佺▼锛� 1.鍒涘缓涓�涓被锛屽疄鐜颁笌琚寮哄璞＄浉鍚岀殑鎺ュ彛 2.灏嗚澧炲己瀵硅薄褰撳仛鑷畾涔夌被鐨勪竴涓垚鍛樺彉閲�
	 * 3.瀹氫箟涓�涓瀯閫犳柟娉曪紝灏嗚澧炲己瀵硅薄浼犻�掕繘鍘� 4.澧炲己鎯宠澧炲己鐨勬柟娉曪紝杩涜瑕嗙洊鍗冲彲 5.瀵逛簬涓嶅儚琚寮虹殑鏂规硶锛岃皟鐢ㄨ澧炲己瀵硅薄鐨勬柟娉曡繘琛屽鐞嗗嵆鍙�
	 * 
	 * @author 閮挒
	 *
	 */

	/////////////////////////////////////////////////////////////////////// 浣跨敤鍖呰璁捐妯″紡锛屽寮篶lose鏂规硶鐨勮嚜瀹氫箟绫诲紑濮�
	class MyConnection implements Connection {

		private Connection conn;

		public MyConnection(Connection conn) {
			this.conn = conn;
		}

		/**
		 * 鑷畾涔夌殑鍖呰璁捐妯″紡绫伙紝澧炲己close鏂规硶锛� 灏嗘暟鎹簱閾炬帴璧勬簮杩旇繕缁欐暟鎹簱杩炴帴姹狅紝鑰屼笉鏄暟鎹簱
		 */
		public void close() {
			connpool.add(conn);
		}

		@Override
		public <T> T unwrap(Class<T> iface) throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.unwrap(iface);
		}

		@Override
		public boolean isWrapperFor(Class<?> iface) throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.isWrapperFor(iface);
		}

		@Override
		public Statement createStatement() throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.createStatement();
		}

		@Override
		public PreparedStatement prepareStatement(String sql) throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.prepareStatement(sql);
		}

		@Override
		public CallableStatement prepareCall(String sql) throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.prepareCall(sql);
		}

		@Override
		public String nativeSQL(String sql) throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.nativeSQL(sql);
		}

		@Override
		public void setAutoCommit(boolean autoCommit) throws SQLException {
			// TODO Auto-generated method stub
			this.conn.setAutoCommit(autoCommit);
		}

		@Override
		public boolean getAutoCommit() throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.getAutoCommit();
		}

		@Override
		public void commit() throws SQLException {
			// TODO Auto-generated method stub
			this.conn.commit();
		}

		@Override
		public void rollback() throws SQLException {
			// TODO Auto-generated method stub
			this.conn.rollback();
		}

		@Override
		public boolean isClosed() throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.isClosed();
		}

		@Override
		public DatabaseMetaData getMetaData() throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.getMetaData();
		}

		@Override
		public void setReadOnly(boolean readOnly) throws SQLException {
			// TODO Auto-generated method stub
			this.conn.setReadOnly(readOnly);
		}

		@Override
		public boolean isReadOnly() throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.isReadOnly();
		}

		@Override
		public void setCatalog(String catalog) throws SQLException {
			// TODO Auto-generated method stub
			this.conn.setCatalog(catalog);
		}

		@Override
		public String getCatalog() throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.getCatalog();
		}

		@Override
		public void setTransactionIsolation(int level) throws SQLException {
			// TODO Auto-generated method stub
			this.conn.setTransactionIsolation(level);
		}

		@Override
		public int getTransactionIsolation() throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.getTransactionIsolation();
		}

		@Override
		public SQLWarning getWarnings() throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.getWarnings();
		}

		@Override
		public void clearWarnings() throws SQLException {
			// TODO Auto-generated method stub
			this.conn.clearWarnings();
		}

		@Override
		public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.createStatement(resultSetType, resultSetConcurrency);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.prepareStatement(sql, resultSetType, resultSetConcurrency);
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.prepareCall(sql, resultSetType, resultSetConcurrency);
		}

		@Override
		public Map<String, Class<?>> getTypeMap() throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.getTypeMap();
		}

		@Override
		public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
			// TODO Auto-generated method stub
			this.conn.setTypeMap(map);
		}

		@Override
		public void setHoldability(int holdability) throws SQLException {
			// TODO Auto-generated method stub
			this.conn.setHoldability(holdability);
		}

		@Override
		public int getHoldability() throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.getHoldability();
		}

		@Override
		public Savepoint setSavepoint() throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.setSavepoint();
		}

		@Override
		public Savepoint setSavepoint(String name) throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.setSavepoint(name);
		}

		@Override
		public void rollback(Savepoint savepoint) throws SQLException {
			// TODO Auto-generated method stub
			this.conn.rollback(savepoint);
		}

		@Override
		public void releaseSavepoint(Savepoint savepoint) throws SQLException {
			// TODO Auto-generated method stub
			this.conn.releaseSavepoint(savepoint);
		}

		@Override
		public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
				throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.prepareStatement(sql, autoGeneratedKeys);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.prepareStatement(sql, columnIndexes);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.prepareStatement(sql, columnNames);
		}

		@Override
		public Clob createClob() throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.createClob();
		}

		@Override
		public Blob createBlob() throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.createBlob();
		}

		@Override
		public NClob createNClob() throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.createNClob();
		}

		@Override
		public SQLXML createSQLXML() throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.createSQLXML();
		}

		@Override
		public boolean isValid(int timeout) throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.isValid(timeout);
		}

		@Override
		public void setClientInfo(String name, String value) throws SQLClientInfoException {
			// TODO Auto-generated method stub
			this.conn.setClientInfo(name, value);
		}

		@Override
		public void setClientInfo(Properties properties) throws SQLClientInfoException {
			// TODO Auto-generated method stub
			this.conn.setClientInfo(properties);
		}

		@Override
		public String getClientInfo(String name) throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.getClientInfo(name);
		}

		@Override
		public Properties getClientInfo() throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.getClientInfo();
		}

		@Override
		public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.createArrayOf(typeName, elements);
		}

		@Override
		public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.createStruct(typeName, attributes);
		}

		@Override
		public void setSchema(String schema) throws SQLException {
			// TODO Auto-generated method stub
			this.conn.setSchema(schema);
		}

		@Override
		public String getSchema() throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.getSchema();
		}

		@Override
		public void abort(Executor executor) throws SQLException {
			// TODO Auto-generated method stub
			this.conn.abort(executor);
		}

		@Override
		public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
			// TODO Auto-generated method stub
			this.conn.setNetworkTimeout(executor, milliseconds);
		}

		@Override
		public int getNetworkTimeout() throws SQLException {
			// TODO Auto-generated method stub
			return this.conn.getNetworkTimeout();
		}

	}
	///////////////////////////////////////////////////////////////////////////// 鍖呰璁捐妯″紡缁撴潫
}
