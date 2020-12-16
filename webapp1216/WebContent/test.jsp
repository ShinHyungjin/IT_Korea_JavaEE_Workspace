<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@ page contentType="text/html;charset=utf-8"%>
<% 
	InitialContext context = new InitialContext(); // JNDI 검색 객체
	DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/mariadb"); // 톰켓 서버에 설정한 리소스 찾기
	
	//찾은 커넷션 풀을 이용하여 커넥션 한개를 추출하면 성공
	Connection con = ds.getConnection();
	out.print(con);


%>