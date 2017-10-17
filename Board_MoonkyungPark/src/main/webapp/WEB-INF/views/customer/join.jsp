<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 가입</title>
</head>

<body>
	<form action="join" method="post">
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" id="cus_id" name="cus_id"></td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td><input type="text" id="cus_nickname" name="cus_nickname"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" id="cus_pw" name="cus_pw"></td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="password" id="cus_pwC" name="cus_pwC"></td>
			</tr>
		</table>
		<input type="submit" value="가입하기">
	</form>
</body>
</html>