<%@page import="emp.model.Dept"%>
<%@page import="java.util.List"%>
<%@page import="emp.model.DeptDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	DeptDAO dao = new DeptDAO();
	List<Dept> list = dao.selectAll();
	
%>

<!DOCTYPE html>
<html>
<head>
<style>
</style>
<meta charset="utf-8">
<title>Insert title here</title>
<script>
</script>
</head>
<body>
	<%
		for(int i=0; i<list.size(); i++) { %>
		부서번호 <%=list.get(i).getDeptno()%> 부서명<%= list.get(i).getDname()%> 위치 <%=list.get(i).getLoc()%><br>
		<%}%>
</body>
</html>