package com.wangjie.cms.service;

import java.util.List;

import com.wangjie.cms.entity.Company;

public interface CompenyService {

	List<Company> clist();


	void addToKafka(Company cd);

	void addToRedis(Company cd);


	Company getById(Integer id);


	void updateToKafka(Company cd);

}
