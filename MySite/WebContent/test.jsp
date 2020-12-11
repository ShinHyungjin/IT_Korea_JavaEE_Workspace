<%@ page contentType="text/html;charset=utf-8"%>
<html>
<head>
<%@ page 
  import="java.sql.*,
				javax.sql.*,
				java.io.*,
				javax.naming.InitialContext,
				javax.naming.Context"
%>
</head>
<body>
	<h1>JDBC JNDI Resource Test</h1>

	<%
		InitialContext initCtx = new InitialContext();	//검색 객체
	DataSource ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/myoracle");
		
	Connection conn = ds.getConnection(); //새로운 접속이 아닌 기존 풀에 접속한 객체를 대여하는것
	Statement stmt = conn.createStatement();
	ResultSet rset = stmt.executeQuery("select * from board");
	while (rset.next()) {
		out.println("title ==" + rset.getString("title")+"<br>");
	}
	rset.close();
	stmt.close();
	conn.close(); //대여했던 객체를 다시 풀로 돌려보냄
	initCtx.close();
	%>
</body>
</html>