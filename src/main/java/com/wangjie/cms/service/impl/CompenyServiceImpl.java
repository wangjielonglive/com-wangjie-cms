package com.wangjie.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.wangjie.cms.dao.CompanyMapper;
import com.wangjie.cms.entity.Article;
import com.wangjie.cms.entity.Company;
import com.wangjie.cms.service.CompenyService;

@Service
public class CompenyServiceImpl implements CompenyService{
	
	@Resource
	private CompanyMapper companyMapper;
	
	@Resource
	private RedisTemplate<String, Company> redisTemplate;
	
	@Resource
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;

	@Override
	public List<Company> clist() {
		List<Company> opsForList = (List<Company>) redisTemplate.opsForList();
		PageInfo pageInfo = null;
		
		
		return companyMapper.clist();
	}



	@Override
	public void addToKafka(Company cd) {
		Gson gson = new Gson();
		String json = gson.toJson(cd);
		//添加数据
		kafkaTemplate.sendDefault("company_add", json);
		
	}


	@Override
	public void addToRedis(Company cd) {
		ListOperations<String, Company> opsForList = redisTemplate.opsForList();
		
		opsForList.leftPush("company_list", cd);
		
	}



	@Override
	public Company getById(Integer id) {
		return companyMapper.getById(id);
	}



	@Override
	public void updateToKafka(Company cd) {
		//发送到kafka
				Gson gson = new Gson();
				String json = gson.toJson(cd);
				//修改数据
				kafkaTemplate.sendDefault("company_update", json);
		
	}

}
