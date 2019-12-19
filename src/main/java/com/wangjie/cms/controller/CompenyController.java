package com.wangjie.cms.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangjie.cms.dao.CompanyMapper;
import com.wangjie.cms.entity.Company;
import com.wangjie.cms.entity.Link;
import com.wangjie.cms.service.CompenyService;

@Controller
public class CompenyController {
	@Resource
	private CompenyService compenyService;
	
	@RequestMapping("clist")
	public String clist(HttpServletRequest request){
		List<Company> clist = compenyService.clist();
		request.setAttribute("clist", clist);
		
		return "company/clist";
		
	}
	
	@RequestMapping("toAdd")
	public String toAdd() {
		
		return "add";
	}
	@RequestMapping("add")
	public String add(Company cd){
		//存到mysql数据库中，通过kafka
		compenyService.addToKafka(cd);
				
		//存到redis中
		compenyService.addToRedis(cd);
		return "redirect:clist";
		
	}
	
	@RequestMapping("/toUpdate")
	public String toUpdate(Model model,Integer id) {
		
		Company stu = compenyService.getById(id);
		model.addAttribute("stu", stu);
		
		return "update";
	}
	
	@RequestMapping("update")
	public String update(Company cd) {
		
		//发送到数据库
//		studentService.update(stu);
		//发送到kafka
		compenyService.updateToKafka(cd);
		
		return "redirect:listFromRedis";
	}

}
