package com.wangjie.cms.entity;

import java.util.Date;

public class User {
	private int id;
	private String username;
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
	public User(int id, String username, String password, String nickname,
			Date birthday, int gender, int locked, Date create_time,
			Date update_time, String url, int score, int role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.birthday = birthday;
		this.gender = gender;
		this.locked = locked;
		this.create_time = create_time;
		this.update_time = update_time;
		this.url = url;
		this.score = score;
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
	
	
}
