<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
        /* 문자셋 설정 */
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
%>



    <jsp:useBean id="dto" class="Ch02.TestDto" scope="request" />
    <jsp:setProperty name="dto" property="StirngtoDate" param="Date"/>
    <jsp:setProperty name="dto" property="*" />
    
    <%
    /*
    	request 내장객체 : 요청정보값(Http Request Protocol의 구조형태)
    */
    System.out.println("dto :  " + dto);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<h1>뿡빵삥 Form_Post_with_Dto</h1>
TestDto :
<%=dto %><br />
</body>
</html>

