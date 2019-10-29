package com.wangjie.cms.service;

import java.util.List;

import com.wangjie.cms.entity.Article4Vote;
import com.wangjie.cms.entity.VoteStatic;

public interface Article4VoteService {

	int publish(Article4Vote av);
	
	List<Article4Vote>  list();
	
	Article4Vote  findById(Integer id);
	
	int vote(Integer articleId,Character option);
	//int vote(Integer userId, Integer articleId,Character option);
	
	List<VoteStatic> getVoteStatics(Integer articleId);

}
