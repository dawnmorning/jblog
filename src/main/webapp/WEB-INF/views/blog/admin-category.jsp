<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${blogVo.title }</h1>
			<c:import url="/WEB-INF/views/includes/blog-header.jsp" />
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/admin-header.jsp" />
				<table class="admin-cat">
					<tr>
						<th>번호</th>
						<th>카테고리명</th>
						<th>포스트 수</th>
						<th>설명</th>
						<th>삭제</th>
					</tr>
					<c:set var="count" value="${fn:length(categoryVo) }" />
					<c:forEach items="${categoryVo }" var="item" varStatus="status">
						<tr>
							<td>${count - status.index}</td>
							<td>${item.name }</td>
							<td>${item.postCount }</td>
							<td>${item.description }</td>
							<td><a
								href="${pageContext.request.contextPath}/${blogVo.blogId }/admin/delete/${item.no}">
									<img
									src="${pageContext.request.contextPath}/assets/images/delete.jpg">
							</a></td>
						</tr>
					</c:forEach>
				</table>
				<h4 class="n-c">새로운 카테고리 추가</h4>
				<form method="post"
					action="${pageContext.request.contextPath}/${blogVo.blogId}/admin/category">
					<table id="admin-cat-add">
						<tr>
							<td class="t">카테고리명</td>
							<td><input type="text" name="name"></td>
						</tr>
						<tr>
							<td class="t">설명</td>
							<td><input type="text" name="description"></td>
						</tr>
						<tr>
							<td class="s">&nbsp;</td>
							<td><input type="submit" value="카테고리 추가"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div id="footer">
			<c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
		</div>
	</div>
</body>
</html>