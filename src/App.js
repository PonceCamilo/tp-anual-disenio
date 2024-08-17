import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes, useLocation } from 'react-router-dom';
import NavApp from './components/NavApp';
import HeaderApp from './components/HeaderApp';
import MapApp from './components/MapApp';
import ReportIssuePage from './pages/ReportIssuePage.js';
import ConsultaCanjePage from './pages/ConsultaCanjePage.js';
import PublicarProductoPage from './pages/PublicarProductoPage.js';
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
        <Route path="/report-issue" element={<ReportIssuePage />} />
        <Route path="/consulta-canje" element={<ConsultaCanjePage />} />
        <Route path="/publicar-producto" element={<PublicarProductoPage />} />
      </Routes>
    </>
  );
}

export default App;