package bean;

/**
 * 针对每一篇文章，描述其浏览量信息。
 * 
 * @author biao
 *
 */
public class Watches {

	private Integer id;
	private Integer post_id;
	private Integer watches;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPost_id() {
		return post_id;
	}

	public void setPost_id(Integer post_id) {
		this.post_id = post_id;
	}

	public Integer getWatches() {
		return watches;
	}

	public void setWatches(Integer watches) {
		this.watches = watches;
	}

	@Override
	public String toString() {
		return "Watches [id=" + id + ", post_id=" + post_id + ", watches=" + watches + "]";
	}

	public Watches() {
		super();
		// TODO Auto-generated constructor stub
	}

}
