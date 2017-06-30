package bean;

import java.util.Date;

public class Comment {
	private Integer id;
	private Integer user_id;
	private String content;
	private Date commenttime;

	private Integer post_id;

	public Integer getPost_id() {
		return post_id;
	}

	public void setPost_id(Integer post_id) {
		this.post_id = post_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCommenttime() {
		return commenttime;
	}

	public void setCommenttime(Date commenttime) {
		this.commenttime = commenttime;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", user_id=" + user_id + ", content=" + content + ", commenttime=" + commenttime
				+ ", post_id=" + post_id + "]";
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

}
