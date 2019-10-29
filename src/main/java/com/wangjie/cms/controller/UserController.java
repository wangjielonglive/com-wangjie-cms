package com.wangjie.cms.controller;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.wangjie.cms.common.ConstClass;
import com.wangjie.cms.entity.Article;
import com.wangjie.cms.entity.User;
import com.wangjie.cms.intercepter.PageUtils;
import com.wangjie.cms.service.Article4VoteService;
import com.wangjie.cms.service.ArticleService;
import com.wangjie.cms.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	Article4VoteService avService;
	
	@GetMapping("register")// 只接受get的请求
	public String register(){
		System.out.println("我是GET");
		return "user/register";
	}
	
	@RequestMapping("index")
	public String index() {
		return "user/index";
	}
	
	/**
	 * 判断用户名是否已经被占用
	 * @param username
	 * @return
	 */
	@RequestMapping("checkExist")
	@ResponseBody
	public boolean checkExist(String username) {
		return !userService.checkUserExist(username);
	}
	
	@PostMapping("register")
	public String register(HttpServletRequest request,@Validated User user,BindingResult error){
		System.out.println("我是POST");
		if (error.hasErrors()) {
			request.setAttribute("errorMsg", "系统错误，请稍后重试");
			return "user/register";
		}
		int result = userService.register(user);
		System.out.println(result);
		if (result>0) {
			return "redirect:login";
		} else {
			
			return "user/register";
		}
	}
	
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login(){
		
		return "user/login";
	}
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute(ConstClass.SESSION_USER_KEY);
		return "user/login";
	}
	
	@PostMapping("login")
	public String login(HttpServletRequest request,@Validated User user,BindingResult error){
		if (error.hasErrors()) {
			return "login";
		}
		//登录
		User loginUser = userService.login(user);
		if(loginUser==null) {
			request.setAttribute("errorMsg", "用户名密码错误");
			return "user/login";
		}else {
			if (loginUser.getLocked() == 0) {  // 判断用户是否被冻结
				//用户信息保存在session当中
				request.getSession().setAttribute(ConstClass.SESSION_USER_KEY, loginUser);
				//普通注册用户
				if(loginUser.getRole()==ConstClass.USER_ROLE_GENERAL) {
					return "redirect:home";	
				//管理员用户	
				}else if(loginUser.getRole()==ConstClass.USER_ROLE_ADMIN){
					return "redirect:../admin/index";	
					
				}else {
					// 其他情况
					return "user/login";
				}
			} else {
				request.setAttribute("errorMsg", "该用户已被冻结！！");
				return "user/login";
			}
			
		}
	}
	
	/**
	 * 进入个人中心(普通注册用户)
	 * @param request
	 * @return
	 */
	@RequestMapping("home")
	public String home(HttpServletRequest request) {
		return "my/home";
	}
	
	/**
	 * 删除用户自己的文章
	 * @param id 文章id
	 * @return
	 */
	@RequestMapping("delArticle")
	@ResponseBody
	public boolean delArticle(Integer id) {
		return articleService.remove(id)>0;
	}
	
	/**
	 * 进入个人中心 获取我的文章
	 * @param request
	 * @return
	 */
	@RequestMapping("myarticlelist")
	public String myarticles(HttpServletRequest request,
			@RequestParam(defaultValue="1") Integer page) {
		
		User loginUser =(User) request.getSession().getAttribute(ConstClass.SESSION_USER_KEY);
		PageInfo<Article>  pageArticles = articleService.listArticleByUserId(loginUser.getId(),page);
		//PageUtils.page(request, "/user/myarticlelist", 10, pageArticles.getList(), (long)pageArticles.getTotal(), pageArticles.getPageNum());
		String pageStr = PageUtils.pageLoad(pageArticles.getPageNum(), pageArticles.getPages(), "/user/myarticlelist", 10);
		request.setAttribute("pageArticles", pageArticles);
		request.setAttribute("page", pageStr);
		return "/my/list";
	}
	
	/**
	 * 跳转到上传页面
	 */
	@GetMapping("toAddhead_picture")
	public String toAddhead_picture() {
		return "my/addhead_picture";
	}

	
	@PostMapping("addhead_picture")
	public String addHead_picture(HttpServletRequest request,MultipartFile file) throws IllegalStateException, IOException {
		User user = (User)request.getSession().getAttribute("SESSION_USER_KEY");
		System.out.println("112323423121233");
		System.out.println("user----------"+user);
		processFile(file,user);
		
		 userService.addHead_picture(user);
		return "redirect:home";
		
	}
	
	/**
	 * 处理接收到的文件
	 */
	
	private void processFile(MultipartFile file,User user) throws IllegalStateException, IOException {


		// 原来的文件名称
		System.out.println("file.isEmpty() :" + file.isEmpty()  );
		System.out.println("file.name :" + file.getOriginalFilename());
		
		if(file.isEmpty()||"".equals(file.getOriginalFilename()) || file.getOriginalFilename().lastIndexOf('.')<0 ) {
			user.setHead_picture("");
			return;
		}
			
		String originName = file.getOriginalFilename();
		String suffixName = originName.substring(originName.lastIndexOf('.'));
		SimpleDateFormat sdf=  new SimpleDateFormat("yyyyMMdd");
		String path = "d:/pic/" + sdf.format(new Date());
		File pathFile = new File(path);
		if(!pathFile.exists()) {
			pathFile.mkdir();
		}
		String destFileName = 		path + "/" +  UUID.randomUUID().toString() + suffixName;
		File distFile = new File( destFileName);
		file.transferTo(distFile);//文件另存到这个目录下边
		user.setHead_picture(destFileName.substring(7));
		
	}
}
