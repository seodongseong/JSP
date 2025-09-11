<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!
	int n = 0;
	public int countUp(){
		n++;
		return n;
	}
	// 선언문에서는 반복문 분기문 등 절차지향문법 사용 X
/* 	while(true){
		
	} */

%>
<!-- 이건 뭐지 -->
N : <%=countUp() %>
<!-- TOMCAT이 켜져있는동안에는 계속 누적이됨 -->
<!-- 그래서 서버를 껐다 키면 초기화 됨 -->
</body>
</html>