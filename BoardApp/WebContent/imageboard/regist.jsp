<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="board.model.ImageBoardDAO"%>
<%@page import="board.model.ImageBoard"%>
<%@page import="common.file.FileManager"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ include file="/inc/lib.jsp" %>
<%!
	String saveDir = "D:/IT_Korea_Class/JavaEE/BoardApp/WebContent/data";
	int maxSize = 3 * 1024 * 1024;
	ImageBoard imageBoard = new ImageBoard();
	ImageBoardDAO dao = new ImageBoardDAO();
	%>
<%
	maxSize = 2 * 1024 * 1024;

	DiskFileItemFactory itemFactory = new DiskFileItemFactory();
	itemFactory.setRepository(new File(saveDir));
	itemFactory.setSizeThreshold(maxSize);
	itemFactory.setDefaultCharset("utf-8");

	request.setCharacterEncoding("utf-8");
	ServletFileUpload upload = new ServletFileUpload(itemFactory);
	List<FileItem> items = upload.parseRequest(request);
	
	for(FileItem item : items) {
		if(item.isFormField()) { // textField라면 db에 넣고~
			if(item.getFieldName().equals("author")) {
				imageBoard.setAuthor(item.getString());
			}else if(item.getFieldName().equals("title")) {
				imageBoard.setTitle(item.getString());
			}else if(item.getFieldName().equals("content")) {
				imageBoard.setContent(item.getString());
			}
			out.print(item.getFieldName()+ "\t" + item.getString()+"<br>");
		}else { // textField가 아니면 업로드~
			String newName = System.currentTimeMillis()+"."+FileManager.getExtend(item.getName());
			String destFile = saveDir+"/"+newName;
			File file = new File(destFile);
			item.write(file);
			
			imageBoard.setFilename(newName);
			out.print("업로드 완료");
			
		}
	}
	int result = dao.insert(imageBoard);
	if(result == 0) {
		out.print(getMsgBack("등록 실패!"));
	} else {
		out.print(getMsgURL("등록 성공!", "list.jsp"));
	}
	
%>
