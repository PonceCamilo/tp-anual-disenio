import React, { useState, useEffect, useRef } from 'react';
import { BrowserRouter as Router, Route, Routes, useLocation } from 'react-router-dom';
import { useAuth0 } from '@auth0/auth0-react'; // Importa el hook de Auth0
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
      <NavApp />
      <Main setHeaderHeight={setHeaderHeight} headerHeight={headerHeight} />
    </Router>
  );
}

function Main({ setHeaderHeight, headerHeight }) {
  const location = useLocation();
  const { isAuthenticated, loginWithRedirect } = useAuth0(); // Obtener estado de autenticación y la función de redirección
  const isMapPage = location.pathname === '/map';
  const infoRef = useRef(null);
  const [isVisible, setIsVisible] = useState(false);

  useEffect(() => {
    const observer = new IntersectionObserver(
      ([entry]) => {
        if (entry.isIntersecting) {
          setIsVisible(true);
        }
      },
      { threshold: 0.1 }
    );

    if (infoRef.current) {
      observer.observe(infoRef.current);
    }

    return () => {
      if (infoRef.current) {
        observer.unobserve(infoRef.current);
      }
    };
  }, []);

  // Componente de ruta protegida
  const ProtectedRoute = ({ children }) => {
    if (!isAuthenticated) {
      loginWithRedirect(); // Redirige al usuario a la página de inicio de sesión si no está autenticado
      return null; // Muestra nada mientras redirige
    }
    return children;
  };

  return (
    <>
      <NavApp className={isMapPage ? 'map-page' : ''} />
      {location.pathname === '/' && (
        <div className="header-info-container">
          <HeaderApp setHeaderHeight={setHeaderHeight} />
          <div ref={infoRef}>
            <InfoApp isVisible={isVisible} />
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
