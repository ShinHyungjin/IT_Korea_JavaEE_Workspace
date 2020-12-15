<%@ page contentType="text/html;charset=utf-8"%>
<% 
	//객체는 자바의 원칙상 제거할 수가 없다 따라서 세션 객체를 제거할 수는없고 무효화할 다른 방법을 사용한다
	session.invalidate();
%>
<script>
	alert("로그아웃");
	location.href="/admin/login_form.jsp";
</script>