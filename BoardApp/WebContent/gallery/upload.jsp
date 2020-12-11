<%@page import="java.io.File"%>
<%@page import="common.file.FileManager"%>
<%@page import="java.io.IOException"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
		
		request.setCharacterEncoding("utf-8");
		
		String saveDirectory = "D:/IT_Korea_Class/JavaEE/BoardApp/WebContent/data";
		int maxSize = 2*1024*1024;
		
		long time = System.currentTimeMillis();
		
		try {
		MultipartRequest multi = new MultipartRequest(request, saveDirectory,maxSize,"utf-8");
		String msg = multi.getParameter("msg");
		out.print(msg);
		
		String ori = multi.getOriginalFileName("photo");
		out.print(ori);
		
		String ext = FileManager.getExtend(ori);
		String filename = time+"."+ext;
		
		File file = multi.getFile("photo");
		file.renameTo(new File(saveDirectory+"/"+filename));
		
		//response.sendRedirect("/gallery/photo_list.jsp");
		out.print("업로드 완료");
		
		}catch(IOException e) {
			e.printStackTrace();
			out.print("업로드 용량이 너무 큽니다");
		}
%>
