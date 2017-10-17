<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<title>글쓰기</title>
</head>
<script>
	$(function() {
		$('#insert').on('click', insertBoard);
	});
	function insertBoard() {
		$
				.ajax({
					method : "post",
					url : "insert",
					data : {
						"board_title" : $("#board_title").val(),
						"board_content" : $("#board_content").val()
					},
					success : function(result) {
						if (result == 1) {
							alert('글이 성공적으로 등록되었습니다');
							location.href = "${pageContext.request.contextPath}/boards";
						}
					}
				});
	}
</script>
<body>
	<table class="table table-hover">
		<tr>
			<td>글제목</td>
			<td><input type="text" id="board_title" name="board_title">
			</td>
		</tr>
		<tr>
			<td>글내용</td>
			<td><textArea id="board_content" name="board_content">
		</textArea></td>
		</tr>
		<tr>
			<td><input type="button" value="글쓰기" id="insert" name="insert"></td>
			<td><input type="button" value="뒤로"
				onclick="location.href='${pageContext.request.contextPath}/'">
			</td>
		</tr>
	</table>
</body>
</html>