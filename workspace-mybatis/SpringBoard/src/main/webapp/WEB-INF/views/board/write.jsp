<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<link rel="stylesheet" type="text/css" href="/resources/css/mypage.css">
<!-- 스마트에디터 2 -->
<script type="text/javascript"
	src="/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>


<div class="text-center" style="margin-bottom: 150px;">


	<h1>글쓰기 페이지</h1>
	<hr>

	<div>
		<form action="/board/write" method="post" enctype="multipart/form-data">

			<table class="table table-bordered">

				<tr>
					<td class="">제목</td>
					<td><input type="text" name="title" style="width: 100%" /></td>
				</tr>
				<tr>
					<td class="" colspan="2" >문의 내용</td>
				</tr>
				<tr>
					<td colspan="10"><textarea id="content" name="content"
							style="width: 100%; height: 500px;"></textarea></td>
				</tr>
			</table>

			<input type="file" name="file" id="file" />


<!-- 			<button onclick="location.href = '/board/list';">목록</button> -->
<!-- 			<button onclick="location.href = '/board/update';">수정</button> -->
<!-- 			<button onclick="location.href = '/board/delete';">삭제</button> -->
<!-- 			<br> -->

			<button type="button" id="btnWrite">작성</button>

		</form>
	</div>

	<!-- .container -->
</div>

<!-- <textarea>태그에 스마트에디터2 적용하는 스크립트 -->
<script type="text/javascript">
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors,
		elPlaceHolder : "content",
		sSkinURI : "/resources/se2/SmartEditor2Skin.html",
		fCreator : "createSEditor2"
	});
</script>
<script type="text/javascript">
	//<form>태그에 submit이 수행되면 스마트에디터에 작성한 내용을 <textarea>에 반영한다
	function submitContents(elClickedObj) {

		//에디터의 내용을 #content에 반영해준다
		oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);

		try {
			//<form>태그의 submit을 수행한다
			elClickedObj.form.submit();
		} catch (e) {
		}
	}
</script>
<script type="text/javascript">
	$(document).ready(function() {

		//수정버튼 동작
		$("#btnWrite").click(function() {
			//스마트 에디터의 내용을 <textarea>에 적용하는 함수를 호출한다
			submitContents($("#btnWrite"))
			//<form> submit
			$("form").submit();
		});


	});
</script>


</div>
<!-- .container -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />