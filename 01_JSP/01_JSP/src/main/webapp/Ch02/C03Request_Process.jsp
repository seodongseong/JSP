<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="Ch02.ProfileDto" %>
    <%
    /*
    	request 내장객체 : 요청정보값(Http Request Protocol의 구조형태)
    */
    /* 문자셋 설정 */
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    
    String username = request.getParameter("username");
    String age = request.getParameter("age");
    String addr = request.getParameter("addr");
    ProfileDto dto = new ProfileDto(username,age,addr);
    System.out.printf("dto : " + dto);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<h1>뿡빵삥 Form_Post_with_Dto</h1>
	ProfileDto : <%=dto %><br/>
	
	이름 : <%=username %><br/>
	나이 : <%=age %><br/>
	주소 : <%=addr %><br/>

</body>
</html>

