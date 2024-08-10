import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes, useLocation } from 'react-router-dom';
import NavApp from './components/NavApp';
import HeaderApp from './components/HeaderApp';
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
        <Route path="/" element={<></>} />
        <Route path="/map" element={<MapApp />} />
      </Routes>
    </>
  );
}

export default App;

