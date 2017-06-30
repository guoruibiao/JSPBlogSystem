package dao;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;

public class WatchesDAOTest {

	private WatchesDAO dao = new WatchesDAO();

	@Test
	public void testGetWatches() throws Exception {
		Integer post_id = 4;
		Integer watches = dao.getWatches(post_id);
		System.out.println(watches);
	}

	@Test
	public void testIncreWatches() throws Exception {
		Integer post_id = 2;
		boolean isUpdated = dao.increWatches(post_id);
		System.out.println(isUpdated);
	}

	@Test
	public void testTopN() throws Exception {
		Integer topN = 5;
		@SuppressWarnings("rawtypes")
		ArrayList<Map> results = dao.topN(topN);
		for (@SuppressWarnings("rawtypes")
		Map map : results) {
			System.out.println(map.toString());
		}
	}

	@Test
	public void testTopNAuthor() throws Exception {
		Integer n = 3;
		ArrayList<Map> authors = dao.topNAuthor(n);
		for (Map author : authors) {
			System.out.println(author.toString());
		}
	}

}
