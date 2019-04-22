<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.itbank.model.domain.Board"%>
<%@page import = "com.itbank.common.pager.Pager" %>
<%!
	Pager pager = new Pager();
%>
<%
	List<Board> boardList =  (List)request.getAttribute("boardList");
	out.print("게시물 갯수"+boardList.size());
	pager.init(request,boardList.size());
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
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
  background-color: #f2f2f2
}
</style>
</head>
<body>

<h2>Zebra Striped Table</h2>
<p>For zebra-striped tables, use the nth-child() selector and add a background-color to all even (or odd) table rows:</p>

<table>
  <tr>
    <th>No</th>
    <th>제목</th>
    <th>작성자</th>
    <th>등록일</th>
    <th>조회수</th>
  </tr>
  <%int num = pager.getNum();%>
  <%int curPos = pager.getCurPos(); %>
  <%for(int i=0;i<pager.getPageSize();i++){ %>
  <%Board board = boardList.get(curPos++); %>
  <%if(num<1) break;%>
  <tr>	
    <td><%=num--%></td>
    <td><a href="/board/detail?board_id=<%=board.getBoard_id()%>"><%=board.getTitle() %></a></td>
    <td><%=board.getWriter() %></td>
    <td><%=board.getRegdate() %></td>
    <td><%=board.getHit() %></td>
  </tr>
  <%} %>
  <tr>
  	<td colspan="5">
  		<%for(int i=pager.getFirstPage();i<=pager.getLastPage();i++){ %>
  		<%if(i>pager.getTotalPage())break; %>
  			<a href = "/board/list?currentPage=<%=i%>">[<%=i%>]</a>
  		<%} %>
  	</td>
  </tr>
  <tr>
  	<td colspan="5">
  		<button onClick="location.href='/board/write.jsp';">등록</button>
  	</td>
  </tr>
</table>

</body>
</html>
