
// This sets the map and style
    mapboxgl.accessToken = MAPBOX_ACCESS_TOKEN;
    var map = new mapboxgl.Map({
    container: 'map', // container ID
});
    map.setStyle('mapbox://styles/mapbox/dark-v10')
    map.setZoom(12)
    map.setCenter([-98.4916, 29.4252])
    map.addControl(new mapboxgl.NavigationControl());

    // Map marker
    var marker = new mapboxgl.Marker({
        color: '#007c6f',
    })
        .setLngLat([-98.4861, 29.4260])
        .setDraggable(true)
        .addTo(map);
    marker.on('dragend', function() {
        // Events popup here
    })
// Search function
    function mapSearch(input) {
        geocode(input, MAPBOX_ACCESS_TOKEN).then(function (results){
            marker.setLngLat(results);
            map.flyTo({
                center: [results[0], results[1]],
                zoom: 12,
                speed: 2,
                essential: true,
            });

            // use these coords for events
            var newCoords = {
                lat: results[1],
                lng: results[0]
            }
        })
    }
// Runs function on click
    var button2 = document.querySelector('#btn')
    button2.addEventListener('click', function (e) {
        e.preventDefault();
        var searchResult = document.querySelector('#mapSearch').value;
        mapSearch(searchResult);
    });