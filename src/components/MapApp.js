import React from 'react'
import {useJsApiLoader,GoogleMap} from '@react-google-maps/api'
import Container from 'react-bootstrap/Container';

const center = {
  lat: 37.7749,
  lng: -122.4194
}

function MapApp() {
  const {isLoaded} = useJsApiLoader({
    googleMapsApiKey: process.env.REACT_APP_GOOGLE_MAPS_API_KEY
  })
  if(!isLoaded) {
    return <div>Loading...</div>
  }
  return (
    <div style={{ width: '100%', height: '100vh' }}>
    <GoogleMap
      center={center}
      zoom={15}
      mapContainerStyle={{ width: '100%', height: '100%' }}
    >
    </GoogleMap>
    </div>
  );
    

}

export default MapApp

