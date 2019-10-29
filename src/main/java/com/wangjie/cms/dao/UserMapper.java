package com.wangjie.cms.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wangjie.cms.entity.User;

public interface UserMapper {
	@Insert("INSERT INTO cms_user(username,password,gender,create_time) values(#{username},#{password},#{gender},now())") 
	int add(User user);
	@Select("select id,username,password,role,locked,head_picture from cms_user where username=#{value} limit 1")
	User findByName(String username);
	
	//上傳圖片
		@Update("update cms_user set head_picture=#{head_picture} where id=#{id}")
	int addHead_picture(User user);

}
