<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Home</title>
</head>
<script>
	function showResult(loginResult) {
		var loginResult = loginResult.value;
		if (loginResult != '') {
			alert(loginResult);
		}
	}
</script>
<body onLoad="showResult(${loginResult});">
	<c:if test="${empty loginid }">
		<h3>게시판입니다 로그인 해주세요!</h3>
		<form action="login" method="post">
			<table>
				<tr>
					<td>아이디</td>
					<td><input type="text" id="cus_id" name="cus_id"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" id="cus_pw" name="cus_pw"></td>
				</tr>
				<tr>
					<td><input type="submit" value="로그인"></td>
				</tr>
			</table>
		</form>
		<input type="button" value="회원 가입"
			onclick="location.href='${pageContext.request.contextPath}/customer/join'">
	</c:if>
</body>
</html>
