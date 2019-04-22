<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<body>

	<h1>My First Google Map</h1>

	<div id="googleMap" style="width: 100%; height: 900px;"></div>

	<script>

function myMap() {
	var latLng = new google.maps.LatLng(37.571066, 126.992255);
var mapProp= {
  center:latLng,
  zoom:18,
};
var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
var marker = new google.maps.Marker({
	position: latLng,
	animation:google.maps.Animation.BOUNCE,
	icon:"https://cdn0.iconfinder.com/data/icons/buttery_png/128/big_smile.png"
	});

marker.setMap(map); //맵에 마커 등록!!!


new google.maps.Marker({
	position:  new google.maps.LatLng(37.571423, 126.992255),
	animation:google.maps.Animation.BOUNCE,
	icon:"https://cdn0.iconfinder.com/data/icons/buttery_png/128/big_smile.png"
	}).setMap(map);

new google.maps.Marker({
	position:  new google.maps.LatLng(37.571836, 126.992255),
	animation:google.maps.Animation.BOUNCE,
	icon:"https://cdn0.iconfinder.com/data/icons/buttery_png/128/big_smile.png"
	}).setMap(map);

new google.maps.Marker({
	position:  new google.maps.LatLng(37.571216, 126.992255),
	animation:google.maps.Animation.BOUNCE,
	icon:"https://cdn0.iconfinder.com/data/icons/buttery_png/128/big_smile.png"
	}).setMap(map);
	
var infowindow = new google.maps.InfoWindow({
	  content:"이곳이 <h3 style='color:red'><img src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTaC0GwlHHA2DidMj50Vec2SR4IUaj_nUW1lgBTppttSiSv386dxQ'></h3>이다!"
	});

	// Zoom to 9 when clicking on marker
	google.maps.event.addListener(marker,'click',function() {
	  map.setCenter(marker.getPosition());
	  infowindow.open(map,marker);
	  var pos = map.getZoom(); //기존에 설정된 줌의 중심점!!
	  map.setZoom(15); 
	  //일정 시간뒤에 무언가 하고 싶을 때
	  window.setTimeout(function() {map.setZoom(pos);},2400);
	});
	
}
</script>

	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC7s3c6u5G3n7koVQkGfBn_qLQarZjjHlc&callback=myMap"></script>

</body>
</html>