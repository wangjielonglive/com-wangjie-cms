<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/js/jquery-1.8.2.js"></script>
<link rel="stylesheet" type="text/css" href="/css/index_work.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="add" name="id" >年检审核</a>
	<table >
		<tr>
			<td>id</td>
			<td>关键字</td>
			<td>描述</td>
			<td>公司名称</td>
			<td>主营产品</td>
			<td>地址</td>
			<td>注册资本</td>
			<td>成立时间</td>
			<td>年检日期</td>
			<td>年检状态</td>
			<td>备注</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${clist}" var="ll">
			<tr>
				<td>${ll.id }</td>
				<td>${ll.keywords }</td>
				<td>${ll.description }</td>
				<td>${ll.companyName }</td>
				<td>${ll.mainBusinessProducts }</td>
				<td>${ll.address  }</td>
				<td>${ll.registeredCapital  }</td>
				<td>${ll.foundingTime }</td>
				<td>${ll.annualCheckDate }</td>
				<td>${ll.annualCheckStatus  }</td>
				<td>${ll.rmk }</td>
				<td>
					<a href="add.jsp" name="id" >年检审核</a>
			
				</td>
			</tr>
		</c:forEach>
	</table>
	
</body>

</html>