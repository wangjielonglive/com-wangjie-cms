package com.wangjie.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.wangjie.cms.entity.User;

public interface AdminMapper {
	// 获取用户列表
	List<User> userList(@Param("name")String name);
	
	// 修改用户的状态
	@Update("UPDATE cms_user SET locked = #{locked} WHERE id = #{id}")
	int modifyUserStatus(@Param("id")Integer id, @Param("locked")Integer locked);
	
}
