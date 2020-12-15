<%@page import="admin.member.Admin"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
		//세션에서 데이터 꺼내기
		out.print("당신이 사용하고 있는 세션 객체는 : " + session);
		Admin admin = (Admin)session.getAttribute("ad");
		
		if(admin != null) {
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="/admin/inc/head.jsp" %>
</head>
<body>
<div><%=admin.getMid()%>님 로그인 중
		<a href="/admin/logout.jsp">로그아웃</a>
</div>
<%@ include file="/admin/inc/topnavi.jsp" %>

<div style="padding-left:16px">
  <h2>Top Navigation Example</h2>
  <p>Some content..</p>
</div>

</body>
</html>
<%}else {%>
	<script>
		alert("로그인이 안되있음");
		history.back();
	</script>
	<%}%>