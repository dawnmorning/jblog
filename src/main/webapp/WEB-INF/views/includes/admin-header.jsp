<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul class="admin-menu">
		<li><a
			href="${pageContext.request.contextPath }/${blogVo.blogId}">홈</a></li>
		<li class="selected"><a
			href="${pageContext.request.contextPath }/${blogVo.blogId}/admin/basic">기본설정</a></li>
		<li><a
			href="${pageContext.request.contextPath }/${blogVo.blogId}/admin/category">카테고리</a></li>
		<li><a
			href="${pageContext.request.contextPath }/${blogVo.blogId}/admin/write">글작성</a></li>

	</ul>
</body>
</html>