package com.wangjie.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangjie.cms.dao.LinkMapper;
import com.wangjie.cms.entity.Link;
import com.wangjie.cms.service.LinkService;

@Service
public class LinkServiceImpl implements LinkService{

	@Autowired
	private LinkMapper linkMapper;

	@Override
	public List<Link> linklist() {
		// TODO Auto-generated method stub
		return linkMapper.linklist();
	}

	@Override
	public int addlink(Link link) {
		// TODO Auto-generated method stub
		return linkMapper.addlink(link);
	}
	//友情链接的删除
	@Override
	public int deletelink(Integer id) {
		// TODO Auto-generated method stub
		return linkMapper.deletelink(id);
	}

	/*@Override
	public int linkupdate(Integer id) {
		// TODO Auto-generated method stub
		return linkMapper.linkupdate(id);
	}
	*/
}
