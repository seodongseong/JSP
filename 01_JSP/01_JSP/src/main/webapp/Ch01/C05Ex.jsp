<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %> 
<!-- Scanner 사용을 위한 설정 -->

<%
	// 입력 받기
	Scanner sc = new Scanner(System.in);
	// 입력받을 때 출력물
	System.out.print("몇 단? ");
	// 입력받은 데이터 담을 변수선언
	int dan = sc.nextInt();
	// Scanner 닫기
	sc.close();
%>  
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<table>
		<summary><%=dan+"단" %></summary>
		<tbody>
		<%
		for(int i=1;i<10;i++)
		{
		%>
			<tr>
				<td><%=dan %></td>
				<td>x</td>
				<td><%=i %></td>
				<td>=</td>
				<td><%=dan*i %></td>
			</tr>
		<%
		}
		%>
		
		</tbody>
	</table>


</body>
</html>