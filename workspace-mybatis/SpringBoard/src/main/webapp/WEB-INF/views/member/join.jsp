<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	$("#cancel").click(function() {
		history.go(-1);
	})
	
	$("#id").focus();
})
</script>

<div class="container">

<h1>회원가입</h1>
<hr>

<div>
<form action="/member/join" method="post" class="form-horizontal">
<div class="form-group">
    <label for="id" class="col-xs-3 control-label">아이디 : </label>
    <div class="col-xs-6">
      <input type="text" class="form-control" id="id" name="id" placeholder="Id">
    </div>
</div>
<div class="form-group">
    <label for="pw" class="col-xs-3 control-label">패스워드 : </label>
    <div class="col-xs-6">
      <input type="text" class="form-control" id="pw" name="pw" placeholder="Password">
    </div>
</div>
<div class="form-group">
    <label for="nick" class="col-xs-3 control-label">닉네임 : </label>
    <div class="col-xs-6">
      <input type="text" class="form-control" id="nick" name="nick" placeholder="Nick">
    </div>
</div>
<div class="form-group">
    <div class="col-xs-offset-3">
      <button class="btn btn-primary">회원가입</button>
      <input type="reset" id="cancel" class="btn btn-danger" value="취소" />
    </div>
</div>
</form>
</div>

</div><!-- .container -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />