package biz;

import java.util.ArrayList;
import java.util.Map;

import dao.WatchesDAO;

/**
 * �������ص�ҵ��������Ϊ���Ų������������£�Ҳ����ΪtopX���͡�
 * 
 * @author biao
 *
 */
public class WatchesBusiness {

	private WatchesDAO watchDAO = new WatchesDAO();

	/**
	 * ���ݲ��ͱ�Ż�ȡ���¶�Ӧ���������
	 * 
	 * @param post_id
	 *            ���±��
	 * @return ���±�Ŷ�Ӧ����������ݡ�
	 * @throws Exception
	 */
	public Integer getWatches(Integer post_id) throws Exception {

		return watchDAO.getWatches(post_id);
	}

	/**
	 * ���ݸ����Ĳ��ͱ�ţ�Ϊ���������һ��ʵ����������㡣
	 * 
	 * @param post_id
	 *            ���ͱ��
	 * @return ��������Ӳ����ɹ�����TRUE�����򷵻�False��
	 * @throws Exception
	 */
	public boolean increWatches(Integer post_id) throws Exception {

		return watchDAO.increWatches(post_id);
	}

	/**
	 * ���ݸ���������N�����ر�վ�������ߵ�Nƪ���ġ�
	 * 
	 * @param n
	 *            ������nΪ����0������������n�㹻��ʱ��Ĭ�Ϸ���վ����������������¼��
	 * @return ���ؼ�¼��С�ڵ���n�Ĳ������ݡ�
	 * @throws Exception
	 */
	public ArrayList<Map> topN(Integer n) throws Exception {
		if (n <= 0) {
			throw new Exception("��WatchesBusiness.topN�����и����Ĳ���n���Ϸ���");
		} else {

			return watchDAO.topN(n);
		}

	}

	/**
	 * ��ȡ��վ׫д������Ŀ����ǰn�����ߵ������Ϣ��
	 * 
	 * @param n
	 *            ������nΪ���ڵ���0����������
	 * @return Ĭ�Ϸ��ؽ������ĿС�ڵ���n��
	 * @throws Exception
	 */
	public ArrayList<Map> topNAuthor(Integer n) throws Exception {

		return watchDAO.topNAuthor(n);
	}

}
