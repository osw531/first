<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta chasrset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

input[type=text], select, textarea {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
}

input[type=button] {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=button]:hover {
	background-color: #45a049;
}

.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(function(){
	$($("input[type='button']")[0]).click(function(){
		regist();
	});
});
function regist(){
	$("form").attr({
		action:"/board/write",
		method:"post"	
	});
	$("form").submit();
}
</script>
</head>
<body>
	<h3>Write Form</h3>

	<div class="container">
		<form>
			<input type="text" 	name="writer"      placeholder="Your name.."> 
			<input type="text"	name="title"         placeholder="Your last name.."> 
			<textarea                 name="content"    placeholder="Write something.." style="height: 200px"></textarea>
			<input type="button" value="등록">
			<input type="button" value="목록">
		</form>
	</div>
</body>
</html>
