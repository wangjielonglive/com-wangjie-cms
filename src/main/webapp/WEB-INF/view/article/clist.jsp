<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<body>

	<c:forEach items="${comments.list }" var="comment">
				<div class="media-body">
					<h5 class="mt-0 mb-1"><small> ${comment.content}</small></h5>
					<br>
					<br>
					<h5 class="mt-0 mb-1"><small> ${comment.userName }  &nbsp;  <fmt:formatDate value="${comment.created }" pattern="yyyy-MM-dd"/> </small></h5>
				</div>
				</li>
				<hr>
		</c:forEach>    
		<div>${page}</div>
</body>
<script type="text/javascript">
	$(function(){
	    $('.page-link').click(function (e) {
	    	
	    	
	    	  //获取点击的的url
	        var url = $(this).attr('data');
	        // alert(url);
	    
	       //在中间区域显示地址的内容
	       $('#center').load(url);
	    });
		
	})
	
</script>
<script type="text/javascript" src="/resource/js/cms.js"></script>