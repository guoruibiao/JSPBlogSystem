package bean;

import java.util.Set;

public class Tag {
	private Integer id;


	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + "]";
	}

	public Tag(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Tag() {
		super();
		// TODO Auto-generated constructor stub
	}

}
