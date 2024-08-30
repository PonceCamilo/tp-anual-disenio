import React, { useState, useEffect, useRef } from 'react';
import { BrowserRouter as Router, Route, Routes, useLocation } from 'react-router-dom';
import NavApp from './components/NavApp';
import HeaderApp from './components/HeaderApp';
import MapApp from './components/MapApp';
import ReportIssuePage from './pages/ReportIssuePage.js';
import ConsultaCanjePage from './pages/ConsultaCanjePage.js';
import PublicarProductoPage from './pages/PublicarProductoPage.js';
import RegistrarVulnerable from './pages/RegistrarVulnerablePage.js';
import RecomendarPuntos from './pages/RecomendarPuntosPage.js';
import Reportes from './pages/ReportesPage.js'
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
        <Route path="/" element={<></>} />
        <Route path="/map" element={<MapApp />} /> 
        <Route path="/report-issue" element={<ReportIssuePage />} /> {/* FALTA MODIFICAR*/}
        <Route path="/consulta-canje" element={<ConsultaCanjePage />} /> {/* FALTA MODIFICAR*/}
        <Route path="/publicar-producto" element={<PublicarProductoPage />} /> {/* FALTA MODIFICAR*/}
        <Route path="/registro-vulnerable" element={<RegistrarVulnerable />} /> 
        <Route path="/recomendar-puntos" element={<RecomendarPuntos />} />
        <Route path="/reportes" element={<Reportes />} /> {/* FALTA MODIFICAR*/}
        <Route path="/donacion-dinero" element={<DonacionDineroPage />} />
        <Route path="/donacion-vianda" element={<DonacionViandaPage />} />
        <Route path="/cargar-heladera" element={<CargarHeladeraPage />} /> {/* FALTA MODIFICAR*/}
        <Route path="/distribucion-viandas" element={<DistribucionViandasPage />} />
        <Route path="/suscripcion-heladera" element={<SuscripcionHeladeraPage />} />
      </Routes>
    </>
  );
}

export default App;
