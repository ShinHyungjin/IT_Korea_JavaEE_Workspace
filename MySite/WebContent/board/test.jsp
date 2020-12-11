<%@ page contentType="text/html;charset=utf-8"%>
<% 

String realPath = application.getRealPath("/data"); //웹사이트의 루트를 기준으로 특정 파일이나, 디렉토리를 명시하면, 스스로 현재 
// 웹사이트가 얹혀진 os로부터 풀경로를 구해온다
out.print(realPath);
%>