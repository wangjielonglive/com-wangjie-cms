package com.wangjie.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangjie.cms.dao.CatMapper;
import com.wangjie.cms.entity.Cat;
import com.wangjie.cms.service.CatService;

@Service
public class CatServiceImpl implements CatService{
	
	@Autowired
	CatMapper catMapper;
	
	@Override
	public List<Cat> getListByChnlId(Integer id) {
		// TODO Auto-generated method stub
		return catMapper.selectByChnlId(id);
	}

}
