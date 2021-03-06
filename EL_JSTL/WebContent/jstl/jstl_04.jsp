<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>c:if</h1>
<h3>조건문</h3>
<hr>

<c:if test="true">
<h3>TRUE: 무조건 실행</h3>
</c:if>

<c:if test="false">
<h3>FALSE: 무조건 실행하지 않음</h3>
</c:if>

<% if( true ) { %>
	<h3>TRUE: 무조건 실행</h3>
<% } %>

<hr>

<c:if test="단순 문자열">
<h3>실행? 실행될까요?</h3>
</c:if>

<%-- true또는 false가 아닌 문자열이 들어가면 false로 처리된다 --%>


<c:if test="${10 eq 10 } ">
<h3>출력! 출력출력출력</h3>
<h3>test속성값 마지막에 빈칸 넣지 않도록 조심하자!</h3>
</c:if>

<c:if test="${10 eq 10}">
<h3>출력! 출력출력출력</h3>
<h3>test속성값 마지막에 빈칸 넣지 않도록 조심하자!</h3>
</c:if>

속성값---"${10 eq 10 } "---

<hr>

<%-- Controller(서블릿)에서 MODEL값을 DB에서 조회하여
	View(JSP)에 전달한 상황 --%>
<%	request.setAttribute("userList", null); %>


<%-- 조회결과가 없을 경우 처리 --%>
<c:if test="${empty userList }">
<h3>조회된 데이터가 없습니다</h3>
</c:if>

<%-- 조회결과가 있을 경우 처리 --%>
<c:if test="${not empty userList }">
<table>
<tr>
	<td>유저정보</td>
</tr>
</table>
</c:if>

</body>
</html>



















