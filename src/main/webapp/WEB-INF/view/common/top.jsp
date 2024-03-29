<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="navbar navbar-light bg-light">
  <a class="navbar-brand" href="/" title="CMS"><img alt="CMS" src="/resource/images/logo.png"></a>
  
  <!-- 搜索框：在专业高级二学完ElasticSearch后实现 -->
  <form class="form-inline" action="/">
    <div class="input-group">
      <input type="text" name="key" value="${key}" class="form-control" placeholder="输入关键字..." aria-label="key" aria-describedby="basic-addon1">
      <div class="input-group-prepend">
        <button class="input-group-btn btn btn-outline-primary" id="basic-addon1">搜索</button>
      </div>
    </div>
  </form>
  
  <!-- 右边登录注册 -->
  <ul class="nav">
   	<c:choose>
   	<%-- 登录显示用户菜单 --%>
    <c:when test="${sessionScope.SESSION_USER_KEY != null}">
   	 <li class="nav-item">
   		<a class="nav-link" href="/my/home">
		<img alt="" src="/pic/${sessionScope.SESSION_USER_KEY.head_picture }" style="max-height: 2.5rem" class="rounded img-fluid">
   		</a>
   	 </li>
   	 <li class="nav-item">
   		<div class="dropdown" style="padding-top: 0.4rem;">
		  <a href="#" class="nav-link dropdown-toggle" role="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		    <c:out value="${sessionScope.SESSION_USER_KEY.username}" default="CMS-User"/>
		  </a>
		  <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
		     <a class="dropdown-item" href="/">返回首页</a>
		    <c:if test="${sessionScope.SESSION_USER_KEY.role==1}">
		    	<a class="dropdown-item" href="/admin/index">后台管理</a>
		    </c:if>
		    <c:if test="${sessionScope.SESSION_USER_KEY.role==0}">
		    	<a class="dropdown-item" href="/user/home">个人主页</a>
		    </c:if>
		    
		    <a class="dropdown-item" href="#">个人设置</a>
		    <a class="dropdown-item" href="user/myarticlelist">我的文章</a>
		    <div class="dropdown-divider"></div>
		    <a class="dropdown-item" href="/user/logout">退出</a>
		  </div>
		</div>
   	 </li>
    </c:when>
    <c:otherwise>
      <%-- 未登录显示登录注册链接 --%>
	   <li class="nav-item"><a class="nav-link" href="/user/register">注册</a></li>
	   <li class="nav-item"><a class="nav-link" href="/user/login">登录</a></li>
     </c:otherwise>
    </c:choose>
  </ul>
</nav>