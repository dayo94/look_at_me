<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<style>
ul {
	list-style: none;
	padding: 0;
}

.nav {
	background-color: #eeeeee;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.nav ul {
	display: flex;
	align-items: center;
}

.nav ul img {
	margin-right: 10px;
	width: 25px;
	height: 25px;
	border-radius: 50%;
}

.content {
	display: flex;
	align-content: center;
	align-items: center;
}

.menu {
	width: 200px;
}

.menu h4 {
	font-weight: bold;
	font-size: 20px;
}

.menu span {
	display: block;
}

.profileWrap {
	width: calc(100% - 200px);
	height: 100%;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

.profileWrap img {
	border-radius: 50%;
	width: 80px;
	height: 80px;
}
</style>
</head>
<body>
	<div>
		<div class="nav">
			<h2>마이페이지</h2>
			<ul>
				<li><img
					src="https://search.pstatic.net/common/?src=http%3A%2F%2Fshop1.phinf.naver.net%2F20201110_157%2F1604988552132g3niM_JPEG%2F6124385887958596_1519189238.jpg&type=sc960_832"
					alt="" /></li>
				<li>
					<c:if test="${empty login or not login }">
						<strong>로그인이 필요합니다</strong>
						<br>
						<button onclick='location.href="/login";'>로그인</button>
					</c:if>
					
					<c:if test="${not empty login and login }">
						<strong>${user_info.user_nickname}님, 환영합니다</strong><br>
					<button onclick='location.href="/logout";'>로그아웃</button>
</c:if>
				</li>
			</ul>
		</div>

		<div class="content">
			<ul class="menu">
				<li>
					<h4>프로필 설정</h4>
					<p>
						<a href="/update">프로필 관리</a>
					</p>

				</li>
				<li>
					<h4>나의 활동</h4>
					<p>
						<a href="/board/list">내가 쓴 게시글</a>
					</p>
					<p>
						<a href="">내가 쓴 댓글</a>
					</p>
				</li>
				<li>
					<h4>활동 포인트 조회</h4>
					<p>
						<a href="">나의 활동 포인트</a>
					</p>
				</li>
				<li>
					<h4>즐겨찾기</h4>
					<p>
						<a href="">레시피</a>
					</p>
					<p>
						<a href="">게시글</a>
					</p>
				</li>
				<li>
					<h4>쪽지 확인</h4>
					<p>
						<a href="">받은 쪽지 확인</a>
					</p>
					<p>
						<a href="">보낸 쪽지 확인</a>
					</p>
				</li>
				<li>
					<h4>나의 문의 내역</h4>
					<p>
						<a href="">1:1 문의 내역</a>
					</p>
				</li>
				<li>
					<h4>회원 탈퇴</h4>
				</li>
				<!-- li 형식 반복 -->
			</ul>
			<div class="profileWrap">
				<img
					src="https://search.pstatic.net/common/?src=http%3A%2F%2Fshop1.phinf.naver.net%2F20201110_157%2F1604988552132g3niM_JPEG%2F6124385887958596_1519189238.jpg&type=sc960_832"
					alt="" />
				<p>
					<span>다요바보</span> 님
				</p>
				<button type="button"><a href="/update">내 정보 수정하기</a></button>
				<!-- 페이지 이동이면 <a><span>내 정보 수정하기</span></a> -->
			</div>
		</div>
	</div>
</body>
</html>