import React, { useState, useEffect } from 'react';
import { useJsApiLoader, GoogleMap, Marker, InfoWindow, } from '@react-google-maps/api';
import SearchMapApp from './SearchMapApp';
import IcoAlerta from '../assets/iconos/IcoAlerta.svg';

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
  const [mapCenter, setMapCenter] = useState(center);
  const [zoom, setZoom] = useState(15);
  const accessToken = localStorage.getItem('accessToken');
  useEffect(() => {
    const fetchLocations = async () => {
      try {
        const response = await fetch('http://localhost:8080/ubicaciones-googlemaps', {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
          },
          credentials: 'include' 
        });
        if (!response.ok) {
          throw new Error('Error de Red');
        }
        const data = await response.json();
        console.log(data);
        setLocations(data);
      } catch (error) {
        console.error('Error al obtener las ubicaciones:', error);
      }
    };
  
    fetchLocations();
  }, []);

  const handleMarkerClick = (location) => {
    setSelectedLocation(location);
    setMapCenter({ lat: location.latitud, lng: location.longitud });
    setZoom(17);
  };

  const handleCloseInfoWindow = () => {
    setSelectedLocation(null);
  };

  const handleSearch = (query) => {
    const location = locations.find(loc => loc.nombre.toLowerCase() === query.toLowerCase());
    if (location) {
      setSelectedLocation(location);
      setMapCenter({ lat: location.latitud, lng: location.longitud });
      setZoom(17);
    }
  };

  if (!isLoaded) {
    return <div>Loading...</div>;
  }

  const getMarkerIcon = (heladeraFuncionando) => {
    return heladeraFuncionando ? undefined : IcoAlerta;
  }

  return (
    <div style={{width: '100%', height: '100vh' }}>
      <GoogleMap
        center={mapCenter}
        zoom={zoom}
        mapContainerStyle={{ width: '100%', height: '100%' }}
      >
        {locations.map((location) => (
          <Marker
            key={location.nombre}
            position={{ lat: location.latitud, lng: location.longitud }}
            onClick={() => handleMarkerClick(location)}
            icon={{
              url: getMarkerIcon(location.funcionando),
              scaledSize: new window.google.maps.Size(40, 40),
            }}
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
      <SearchMapApp onSearch={handleSearch} />
    </div>
  );
}

export default MapApp;