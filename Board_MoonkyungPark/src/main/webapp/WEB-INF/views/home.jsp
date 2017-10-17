<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 홈</title>
</head>
<script>
	function logout() {
		location.href = "${pageContext.request.contextPath}/customer/logout"
	}
</script>
<body>
	<c:if test="${not empty loginResult }">
		<script>
			alert('${loginResult}');
		</script>
	</c:if>
	<c:if test="${empty loginid }">
		<h3>로그인 후 이용 가능합니다.</h3>
		<input type="button" value="로그인"
			onclick="location.href='${pageContext.request.contextPath}/customer/login'">
	</c:if>
	<c:if test="${not empty loginid }">
		<h3>게시판에 오신 것을 환영합니다</h3>
		<input type="button" value="게시판으로"
			onclick="location.href='${pageContext.request.contextPath}/boards'">
		<input type="button" value="로그아웃" onClick="logout()">
	</c:if>
</body>
</html>