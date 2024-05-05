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
        },
        error: function(xhr, status, error){
            console.error("Error:", status, error);
        }
    });
}
