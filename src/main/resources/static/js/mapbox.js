
// This sets the map and style
    mapboxgl.accessToken = MAPBOX_ACCESS_TOKEN;
    var map = new mapboxgl.Map({
    container: 'map', // container ID
});
    map.setStyle('mapbox://styles/mapbox/streets-v11')
    map.setZoom(12)
    map.setCenter([-98.4916, 29.4252])
    map.addControl(new mapboxgl.NavigationControl());

    // Map marker
    var marker = new mapboxgl.Marker({
        color: '#007c6f',
    })

    var popupOptions = {
        anchor: "top",
    }
// Search function
    function mapSearch(input) {
        geocode(input, MAPBOX_ACCESS_TOKEN).then(function (results){
            marker.setLngLat(results);
            map.flyTo({
                center: [results[0], results[1]],
                zoom: 10,
                speed: 2,
                essential: true,
            });
        })
    }
// Runs search function on click
    var button = document.querySelector('#btn')
    button.addEventListener('click', function (e) {
        e.preventDefault();
        var searchResult = document.querySelector('#mapSearch').value;
        mapSearch(searchResult);
    });
    //Displays every event on the map
    mapboxgl.accessToken = MAPBOX_ACCESS_TOKEN;
    let id = $(".eventId");
    let addresses = $(".eventLocation");
    let titles = $(".eventTitle");
    for(let i = 0; i < addresses.length; i++) {
        let loc = addresses[i].innerText;
        let titleText = titles[i].innerText;
        let event = id[i].innerText;
        geocode(loc, MAPBOX_ACCESS_TOKEN).then(function (address) {
            let popup = new mapboxgl.Popup(popupOptions)
                .setHTML(
                    '<a href="/event/' + event + '" style="font-size: 15px; font-weight: bold;">' + titleText + '</a>' +
                    '<p>' + loc + '</p>'
                );
            let marker = new mapboxgl.Marker({
                color: '#007c6f',
            })
                .setLngLat(address )
                .setPopup(popup)
                .addTo(map);
        })
    }
