import React, { useState, useEffect } from 'react';
import { useJsApiLoader, GoogleMap, Marker, InfoWindow } from '@react-google-maps/api';
import SearchMapApp from './SearchMapApp';

const center = {
  lat: -34.5994039,
  lng: -58.435489
};

function MapApp() {
  const { isLoaded } = useJsApiLoader({
    googleMapsApiKey: process.env.REACT_APP_GOOGLE_MAPS_API_KEY
  });

  const [locations, setLocations] = useState([]);
  const [selectedLocation, setSelectedLocation] = useState(null);
  const [zoom, setZoom] = useState(15);

  useEffect(() => {
    const fetchLocations = async () => {
      try {
        const response = await fetch('http://localhost:8080/ubicaciones-googlemaps');
        const data = await response.json();
        setLocations(data);
      } catch (error) {
        console.error('Error al obtener las ubicaciones:', error);
      }
    };

    fetchLocations();
  }, []);
 
  const handleMarkerClick = (location) => {
    setSelectedLocation(location);
    setZoom(17);
  };

  const handleCloseInfoWindow = () => {
    setSelectedLocation(null);
  };

  if (!isLoaded) {
    return <div>Loading...</div>;
  }

  return (
    <div style={{ position: 'relative', width: '100%', height: '100vh' }}>
      <GoogleMap
        center={center}
        zoom={zoom}
        mapContainerStyle={{ width: '100%', height: '100%' }}
      >
        {locations.map((location) => (
          <Marker
            key={location.nombre}
            position={{ lat: location.latitud, lng: location.longitud }}
            onClick={() => handleMarkerClick(location)}
          />
        ))}

        {selectedLocation && (
          <InfoWindow
            position={{ lat: selectedLocation.latitud, lng: selectedLocation.longitud }}
            onCloseClick={handleCloseInfoWindow}
          >
            <div>
              <h3>{selectedLocation.nombre}</h3>
              <p>{selectedLocation.provincia}</p>
            </div>
          </InfoWindow>
        )}
      </GoogleMap>
      <SearchMapApp />
    </div>
  );
}

export default MapApp;
