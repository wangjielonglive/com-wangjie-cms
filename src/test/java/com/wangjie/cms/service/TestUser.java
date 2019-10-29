package com.wangjie.cms.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.wangjie.cms.entity.User;

public class TestUser extends BaseTest{
	@Autowired
	UserService userService;
	
	@Test
	public void testRegister(){
		User user = new User("wangwu","password",5);
		int register = userService.register(user);
		assertTrue(register>0);
		
	}


}
