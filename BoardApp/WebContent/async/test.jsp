<%@ page contentType="text/html;charset=utf-8"%>
<%
	int count = 0;
	for(int i=0; i<10000; i++) {
		count++;
	}
	out.print("서버의 실행결과 : " + count);
	
%>
