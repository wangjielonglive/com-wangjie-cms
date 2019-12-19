package com.wangjie.cms.entity;

public class Domain {
	private int id;
	private String title;
	private String stract;//abstract
	private String created;
	public Domain(int id, String title, String stract, String created) {
		super();
		this.id = id;
		this.title = title;
		this.stract = stract;
		this.created = created;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStract() {
		return stract;
	}
	public void setStract(String stract) {
		this.stract = stract;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public Domain() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Domain [id=" + id + ", title=" + title + ", stract=" + stract
				+ ", created=" + created + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + id;
		result = prime * result + ((stract == null) ? 0 : stract.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Domain other = (Domain) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (id != other.id)
			return false;
		if (stract == null) {
			if (other.stract != null)
				return false;
		} else if (!stract.equals(other.stract))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	
}
