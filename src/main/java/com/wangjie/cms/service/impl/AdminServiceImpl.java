package com.wangjie.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangjie.cms.dao.AdminMapper;
import com.wangjie.cms.entity.User;
import com.wangjie.cms.service.Adminservice;

@Service
public class AdminServiceImpl implements Adminservice{
	@Autowired
	AdminMapper adminMapper;
		
	// 获取用户列表
		@Override
		public PageInfo<User> userList(Integer pageNum, String name) {
			PageHelper.startPage(pageNum, 10);
			List<User> userList = adminMapper.userList(name);
			return new PageInfo<>(userList);
		}

		// 修改用户的状态
		@Override
		public int modifyUserStatus(Integer id, Integer locked) {
			int res = adminMapper.modifyUserStatus(id, locked);
			return res;
		}

}
