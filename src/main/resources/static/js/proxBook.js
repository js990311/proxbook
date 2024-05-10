$(document).ready(function(){
    $('#prox-book').on("click", getLocation);
});

function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(libraryBookQuery);
    } else {
        console.error("Geolocation is not supported by this browser.");
    }
}


const libraryBookQuery = (position) => {
    let endpoints = `/book/prox-book`;

    let bookId = $("#book-id").data("id");
    let latitude = position.coords.latitude;
    let longitude = position.coords.longitude;

    let data = {
        bookId: bookId,
        latitude : latitude,
        longitude: longitude,
    };

    let jsonData = JSON.stringify(data);
    console.log(jsonData);

    $.ajax({
        url: endpoints,
        type: "POST",
        contentType: "application/json",
        data: jsonData,
        success: function(response){
            console.log("서버 응답:", response);
            $('#result').html(response);
            kakaoMap();
        },
        error: function(xhr, status, error){
            console.error("Error:", status, error);
        }
    });
}
