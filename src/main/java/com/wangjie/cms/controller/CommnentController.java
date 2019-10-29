package com.wangjie.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangjie.cms.common.ConstClass;
import com.wangjie.cms.common.ResultMsg;
import com.wangjie.cms.entity.Comment;
import com.wangjie.cms.entity.User;
import com.wangjie.cms.service.CommnentService;

@Controller
//@RequestMapping("commnent")
public class CommnentController {

	@Autowired
	CommnentService commnentService;
	//添加评论
	@RequestMapping("commnentinsert")
	@ResponseBody
	public ResultMsg comment1(HttpServletRequest request,Integer articleId,String content) {
		User loginUser = (User)request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		if(loginUser==null) {
			return new ResultMsg(2, "用户尚未登录", "");		
		}
		commnentService.comment(loginUser.getId(),articleId,content);
		return new ResultMsg(1, "发布成功", "");
	}

	@RequestMapping("commnentlist")
	public String getComment(HttpServletRequest request ,@Param("articleId")Integer articleId) {
		System.out.println("123");
		List<Comment> comments =commnentService.commnentlist(articleId);
		for (Comment commnent : comments) {
			System.out.println(commnent);
		}
		request.setAttribute("comments", comments);
		return "article/clist";
		
	}
	
	
	
}
