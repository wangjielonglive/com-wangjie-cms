package com.wangjie.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangjie.cms.dao.CommnentMapper;
import com.wangjie.cms.entity.Comment;
import com.wangjie.cms.service.CommnentService;

@Service
public class CommnentServiceImpl implements CommnentService {

	@Autowired
	CommnentMapper commnentMapper;
	

	

	@Override
	public List<Comment> commnentlist(Integer articleId) {
		// TODO Auto-generated method stub
		return commnentMapper.commnentlist(articleId);
	}

	@Override
	public void comment(Integer userId, Integer articleId, String content) {
		// TODO Auto-generated method stub
		Comment commnent = new Comment(articleId,userId,content);
		commnentMapper.addCommnent(commnent);
		commnentMapper.increaseCommentCnt(articleId);
	}
	


}
