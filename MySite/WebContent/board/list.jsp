<%@page import="board.model.MybatisBoardDAO"%>
<%@page import="common.board.Pager"%>
<%@page import="board.model.Board"%>
<%@page import="java.util.List"%>
<%@page import="board.model.BoardDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%	
	//DB연동	
	MybatisBoardDAO dao = new MybatisBoardDAO();
	Pager pager = new Pager();	// 페이징 처리를 클래스로 정의해서 사용하자!
	List<Board> list = dao.selectAll();
	pager.init(request, list);
	
	String realPath = application.getRealPath("/data");
	realPath = realPath.replace("\\", "/");
	
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
	<%int num = pager.getNum(); %>
	<%int curPos = pager.getCurPos(); %>
	<%for(int i=1;i<=pager.getPageSize();i++){ %>
	<%
		//데이터 추출
		if(num<1)
			break;
		Board board=list.get(curPos++); 
	%>
  <tr>
    <td><%=num-- %></td>
    <!-- <td><img src="<%=realPath%>/<%=board.getFilename()%>" width="20px" height="20px"></td> -->
    <td><img src="/data/<%=board.getFilename()%>" width="100px" height="80px"></td>
    <td><a href="detail.jsp?board_id=<%=board.getBoard_id()%>"><%=board.getTitle() %></td>
    <td><%=board.getWriter() %></td>
	<td><%=board.getRegdate() %></td>
	<td><%=board.getHit() %></td>
  </tr>
	<%} %>  
 <tr>
	<td colspan="6" style="text-align:center"> 
		
		<%if((pager.getFirstPage()-1)>=1){ //페이지가 있다면...%>
			<a href="list.jsp?currentPage=<%=pager.getFirstPage()-1%>">◀</a>
		<%}else{%>
			<a href="javascript:alert('처음 페이지입니다');">◀</a>
		<%} %>
		
		<%for(int i=pager.getFirstPage();i<=pager.getLastPage();i++){%>
		<%if(i>pager.getTotalPage())break; //페이지를 출력하는 i 가 총페이지수에 넘어설때 반복문 빠져나와라...%>		
		<a href="list.jsp?currentPage=<%=i %>"   <%if(pager.getCurrentPage()==i){%>class="pageNum"<%}%> >[<%=i %>]</a>
		<%} %>
		
		<%if((pager.getLastPage()+1)<pager.getTotalPage()){%>
			<a href="list.jsp?currentPage=<%=pager.getLastPage()+1%>">▶</a>		
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