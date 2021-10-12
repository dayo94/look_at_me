<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<script type="text/javascript">
$(document).ready(function() {


	
});


</script>

</head>
<body>



<form action="/update" method="post" enctype="multipart/form-data">
<input type="hidden" name="user_no" value="${user_info.user_no }" />

<table>
<tr><td>이메일</td><td>${user_info.user_email }</td></tr>
<tr>
	<td>비밀번호</td>
	<td><input type="text" name="user_password" value="${user_info.user_password }"/></td>
</tr>
<tr>
	<td>닉네임</td>
	<td><input type="text" name="user_nickname" value="${user_info.user_nickname }"/></td>
</tr>
<tr><td>포인트</td><td>${user_info.user_point }</td></tr>
<tr><td>이름</td><td>${user_info.user_name }</td></tr>
<tr><td>생년월일</td><td>${user_info.user_birth }</td></tr>

</table>



<!-- 첨부파일 -->
<!-- <div> -->
<!-- 	<div id="beforeFile"> -->
<!-- 		기존 첨부파일:  -->
<%-- 		<a href="/upload/${attachmentFile.stored_file_name}" download="${attachmentFile.original_file_name }">${attachmentFile.original_file_name }</a> --%>
<!-- 	</div> -->
	
	<div>
	<div id="beforeFile">
		기존 첨부파일: 
		<a href="/upload/${attachmentFile.profile_name}" download="${attachmentFile.profile_name }">${attachmentFile.profile_name }</a>
	</div>

	<div id="afterFile">
		새 첨부파일:
		<input type="file" name="file" />
	</div>
</div>

<button>수정</button>

</form>




<!-- <input type="file" id="profile" accept="img/*" required multiple> -->
<!-- <button id="btn">사진첨부</button> -->


<!-- <h3>이미지 미리보기</h3> -->
<!-- <div id="imagePreview"> -->
<!-- 	<img id="img" /> -->
<!-- </div> -->

</body>
</html>