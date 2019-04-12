<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	RuntimeException ex = (RuntimeException)request.getAttribute("err");
	out.print(ex.getMessage());
%>
</body>
</html>