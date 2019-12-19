package com.wangjie.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wangjie.cms.entity.Company;

public interface CompanyMapper {

	List<Company> clist();

	void add(Company cd);

	Company getById(@Param("id")Integer id);

	void del(@Param("id")Integer id);

}
