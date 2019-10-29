package com.wangjie.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangjie.cms.dao.ArticleMapper;
import com.wangjie.cms.dao.SpecialMapper;
import com.wangjie.cms.entity.Special;
import com.wangjie.cms.service.SpecialService;

@Service
public class SpecialServiceImpl implements SpecialService{
	@Autowired
	SpecialMapper specialMapper; 
	
	@Autowired
	ArticleMapper  articleMapper;

	@Override
	public List<Special> list() {
		// TODO Auto-generated method stub
		
		List<Special> list =  specialMapper.list();
		for (Special special : list) {
			special.setArticleNum(articleMapper.getArticleNum(special.getId()));
		}
		return list;
	}

	@Override
	public int add(Special special) {
		// TODO Auto-generated method stub
		return specialMapper.add(special);
	}

	@Override
	public Special findById(Integer id) {
		// TODO Auto-generated method stub
		Special special = specialMapper.findById(id);
		special.setArtilceList(articleMapper.findBySepecailId(id));
		return special;
	}

	@Override
	public int addArticle(Integer specId, Integer articleId) {
		// TODO Auto-generated method stub
		return specialMapper.addArticle( specId,  articleId);
	}

	@Override
	public int removeArticle(Integer specId, Integer articleId) {
		// TODO Auto-generated method stub
		return specialMapper.removeArticle(specId, articleId);
	}

	@Override
	public int update(Special special) {
		// TODO Auto-generated method stub
		return specialMapper.update(special);
	}
	

}
