package com.wangjie.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangjie.cms.dao.ChannelMapper;
import com.wangjie.cms.entity.Article;
import com.wangjie.cms.entity.Channel;
import com.wangjie.cms.service.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService{
	@Autowired
	ChannelMapper channelMapper;
	
	@Override
	public List<Channel> getAllChnls() {
		// TODO Auto-generated method stub
		
		return channelMapper.listAll();
	}

	@Override
	public List<Article> getSreach(String title) {
		// TODO Auto-generated method stub
		return channelMapper.sreach(title);
	}

}
