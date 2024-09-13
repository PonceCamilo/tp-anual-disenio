import React, { useState, useEffect, useRef } from 'react';
import { BrowserRouter as Router, Route, Routes, useLocation } from 'react-router-dom';
import { useAuth0 } from '@auth0/auth0-react';
import NavApp from './components/NavApp';
import HeaderApp from './components/HeaderApp';
import MapApp from './components/MapApp';
import ReportIssuePage from './pages/ReportIssuePage.js';
import ConsultaCanjePage from './pages/ConsultaCanjePage.js';
import PublicarProductoPage from './pages/PublicarProductoPage.js';
import RegistrarVulnerable from './pages/RegistrarVulnerablePage.js';
import RecomendarPuntos from './pages/RecomendarPuntosPage.js';
import Reportes from './pages/ReportesPage.js';
import InfoApp from './components/InfoApp';
import DonacionDineroPage from './pages/DonacionDineroPage.js';
import DonacionViandaPage from './pages/DonacionViandaPage.js';
import CargarHeladeraPage from './pages/CargarHeladeraPage.js';
import DistribucionViandasPage from './pages/DistribucionViandasPage.js';
import SuscripcionHeladeraPage from './pages/SuscripcionHeladeraPage.js';

function App() {
  const [headerHeight, setHeaderHeight] = useState(0);

  return (
    <Router>
      
      <Main setHeaderHeight={setHeaderHeight} headerHeight={headerHeight} />
    </Router>
  );
}

function Main({ setHeaderHeight, headerHeight }) {
  const location = useLocation();
  const { isAuthenticated, loginWithRedirect } = useAuth0(); 
  const isMapPage = location.pathname === '/map';

  const ProtectedRoute = ({ children }) => {
    if (!isAuthenticated) {
      loginWithRedirect();
      return null; 
    }
    return children;
  };

  return (
    <>
      <NavApp className={isMapPage ? 'map-page' : ''} />
      {location.pathname === '/' && (
        <div className="header-info-container">
          <HeaderApp setHeaderHeight={setHeaderHeight} />
          <div >
            <InfoApp/>
          </div>
        </div>
      )}
      <Routes>
        {/* Rutas públicas */}
        <Route path="/" element={<></>} />
        <Route path="/map" element={<MapApp />} />
        
        {/* Rutas protegidas */}
        <Route path="/report-issue" element={<ProtectedRoute><ReportIssuePage /></ProtectedRoute>} />
        <Route path="/consulta-canje" element={<ProtectedRoute><ConsultaCanjePage /></ProtectedRoute>} />
        <Route path="/publicar-producto" element={<ProtectedRoute><PublicarProductoPage /></ProtectedRoute>} />
        <Route path="/registro-vulnerable" element={<ProtectedRoute><RegistrarVulnerable /></ProtectedRoute>} />
        <Route path="/recomendar-puntos" element={<ProtectedRoute><RecomendarPuntos /></ProtectedRoute>} />
        <Route path="/reportes" element={<ProtectedRoute><Reportes /></ProtectedRoute>} />
        <Route path="/donacion-dinero" element={<ProtectedRoute><DonacionDineroPage /></ProtectedRoute>} />
        <Route path="/donacion-vianda" element={<ProtectedRoute><DonacionViandaPage /></ProtectedRoute>} />
        <Route path="/cargar-heladera" element={<ProtectedRoute><CargarHeladeraPage /></ProtectedRoute>} />
        <Route path="/distribucion-viandas" element={<ProtectedRoute><DistribucionViandasPage /></ProtectedRoute>} />
        <Route path="/suscripcion-heladera" element={<ProtectedRoute><SuscripcionHeladeraPage /></ProtectedRoute>} />
      </Routes>
    </>
  );
}

export default App;
