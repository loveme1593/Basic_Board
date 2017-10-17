<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<title>글읽기</title>
</head>
<script>
$(function(){
	$('#delete').on('click', deleteBoard);
	$('#insertReply').on('click', insertReply);
});
function updateChangeReply(reply_num,reply_content){
	$('#replyUpdate_content'+reply_num).replaceWith('<input type="text" id="reply_text" value='+reply_content+'>');
	$('#updateReply'+reply_num).replaceWith('<input type="button" id="reply_reply" value="댓글 수정">');
	$('#reply_reply').on('click',function(){
		$.ajax({
			url:"updateReply",
			method:"post",
			data:{
				"reply_num":reply_num,
				"reply_content":$("#reply_text").val(),
				"board_num":${board.board_num}
			},
			success:function(board_num){
				alert('댓글이 성공적으로 수정되었습니다.');
				location.href = "${pageContext.request.contextPath}/boards/get?board_num="+board_num;
			}
		});
		
	});
}
function deleteBoard(){
	if(confirm('정말 삭제하시겠습니까?')){
			$.ajax({
				method : "post",
				url : "delete",
				data : {
					"board_num" : ${board.board_num}
				},
				success : function(result) {
					if(result==1){
					alert('글이 성공적으로 삭제되었습니다');
					location.href = "${pageContext.request.contextPath}/boards";
					}
				}
			});}
}
function updateBoard(board_num){
	location.href = "${pageContext.request.contextPath}/boards/update?board_num="+board_num;
}
function insertReply(){
	$.ajax({
		method:"post",
		url:"insertReply",
		data:{
			"board_num":${board.board_num},
			"reply_content":$("#reply_content").val()
		},
		success:function(board_num){
				alert('댓글이 성공적으로 등록되었습니다.');
				location.href = "${pageContext.request.contextPath}/boards/get?board_num="+board_num;
		}
	});
}
function deleteReply(reply_num){
	if(confirm('댓글을 삭제하시겠습니까?')){
		$.ajax({
			method:"post",
			url:"deleteReply",
			data:{
				"reply_num":reply_num,
				"board_num":${board.board_num}
			},
			success:function(board_num){
				alert('댓글이 성공적으로 삭제되었습니다.');
				location.href = "${pageContext.request.contextPath}/boards/get?board_num="+board_num;
			}
		});
	}
}
function insertR_reply(reply_num){
	$("#"+reply_num).replaceWith('<input type="text"id="r_reply_content"> <input type="button"id="r_reply_btn" value="댓글 작성">');
	$('#r_reply_btn').on('click',function(){
		$.ajax({
			method:"post",
			url:"insertReply",
			data:{
				"reply_num":reply_num,
				"reply_content":$("#r_reply_content").val(),
				"board_num":${board.board_num}
			},
			success:function(board_num){
				alert('댓글이 성공적으로 등록되었습니다.');
				location.href = "${pageContext.request.contextPath}/boards/get?board_num="+board_num;
		}
		});
	});
}
</script>
<body>
	<table>
		<tr>
			<td>글제목</td>
			<td>${board.board_title }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${board.board_nickname }</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${board.board_date }</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${board.board_hits }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${board.board_content }</td>
		</tr>
		<tr>
			<td><c:if test="${loginid==board.board_id }">
					<input type="button" value="수정" id="update" name="update"
						onclick="updateBoard('${board.board_num}')">
					<input type="button" value="삭제" id="delete" name="delete">
				</c:if> <input type="button" value="리스트로"
				onclick="location.href='${pageContext.request.contextPath}/boards'">
			</td>
	</table>

	<!-- 리플 -->
	<input type="text" id="reply_content" name="reply_content">
	<input type="button" id="insertReply" name="insertReply" value="댓글쓰기">
	<br>
	<c:forEach var="reply" items="${reply }">
		<table>
			<tr>
				<c:if test="${!empty reply.rreply_id }">
					<td>@${reply.reply_nickname }</td>
					<td>${reply.rreply_nickname }</td>
				</c:if>
				<c:if test="${empty reply.rreply_id }">
					<td>${reply.reply_nickname }</td>
				</c:if>
				<td><div id="replyUpdate_content${reply.reply_num }">${reply.reply_content }</div></td>
				<td>${reply.reply_date }</td>
				<!-- 대댓글 버튼:자기 자신이 아닐 때만 나타나도록 -->
				<c:if test="${loginid!=reply.reply_id&&loginid!=reply.rreply_id }">
					<td><input type="button" id="insertR_reply"
						name="insertR_reply" value="답글 작성"
						onclick="insertR_reply('${reply.reply_num}')"></td>
				</c:if>
				<td>
					<div id="${reply.reply_num }"></div>
				</td>
				<c:if
					test="${loginid==reply.reply_id&&empty reply.rreply_id||loginid==reply.rreply_id }">
					<td><input type="button" id="updateReply${reply.reply_num }"
						name="updateReply" value="댓글 수정"
						onClick="updateChangeReply('${reply.reply_num}','${reply.reply_content} ')"></td>
					<td><input type="button" id="deleteReply" name="deleteReply"
						value="댓글 삭제" onClick="deleteReply('${reply.reply_num}')"></td>
				</c:if>
			</tr>
		</table>
	</c:forEach>
</body>
</html>