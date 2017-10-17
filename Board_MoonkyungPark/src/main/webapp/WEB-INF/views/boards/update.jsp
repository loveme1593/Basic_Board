<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<title>게시물 수정</title>
</head>
<script>
	$(function() {
		$('#update').on('click', updateBoard);
	});
	function updateBoard() {
		$
				.ajax({
					method : "post",
					url : "update",
					data : {
						"board_num":${board.board_num},
						"board_title" :  $("#board_title").val(),
						"board_content" : $("#board_content").val()
					},
					success : function(board_num) {
							alert('글이 성공적으로 수정되었습니다');
							location.href = "${pageContext.request.contextPath}/boards/get?board_num="+board_num;
					}
				});
	}
</script>
<body>
	<table class="table table-hover">
		<tr>
			<td>글제목</td>
			<td><input type="text" id="board_title" name="board_title"
				value="${board.board_title }"></td>
		</tr>
		<tr>
			<td>글내용</td>
			<td><textArea id="board_content" name="board_content">
           ${board.board_content}
		</textArea></td>
		</tr>
		<tr>
			<td><input type="button" value="수정하기" id="update" name="update"></td>
			<td><input type="button" value="뒤로"
				onclick="location.href='${pageContext.request.contextPath}/'">
			</td>
		</tr>
	</table>
</body>
</html>