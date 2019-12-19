package com.wangjie.cms.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class User implements Serializable{
	private int id;
	@NotEmpty(message="用户名不能为空")
	@Length(min=4,max=12,message="用户名不合法")
	private String username;
	@NotEmpty(message="密码不能为空")
	@Length(min=3,max=9,message="密码名不合法")
	private String password;
	private String nickname;
	private Date birthday;
	private int gender;
	private int locked;
	private Date create_time;
	private Date update_time;
	private String url;
	private int score;
	private int role;
	
	private String head_picture;
	

	public String getHead_picture() {
		return head_picture;
	}
	public void setHead_picture(String head_picture) {
		this.head_picture = head_picture;
	}
	public User(String username, String password, int role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getLocked() {
		return locked;
	}
	public void setLocked(int locked) {
		this.locked = locked;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", nickname=" + nickname + ", birthday="
				+ birthday + ", gender=" + gender + ", locked=" + locked
				+ ", create_time=" + create_time + ", update_time="
				+ update_time + ", url=" + url + ", score=" + score + ", role="
				+ role + "]";
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result
				+ ((create_time == null) ? 0 : create_time.hashCode());
		result = prime * result + gender;
		result = prime * result + id;
		result = prime * result + locked;
		result = prime * result
				+ ((nickname == null) ? 0 : nickname.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + role;
		result = prime * result + score;
		result = prime * result
				+ ((update_time == null) ? 0 : update_time.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (create_time == null) {
			if (other.create_time != null)
				return false;
		} else if (!create_time.equals(other.create_time))
			return false;
		if (gender != other.gender)
			return false;
		if (id != other.id)
			return false;
		if (locked != other.locked)
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role != other.role)
			return false;
		if (score != other.score)
			return false;
		if (update_time == null) {
			if (other.update_time != null)
				return false;
		} else if (!update_time.equals(other.update_time))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
	
}
