<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(function() {
		$($("input[type='button']")[0]).click(function() {
			searchMountain();
		});
		$($("input[type='button']")[1]).click(function(){
			regist();
		});
	});

	//비동기 요청!!
	function searchMountain() {
		$.ajax({
			url : "/admin/mountain/list",
			type : "get",
			data : {
				name : $($("form").find("input[name='name']")).val()
			},
			success : function(result) {
				var json = JSON.parse(result);
				var obj;
				if (json.length < 1) {
					alert("해당 정보가 없습니다!");
				} else if (json.length == 1) {
					obj = json[0];
					//alert("결정된 산의 정보 :" + obj.detail);
					setData(obj);
				} else {
					//alert("넘겨받은 json배열의 길이는"+json.length);
					var n = prompt(json.length
							+ " 건이 발견되었습니다.\n 원하시는 산의 번호를 선택하세요.[0~"
							+ (json.length - 1) + "]");
					obj = json[n];
					//alert("결정된 산의 정보 :" + obj.detail);
					setData(obj);

				}
				
			}
		});
	}
//산의 주소와 상세정보 채워넣기
function setData(obj){
	$($("form").find("input[name='addr']")).val(obj.addr);
	$($("form").find("textarea[name='detail']")).val(obj.detail);
}

function regist(){
	//alert("눌렀네");
	$("form").attr({
		method:"post",
		action:"/admin/mountain/regist"
	});
	$("form").submit();
}
</script>
</head>
<body>

	<h3>Contact Form</h3>

	<div class="container">
		<form enctype="multipart/form-data">
			<input type="text" 	     name="name" placeholder="산 이름" style="width: 85%">
			<input type="button" 	 value="공공데이터 검색" /> 
			<input type="text"	 	 name="addr" placeholder="주소">
			<textarea 					 name="detail" placeholder="상세 정보"	style="height: 200px"></textarea>
			<input type="file" name="myFile"/> 
			<input type="text" name="lati"	 placeholder="위도" value = "0"/>
			 <input type="text" name="longi" placeholder="경도" value="0"/>
			  <select  name="marker">
				<option value="">마커 선택</option>
				<option value="pin1.png">Blue Marker</option>
				<option value="pin2.png">Red Marker</option>
				<option value="pin3.png">Green Marker</option>
				<option value="pin4.png">RED</option>
				<option value="pin5.png">GREEN</option>
			</select>
			 <input type="button" value="등록">
			 <input type="button"	value="목록">
		</form>
	</div>

</body>
</html>
