package com.wangjie.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangjie.cms.dao.DomainMapper;
import com.wangjie.cms.service.DomainService;

@Service
public class DomainServiceImpl implements DomainService{
	@Autowired
	DomainMapper domainMapper;

	@Override
	public void list(int page) {
		// TODO Auto-generated method stub
		domainMapper.list(page);
	}

}
