<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>

<h1>세계 유명산 정보</h1>

<div id="googleMap" style="width:100%;height:400px;"></div>

<script>
var googleMap;
var map;
var myCenter;

function myMap() {
   googleMap = document.getElementById("googleMap");
   myCenter= new google.maps.LatLng(37.701277, 127.015684);
   map = new google.maps.Map(googleMap,{center:myCenter,zoom:9});
//비동기로 데이터 가져오기!!
   $.ajax({
      url:"/rest/mountains",
      type:"get",
      success:function(result){
         //alert("서버에서 받아온 산의 목록 수는 : "+result.length);
         loadMap(result);
      }
   });
}
function loadMap(jsonArray){
   for(var i=0;i<jsonArray.length;i++){
      var json = jsonArray[i];
      var marker = new google.maps.Marker({
          position: new google.maps.LatLng(json.lati, json.longi),
          title:json.name,
          id:i
      });
      marker.setMap(map);//마커맵에 반영 

      //마커에 리스너 연결!!
      google.maps.event.addListener(marker,'click',function() {
		showInfo(this,jsonArray);
      });
   }
}
//정보 윈도우 창 띄우기
function showInfo(marker,jsonArray){
	var infowindow = new google.maps.InfoWindow({
		content:"<img src='/data/"+jsonArray[marker.id].filename+"'>"+jsonArray[marker.id].detail
		
	});
	infowindow.open(map, marker); //정보창 띄우기
	
}
</script>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA6VEnQaiT_Z_bqCTTiGz5Nx4bOiF4dYnU&callback=myMap"></script>

</body>
</html>