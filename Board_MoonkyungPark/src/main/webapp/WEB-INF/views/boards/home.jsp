<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<title>게시판홈</title>
</head>
<script>
	$(function() {
		$('#write').on('click', insertBoard);
	});
	function insertBoard() {
		location.href = "${pageContext.request.contextPath}/boards/insert";
	}
</script>
<body>
	<table class="table table-hover">
		<tr>
			<td>글번호</td>
			<td>글제목</td>
			<td>작성자</td>
			<td>작성일</td>
			<td>조회수</td>
		</tr>
		<c:forEach var="item" items="${boards }">
			<tr>
				<td>${item.board_num }</td>
				<td><a
					href="${pageContext.request.contextPath}/boards/get?board_num=${item.board_num}">${item.board_title }
						(${item.board_replies }) </a></td>
				<td>${item.board_id }</td>
				<td>${item.board_date }</td>
				<td>${item.board_hits }</td>
			</tr>
		</c:forEach>
		<tr>
			<td><input type="button" value="글쓰기" id="write"></td>
			<td><input type="button" value="홈으로"
				onclick="location.href='${pageContext.request.contextPath}/'"></td>
		</tr>
	</table>
	<div style="margin-left: 15%; padding: 1px 16px;">
		<form action="" , method="get">
			<input type="hidden" id="page" name="page" value="${page }">
			<select id="orderBy" name="orderBy">
				<option value="" selected="selected"></option>
				<option value="writer">작성자 순</option>
				<option value="time">오래된 시간 순</option>
			</select> <select id="searchType" name="searchType">
				<option value="" selected="selected"></option>
				<option value="board_title">글제목</option>
				<option value="board_id">작성자</option>
				<option value="board_content">내용</option>
			</select> <input type="text" id="searchText" name="searchText"
				style="width: 40%"> <input type="submit" value="검색">
		</form>
	</div>
	<a
		href="${pageContext.request.contextPath}/boards?page=${page-1}&searchType=${searchType}&searchText=${searchText}&orderBy=${orderBy}">&laquo;</a>
	<c:forEach var="page" begin="${page }" end="${endPage }">
		<a
			href="${pageContext.request.contextPath}/boards?page=${page}&searchType=${searchType}&searchText=${searchText}&orderBy=${orderBy}">${page}</a>
	</c:forEach>
	<a
		href="${pageContext.request.contextPath}/boards?page=${page+1}&searchType=${searchType}&searchText=${searchText}&orderBy=${orderBy}">&raquo;</a>
</body>
</html>
