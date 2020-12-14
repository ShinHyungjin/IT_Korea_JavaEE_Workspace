<%@page import="board.model.MybatisBoardDAO"%>
<%@page import="common.file.FileManager"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="board.model.Board"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp"%>
<%
	MybatisBoardDAO dao = new MybatisBoardDAO();

	DiskFileItemFactory factory = new DiskFileItemFactory();

	String saveDir = application.getRealPath("/data");
	factory.setRepository(new File(saveDir));//임시적으로 사용할 경로
	factory.setSizeThreshold(2 * 1024 * 1024); //2M
	factory.setDefaultCharset("utf-8");

	request.setCharacterEncoding("utf-8");
	//아래의 객체가 업로드된 정보를 가지고 있으므로 ,파라미터 등도 뽑아낼수 있다.
	ServletFileUpload upload = new ServletFileUpload(factory);
	List<FileItem> items = upload.parseRequest(request);//요청 객체로부터 업로드 정보 뽑기!!

	Board board = new Board();
	boolean flag = false; // 업로드 여부를 판단하는 변수
	int result = 0;
	
	for( FileItem item: items){
		if(item.isFormField()){//text 입력기반의 컴포넌트라면...		
			if(item.getFieldName().equals("board_id")){
				board.setBoard_id(Integer.parseInt(item.getString()));
			}else if(item.getFieldName().equals("filename")){
				board.setFilename(item.getString());
			}else if(item.getFieldName().equals("title")){
				board.setTitle(item.getString());
			}else if(item.getFieldName().equals("writer")){
				board.setWriter(item.getString());
			}else if(item.getFieldName().equals("content")){
				board.setContent(item.getString());
			}
		}else{//파일이라면...
			if(item.getName().length() > 0) { // 새로운 파일을 업로드 한다면
				System.out.println(saveDir + "/" + board.getFilename());
				FileManager.deleteFile(saveDir+"/"+board.getFilename()); //기존파일 삭제
				long time = System.currentTimeMillis();
				String newName=time+"."+FileManager.getExtend(item.getName());
				board.setFilename(newName);
				System.out.println(saveDir + "/" + newName);
				
				File file =new File(saveDir+"/"+newName); 
				item.write(file);//하드디스크에 파일 생성
				
				flag = true;
			}
		}
	}
	//오라클에 insert !!!
	if(flag) {	//업로드가 성공이라면
		result = dao.update(board);
		if(result==0) {
			out.print(getMsgBack("수정 실패"));
		}else {
			out.print(getMsgURL("수정 성공", "detail.jsp?board_id="+board.getBoard_id()));
		}
	}
	
%>
