package biz;

import java.util.ArrayList;
import java.util.Map;

import dao.WatchesDAO;

/**
 * 浏览量相关的业务处理。可以为热门播主，热门文章，也可以为topX类型。
 * 
 * @author biao
 *
 */
public class WatchesBusiness {

	private WatchesDAO watchDAO = new WatchesDAO();

	/**
	 * 根据博客编号获取文章对应的浏览量。
	 * 
	 * @param post_id
	 *            文章编号
	 * @return 文章编号对应的浏览量数据。
	 * @throws Exception
	 */
	public Integer getWatches(Integer post_id) throws Exception {

		return watchDAO.getWatches(post_id);
	}

	/**
	 * 根据给定的博客编号，为其浏览量加一，实现浏览量计算。
	 * 
	 * @param post_id
	 *            博客编号
	 * @return 浏览量增加操作成功返回TRUE，否则返回False。
	 * @throws Exception
	 */
	public boolean increWatches(Integer post_id) throws Exception {

		return watchDAO.increWatches(post_id);
	}

	/**
	 * 根据给定的数字N，返回本站浏览量最高的N篇博文。
	 * 
	 * @param n
	 *            理论上n为大于0的正整数。当n足够大时，默认返回站内文章数上限条记录。
	 * @return 返回记录数小于等于n的博文数据。
	 * @throws Exception
	 */
	public ArrayList<Map> topN(Integer n) throws Exception {
		if (n <= 0) {
			throw new Exception("在WatchesBusiness.topN方法中给定的参数n不合法！");
		} else {

			return watchDAO.topN(n);
		}

	}

	/**
	 * 获取本站撰写文章数目最多的前n个作者的相关信息。
	 * 
	 * @param n
	 *            理论上n为大于等于0的正整数。
	 * @return 默认返回结果集数目小于等于n。
	 * @throws Exception
	 */
	public ArrayList<Map> topNAuthor(Integer n) throws Exception {

		return watchDAO.topNAuthor(n);
	}

}
