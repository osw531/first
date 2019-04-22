<%@page import="java.util.List"%>
<%@page import="com.itbank.model.domain.Mountain"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	List<Mountain> mtList = (List)request.getAttribute("mtList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
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
<table>
  <tr>
    <th>NO</th>
    <th>이미지</th>
    <th>산 이름</th>
    <th>주소</th>
    <th>위도</th>
    <th>경도</th>
    <th>사용 마커</th>
  </tr>
  <%for(int i=0;i<mtList.size();i++){%>
  <%Mountain mt = mtList.get(i); %>
  <tr>
  	<td><img src="/data/<%=mt.getFilename()%>" width="70px"></td>
    <td><%=mt.getName() %></td>
    <td><%=mt.getAddr() %></td>
    <td><%=mt.getLati() %></td>
    <td><%=mt.getLongi() %></td>
    <td><img src="/images/marker/<%=mt.getMarker()%>"></td>
  </tr>
  <%}%>
</table>
</body>
</html>
