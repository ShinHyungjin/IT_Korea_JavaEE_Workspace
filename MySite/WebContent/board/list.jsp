<%@page import="board.model.Board"%>
<%@page import="java.util.List"%>
<%@page import="board.model.BoardDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%	
	//DB연동	
	BoardDAO dao = new BoardDAO();
	List<Board> list = dao.selectAll();
	
	String realPath = application.getRealPath("/data");
	realPath = realPath.replace("\\", "/");
	
	int totalRecord=list.size(); //총 레코드 수
	int pageSize=10; //한 페이지당 보여질 레코드 수
	int totalPage =(int)Math.ceil((float)totalRecord/pageSize);// 총 페이지수
	int blockSize=10; //한 블럭당 보여질 페이지 수
	int currentPage=1; //현재 페이지
	
	//숫자화
	if(request.getParameter("currentPage")!=null){ //페이지를 넘겼다면	
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int firstPage = currentPage%blockSize==0?currentPage-blockSize+1:currentPage/blockSize*blockSize+1; // 반복문의 시작값
	int lastPage = firstPage+blockSize-1; // 반복문의 끝값
	int num=totalRecord - (currentPage-1)*pageSize; // 페이지당 시작 번호
	int curPos = (currentPage-1)*pageSize; //페이지당 List에서의 시작 index
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}
th, td {
  text-align: left;
  padding: 16px;
}
tr:nth-child(even) {
  background-color: #f2f2f2;
}
img{
	box-sizing:border-box;
}
a{
	text-decoration:none;
}
.pageNum{
	font-size:20pt;
	color:red;
	font-weight:bold;
}
</style>
<script>
</script>
</head>
<body>
<table>
  <tr>
    <th>No</th>
    <th>이미지</th>
    <th>제목</th>
    <th>작성자</th>
	<th>등록일</th>
	<th>조회수</th>
  </tr>

	<%for(int i=1;i<=pageSize;i++){ %>
	<%if(num<1)break; %>
	<%
		//데이터 추출
		Board board=list.get(curPos++); 
	%>
  <tr>
    <td><%=num-- %></td>
    <!-- <td><img src="<%=realPath%>/<%=board.getFilename()%>" width="20px" height="20px"></td> -->
    <td><img src="/data/<%=board.getFilename()%>" width="100px" height="80px"></td>
    <td><%=board.getTitle() %></td>
    <td><%=board.getWriter() %></td>
	<td><%=board.getRegdate() %></td>
	<td><%=board.getHit() %></td>
  </tr>
	<%} %>  
 <tr>
	<td colspan="6" style="text-align:center"> 
		
		<%if((firstPage-1)>=1){ //페이지가 있다면...%>
			<a href="list.jsp?currentPage=<%=firstPage-1%>">◀</a>
		<%}else{%>
			<a href="javascript:alert('처음 페이지입니다');">◀</a>
		<%} %>
		
		<%for(int i=firstPage;i<=lastPage;i++){%>
		<%if(i>totalPage)break; //페이지를 출력하는 i 가 총페이지수에 넘어설때 반복문 빠져나와라...%>		
		<a href="list.jsp?currentPage=<%=i %>"   <%if(currentPage==i){%>class="pageNum"<%}%> >[<%=i %>]</a>
		<%} %>
		
		<%if((lastPage+1)<totalPage){%>
			<a href="list.jsp?currentPage=<%=lastPage+1%>">▶</a>		
		<%}else{%>
			<a href="javascript:alert('마지막 페이지입니다');">▶</a>
		<%}%>								
	</td>
   </tr>
	
	<tr>	
	<td colspan="6" > 
		<button onClick="location.href='regist_form.jsp'">글등록</button>
	</td>
  </tr>

</table>
</body>
</html>