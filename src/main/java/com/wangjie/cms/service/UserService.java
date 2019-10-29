package com.wangjie.cms.service;

import com.wangjie.cms.entity.User;

public interface UserService {
	int register(User user) ;
	User login(User user);
	
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	boolean checkUserExist(String username);
	
		
	//个人主要上传头像
	int addHead_picture(User user);
		
}
