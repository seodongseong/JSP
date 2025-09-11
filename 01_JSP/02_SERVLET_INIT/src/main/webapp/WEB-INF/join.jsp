<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		<!-- JOIN PAGR 표시 -->
		<h1>JOIN PAGE</h1>
		<!-- form의 주소:페이지 주소의 가장 첫번째 요소/join.do method는 post방식으로(사실 잘 모름) -->
		<form action="${pageContext.request.contextPath}/join.do" method="post">
			<div>
				<label>계정명 : </label><span>${username_msg}</span><br/>
				<input name="username"/>
			</div>
			<div>
				<label>패스워드 : </label><span>${password_msg}</span><br/>
				<input type="password" name="password"/>
			</div>
			<div>
				<button>회원가입</button>
			</div>
			
		
		
		</form>

</body>
</html>