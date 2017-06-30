package bean;

public class PostTag {

	private Integer post_id;
	private Integer tag_id;

	public Integer getPost_id() {
		return post_id;
	}

	public void setPost_id(Integer post_id) {
		this.post_id = post_id;
	}

	public Integer getTag_id() {
		return tag_id;
	}

	public void setTag_id(Integer tag_id) {
		this.tag_id = tag_id;
	}

	@Override
	public String toString() {
		return "PostTag [post_id=" + post_id + ", tag_id=" + tag_id + "]";
	}

	public PostTag() {
		super();
		// TODO Auto-generated constructor stub
	}

}
