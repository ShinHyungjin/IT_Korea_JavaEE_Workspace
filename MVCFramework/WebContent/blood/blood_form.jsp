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
		form.action="/blood.do";
		form.submit();
	}
</script>
</head>
<body>
	<form>
		<select name="blood">
			<option>혈액형 선택</option>
			<option value="A">A형</option>
			<option value="B">B형</option>
			<option value="O">O형</option>
			<option value="AB">AB형</option>
		</select>
		<button type="button" onClick="send()">분석보기</button>
	</form>
</body>
</html>