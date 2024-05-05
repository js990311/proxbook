$(document).ready(function(){
    // 페이지 로드 시 위치 정보 요청
    getLocation();
});

function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(sendPositionToServer);
    } else {
        console.error("Geolocation is not supported by this browser.");
    }
}

function sendPositionToServer(position) {
    let latitude = position.coords.latitude;
    let longitude = position.coords.longitude;
    let endpoints = `/library/prox-library`;

    // 위치 정보를 JSON 형식으로 변환
    let locationData = JSON.stringify({latitude: latitude, longitude: longitude});

    console.log(locationData);

    // 서버로 위치 정보를 POST 요청으로 보내기
    $.ajax({
        url: endpoints,
        type: "POST",
        contentType: "application/json",
        data: locationData,
        success: function(response){
            console.log("서버 응답:", response);
            $('#result').html(response)
            libraryMapMarker();
        },
        error: function(xhr, status, error){
            console.error("Error:", status, error);
        }
    });
}

function libraryMapMarker(){
    let libraryCodes = [];
    $("#result li").each((idx,item)=>{
        libraryCodes.push(item.id);
    });
    console.log(libraryCodes);

    let jsonData = JSON.stringify({libraryCodes : libraryCodes});

    $.ajax({
        url: "/library/prox-library/geo",
        type: "POST",
        contentType: "application/json",
        data: jsonData,
        success: function (response){
            console.log(response);
            kakaoMapMaker(response["libraries"]);
        },
        error: function (xhr, status, error){
            console.error("Error:", status, error);
        }
    })
}

function kakaoMapMaker(libraries){
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition((position)=>{
            kakao.maps.load(
                ()=>{
                    // 지도 생성
                    let mapDiv = document.getElementById("map");
                    let options = {
                        center: new kakao.maps.LatLng(
                            position.coords.latitude,
                            position.coords.longitude
                        ),
                        level: 7
                    }
                    let map = new kakao.maps.Map(mapDiv, options);

                    // 도서관 마커
                    libraries.forEach(library=>{
                        let markerPosition = new kakao.maps.LatLng(
                            library['latitude'],
                            library['longitude']
                        );

                        let marker = new kakao.maps.Marker({
                            position: markerPosition,
                            clickable: true
                        });

                        // 마커를 지도에 표기
                        marker.setMap(map);

                        // 마커 클릭시 윈도우
                        let infoWindow = new kakao.maps.InfoWindow({
                            content: library['name'],
                            removable : true
                        });
                        kakao.maps.event.addListener(marker, 'click', ()=>{
                            infoWindow.open(map, marker);
                        })

                    });
                }
            )
        });
    } else {
        console.error("Geolocation is not supported by this browser.");
    }

}