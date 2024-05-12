const kakaoMap = () => {
    kakao.maps.load(()=>{
        // 지도 생성
        let $map = $("#map");
        let mapDiv = $map[0];
        let userLatitude = $map.data("lat");
        let userLongitude = $map.data("lng");
        $map.removeAttr("data-lat").removeAttr("data-lng");
        let options = {
            center: new kakao.maps.LatLng(
                userLatitude,
                userLongitude
            ),
            level: 7
        }
        let map = new kakao.maps.Map(mapDiv, options);

        // 마커 생성
        $("#lib-ul li").each(function() {
            let latitude = $(this).data("lat");
            let longitude = $(this).data("lng");

            let markerPosition = new kakao.maps.LatLng(
                latitude,
                longitude
            );
            let marker = new kakao.maps.Marker({
                position: markerPosition,
                clickable: true
            });

            // 마커를 지도에 표기
            marker.setMap(map);

            // 마커의 정보를 추출 후 window에 보이기
            let libraryName = $(this).find("p").first().text();
            let infoWindow = new kakao.maps.InfoWindow({
                content: libraryName,
                removable : true
            });
            kakao.maps.event.addListener(marker, 'click', ()=>{
                infoWindow.open(map, marker);
            })

            // li 클릭 시 이벤트
            this.onclick = () => {
                map.setCenter(
                    marker.getPosition()
                );
                map.setLevel(3);
            };
        });

        $("#lib-ul li").removeAttr("data-lat").removeAttr("data-lng");
    }); /* end of kakao.maps.load*/
}

// shorten url 복사 로직
const copyUrl = () => {
    let path = $("#shorten-url").text();
    let url = `${window.location.host}${path}`;
    navigator.clipboard.writeText(url)
        .then(function() {
            console.log(url);
        })
        .catch(function(err) {
            console.error('clipboard copy error', err);
        });
}