<%@page import="blood.BloodManager"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
</style>
<meta charset="utf-8">
<title>Insert title here</title>
<script>
	function send() {
		var form = document.querySelector("form");
		form.method="post";
		form.action="/movie.do";
		form.submit();
	}
</script>
</head>
<body>
	<form>
		<select name="movie">
			<option>영화 선택</option>
			<option value="미션임파서블5">미션임파서블5</option>
			<option value="스타워즈">스타워즈</option>
			<option value="존윅3">존윅3</option>
			<option value="분노의질주">분노의질주</option>
		</select>
		<button type="button" onClick="send()">분석보기</button>
	</form>
</body>
</html>