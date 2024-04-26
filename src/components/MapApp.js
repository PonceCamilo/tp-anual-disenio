import React from 'react';
import { useJsApiLoader, GoogleMap } from '@react-google-maps/api';
import SearchMapApp from './SearchMapApp';
const center = {
  lat: -34.5994039,
  lng: -58.435489
};

function MapApp() {
  const { isLoaded } = useJsApiLoader({
    googleMapsApiKey: process.env.REACT_APP_GOOGLE_MAPS_API_KEY
  });

  if (!isLoaded) {
    return <div>Loading...</div>;
  }

  return (
    <div style={{ position: 'relative',width: '100%', height: '100vh' }}>
      <GoogleMap
        center={center}
        zoom={15}
        mapContainerStyle={{ width: '100%', height: '100%' }}
      >
        {/* Contenido del mapa aquí */}
      </GoogleMap>
      <SearchMapApp/>
      
    </div>
  );
}

export default MapApp;
