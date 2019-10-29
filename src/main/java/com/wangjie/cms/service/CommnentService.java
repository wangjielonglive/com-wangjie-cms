package com.wangjie.cms.service;

import java.util.List;

import com.wangjie.cms.entity.Comment;

public interface CommnentService {


	List<Comment> commnentlist(Integer articleId);

	/**
	 * 发布评论
	 * @param id
	 * @param articleId
	 * @param content
	 */
	void comment(Integer id, Integer articleId, String content);

}
