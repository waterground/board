<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${!empty member}">
<div class="my-3 p-3 bg-white rounded shadow-sm" style="padding-top:10px">
	<h6>댓글 쓰기<span id="rCnt"></span></h6>
	<form:form id="replyForm" method="post" modelAttribute="reply">
		<div>
			<table class="table table-bordered">
				<tr>
					<td>
						<form:hidden path="mId" id="mId" value="${member.mId}"/>
						<form:hidden path="rName" value="${member.mName}"/>
						<form:textarea rows="3" path="rContent" id="rContent" placeholder="댓글을 입력하세요" style="width:100%;"></form:textarea>
						<br/>
						<button id="writeBtn"
								class="btn float-right btn-success">등록</button>
					</td>
				</tr>
			</table>
		</div>
	</form:form>
</div>
</c:if>
<div class="my-3 p-3 bg-white rounded shadow-sm" style="padding-top: 10px">
	<h6 class="border-bottom pb-2 mb-0">댓글 목록</h6>
	<div id="replyList"></div>
	<div class="text-center" style="margin:0 auto; width: 240px;">
		<ul class="pagination"></ul>
	</div>
</div> 
<script>
var bId, mIdVal;
// 댓글 목록 페이지 번호 초기화
var pageNum = 1;
$(document).ready(function(){
	
	bId = ${board.bId};
	mIdVal = $("#mId").val();
	// 댓글 목록 함수 호출
		
	fn_getList(pageNum);
});

//댓글 등록
$("#writeBtn").on("click", function() {
	var rContent = $("#rContent");
	var rContentVal = rContent.val();
	var rNameVal = $("#rName").val();
	
	$.ajax({
		type : "post",
		url : "${cp}/reply/write",
		headers : {
			"Content-type" : "application/json",
			"X-HTTP-Method-Override" : "POST"
		},
		dataType : "text",
		data : JSON.stringify({
			"bId" : bId,
			"rContent" : rContentVal,
			"rName" : rNameVal,
			"mId" : mIdVal
		}),
		success : function(result) {
			if (result == "register Success") {
				alert("댓글이 등록 되었습니다");
				fn_getList(pageNum);
				rContent.val(""); // 댓글 내용 초기화
			}
		},
		error: function(request, status, error) {
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
});

// 댓글 수정 폼 출력
function fn_updateForm(rId){
	var html = "";
	var buttons = "";

	html += "<textarea id='editContent' class='form-control' rows='3'>";
	html += "</textarea>"; 
	
	buttons += "<a href='#' onClick='fn_update(" + rId+")'>완료</a>&nbsp;"
	buttons += "<a href='#' onClick='fn_getList("+pageNum+")'>취소</a>";
	

	$("#"+rId+"content").replaceWith(html);
	$("#"+rId+"buttons").replaceWith(buttons);
}

//댓글 수정
function fn_update(rId) {
	var html = "";
	var rContentVal = $("#editContent").val();

	$.ajax({
		type : "post",
		url : "${cp}/reply/modify",
		headers : {
			"Content-type" : "application/json",
			"X-HTTP-Method-Override" : "POST"
		},
		dataType : "text",
		data : JSON.stringify({
			"rId" : rId,
			"bId" : bId,
			"rContent" : rContentVal,
			"rName" : $("#rName").val(),
			"mId" : mIdVal
		}),
		success : function(result) {
			if (result == "modify Success") {
				alert("댓글이 수정되었습니다");
			}
			fn_getList(pageNum);
		},
		error: function(request, status, error) {
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

//댓글 삭제
function fn_remove(rId) {
	$.ajax({
		type : "post",
		url : "${cp}/reply/remove/",
		headers : {
			"Content-type" : "application/json",
			"X-HTTP-Method-Override" : "POST"
		},
		dataType : "text",
		data : JSON.stringify({
			"rId" : rId
		}),
		success : function(result) {
			if (result == "remove Success") {
				alert("댓글이 삭제 되었습니다");
			}
			fn_getList(pageNum);
		},
		error: function(request, status, error) {
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});	
}

// 댓글 페이징 출력
function fn_printPaging(pagination){
	var html = "";
	if(pagination.prev){
		html += "<li class='page-item'><a href='' onClick='fn_getList(" + (pagination.startPage - 1) +")' class='page-link' href='#'>&lt;</a></li>";
	}
	
	for(var i = pagination.startPage, len = pagination.endPage; i <= len; i++){
		html += "<li class='page-item";
		
		if(pagination.curPage == i){
			html += " active";
			console.log("true -> " + i);
		}
		
		html += "'><a href='#' class='page-link' onClick='fn_getList("+i+")'>"+i+"</a></li>";
		
	}
	
	if(pagination.next){
		html += "<li class='page-item'><a href='#' onClick='fn_getList(" + (pagination.endPage + 1) + ")'class='page-link' href='"+(pagination.endPage + 1)+"' class='page-link' href='#'>&gt;</a></li>";
	}
	$(".pagination").html(html);
}

// 댓글 목록 함수
function fn_getList(pageNum){
	$.ajax({
		type: "post",
		url: "${cp}/reply/list/" + pageNum,
		data: {"bId" : bId},
		dataType: "json",
		success: function(res){
			var html = "";
			
			if(res.reply.length < 1){
				html = "등록된 댓글이 없습니다";
			}else{
				$(res.reply).each(function(){
					var date = new Date(this.rDate);
					html += "<br/>";
					html += "<div><table id='"+ this.rId +"' class='table'>"
						 + "<h6><strong>"+ this.rName +"</strong>&nbsp;";
					html += "<small>" + dateToStr(date) +"</small>&nbsp;";
					if(mIdVal == this.mId){
						html += "<span id='" + this.rId+ "buttons'>";
						html += "<a href='#' onClick='fn_updateForm("+ this.rId + ")'>수정</a>&nbsp;"
						html += "<a href='#' onClick='fn_remove(" + this.rId+ ")'>삭제</a>";
					}
					html += "</span></h6>";
					html += "<div id='"+this.rId+"content'>"+this.rContent + "</div><tr><td></td></tr>";
					html += "</table></div>";
				});
			}
			$("#replyList").html(html);

			fn_printPaging(res.pagination);
		}
	});
}

// 댓글 날짜 출력
function dateToStr(format){
    var year = format.getFullYear();
    var month = format.getMonth() + 1;
    if(month<10) month = '0' + month;
    var date = format.getDate();
    if(date < 10) date = '0' + date;
    var hour = format.getHours();
    if(hour < 10) hour = '0' + hour;
    var min = format.getMinutes();
    if(min < 10) min = '0' + min;
    var sec = format.getSeconds();
    if(sec < 10) sec = '0' + sec;

    return year + "-" + month + "-" + date + " " + hour + ":" + min + ":" + sec;
}
</script>
