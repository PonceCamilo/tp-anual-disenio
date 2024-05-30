import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes, useLocation } from 'react-router-dom';
import NavApp from './components/NavApp';
import HeaderApp from './components/HeaderApp';
import BodyApp from './components/BodyApp';
import MapApp from './components/MapApp';

function App() {
  const [headerHeight, setHeaderHeight] = useState(0);

  return (
    <Router>
      <NavApp />
      <Main setHeaderHeight={setHeaderHeight} headerHeight={headerHeight} />
    </Router>
  );
}

function Main({ setHeaderHeight, headerHeight }) {
  const location = useLocation();

  return (
    <>
      {location.pathname !== '/map' && (
        <>
          <HeaderApp setHeaderHeight={setHeaderHeight} />
        </>
      )}
      <Routes>
        <Route path="/" element={<BodyApp headerHeight={headerHeight} />} />
        <Route path="/map" element={<MapApp />} />
      </Routes>
    </>
  );
}

export default App;

