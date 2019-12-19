package com.wangjie.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.wangjie.cms.intercepter.PageUtils;
import com.wangjie.cms.service.DomainService;

@Controller
public class DomainController {
	@Autowired
	DomainService domainService;
	
	public String absteat(HttpServletRequest request,int page){
		domainService.list(page);
		PageUtils.pages(request, "/user/list", "page", "10", "page", "page");
		return null;
	}
}
