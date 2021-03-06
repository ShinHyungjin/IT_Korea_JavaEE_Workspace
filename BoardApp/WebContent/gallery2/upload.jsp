<%@page import="common.file.FileManager"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.DefaultFileItemFactory"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
	String saveDir = "D:/IT_Korea_Class/JavaEE/BoardApp/WebContent/data";
int maxSize = 2 * 1024 * 1024;

//업로드 객체를 생성해주는 팩토리 객체 (주로 설정담당 : 경로,용량제한 등)
DefaultFileItemFactory itemFactory = new DefaultFileItemFactory();
itemFactory.setRepository(new File(saveDir)); // 저장될 위치(임시 디렉토리이므로 최종 write될 때 참조되는 물리적 위치가 아님)
itemFactory.setSizeThreshold(maxSize);

//실제 업로드를 수행하는 객체	
ServletFileUpload upload = new ServletFileUpload(itemFactory);
request.setCharacterEncoding("utf-8");
//request에는 현재 input type 2개의 정보가 담겨있고 이것이 List에는 2개의 FileItem이 담겨짐
List<FileItem> items = upload.parseRequest(request); //업로드 컴포넌트에게 클라이언트의 요청정보를 전달

for (FileItem item : items) {
	if (!item.isFormField()) { //textFiled가 아닌것만 업로드하겠다~
		String ext = FileManager.getExtend(item.getName());
		String filename = System.currentTimeMillis() + "." + ext;

		File file = new File(saveDir+"/"+filename);
		item.write(file);
		out.print("<br>보고서 작성 <br><br>");
		out.print("원래 파일명 : " + item.getName() + "<br>");
		out.print("생성된 파일 이름 : " + filename + "<br>");
		out.print("저장될 파일의 크기 : " + item.getSize() + "bytes <br>");
		out.print("저장될경로 : " + saveDir + "<br>");
	} else { // textFiled는 파라미터와 값 출력
		out.print(item.getFieldName() + "의 값은 ");
		out.print(item.getString() + "<br>");
	}
}
%>
