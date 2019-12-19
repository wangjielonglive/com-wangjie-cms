<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <script type="text/javascript" src="jquery/jquery-1.8.2.js"></script>
  <link rel="stylesheet" type="text/css" href="css/index_work.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
	<div class="container">
		<form action="" id="sdh">
			<label>文章标题</label>
			<input name="title" id="title">
			<br>
			<label>文章摘要</label>
			<textarea rows="10" cols="100" name="digest" id="digest"></textarea>
			<br>
			<input type="button" value="提交专题" onclick="adda">
		</form>
	</div>
	<script type="text/javascript">
		function adda(){
			.post("/article/add",$("#spform").serialize(),function (msg){
				if(mas.result==1){
					alert("处理成功!!!");
				}else{
					alert("处理失败!!!");
				}
			},json;)
		}
	</script>
</html>