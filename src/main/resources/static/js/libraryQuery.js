const libraryQuery = () => {
    if(navigator.geolocation){
        navigator.geolocation.getCurrentPosition((position) => {

            let endpoints = `/library/proxbook`;

            let bookId = document.getElementById("book-id").getAttribute("data-id");
            let data = {
                bookId: bookId,
                latitude : position.coords.latitude,
                longitude: position.coords.longitude,
            };
            let jsonData = JSON.stringify(data);

            console.log(jsonData);

            fetch(endpoints,{
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: jsonData
            })
                .then(resp=>resp.text())
                .then(data=>{
                    console.log("library-query after fetch", data);
                    let resultUrl = document.getElementById("library-result");
                    console.log("resultUrl", resultUrl);
                    resultUrl.innerText = data;
                });
        })
    }else{
        console.log("현재 위치를 찾을 수 없습니다");
    }
}