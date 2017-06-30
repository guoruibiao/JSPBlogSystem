package dao;

import java.sql.Connection;
import java.util.ArrayList;

import bean.Tag;
import dbhelper.DbHelper;
import dbhelper.QueryRunner;
import handlers.BeanListHandler;

public class TagDAO extends AbstractDAO {
	private DbHelper helper = new DbHelper();

	@SuppressWarnings("finally")
	public boolean add(Tag tag) throws Exception {
		boolean isAdded = false;
		try {
			Connection conn = helper.getConnection();
			String sql = "insert into tag(name) values(?)";
			Object[] params = { tag.getName() };
			QueryRunner query = new QueryRunner();
			query.update(conn, sql, params);
			DbHelper.release(conn);

			isAdded = true;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("��ӱ�ǩʧ�ܣ�" + e);
		} finally {
			return isAdded;
		}
	}

	@SuppressWarnings("finally")
	public boolean delete(Integer tag_id) throws Exception {
		boolean isDeleted = false;
		try {
			Connection conn = helper.getConnection();
			String sql = "delete from tag where id=?";
			QueryRunner query = new QueryRunner();
			query.update(conn, sql, tag_id);
			DbHelper.release(conn);

			isDeleted = true;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("ͨ����ǩIDɾ����ǩʧ�ܣ�" + e);
		} finally {
			return isDeleted;
		}
	}

	@SuppressWarnings("finally")
	public boolean delete(String tagname) throws Exception {
		boolean isDeleted = false;
		try {
			Connection conn = helper.getConnection();
			String sql = "delete from tag where name=?";
			QueryRunner query = new QueryRunner();
			query.update(conn, sql, tagname);
			DbHelper.release(conn);

			isDeleted = true;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("ͨ����ǩ����ɾ����ǩʧ�ܣ�" + e);
		} finally {
			return isDeleted;
		}
	}

	@SuppressWarnings("finally")
	public boolean update(Integer tag_id, Tag tag) throws Exception {
		boolean isUpdated = false;
		try {
			Connection conn = helper.getConnection();
			String sql = "update tag set name=? where id=?";
			QueryRunner query = new QueryRunner();
			Object[] params = { tag.getName(), tag_id };
			query.update(conn, sql, params);
			DbHelper.release(conn);

			isUpdated = true;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("���±�ǩ����ʧ�ܣ�" + e);
		} finally {
			return isUpdated;
		}
	}

	public ArrayList<Tag> getAllTags() throws Exception {
		try {
			Connection conn = helper.getConnection();
			String sql = "select * from tag";
			QueryRunner query = new QueryRunner();
			ArrayList<Tag> tags = (ArrayList<Tag>) query.query(conn, sql, new BeanListHandler<Tag>(Tag.class));
			DbHelper.release(conn);

			return tags != null ? tags : null;
		} catch (Exception e) {
			throw new Exception("��ȡ���б�ǩ��Ϣ����" + e);
		}
	}

}
