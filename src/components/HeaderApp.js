import React, { useRef, useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../assets/styles/CustomContainer.css';
import LoadCSVModal from './LoadCSVModal';

function HeaderApp({ setHeaderHeight }) {
  const headerRef = useRef(null);
  const navigate = useNavigate(); // Hook para manejar la navegación
  const [showCSVModal, setShowCSVModal] = useState(false);

  useEffect(() => {
    if (headerRef.current) {
      setHeaderHeight(headerRef.current.clientHeight);
    }
  }, [setHeaderHeight]);

  const handleReportIssue = () => {
    navigate('/report-issue');
  };
  const handleConsultaCanje = () => {
    navigate('/consulta-canje');
  };

  const handlePublicarProducto = () => {
    navigate('/publicar-producto');
  };

  const handleCargaCSV = () => {
    navigate('#carga-csv');
    setShowCSVModal(true);
  }
  const handleRegistrarVulnerable = () => {
    navigate('/registro-vulnerable');
  };

  return (
    <div ref={headerRef} className='header-container d-flex flex-column justify-content-center align-items-center vh-100'>
      <h1 className='font-monospace mb-5'>Heladeras Comunitarias</h1>
      <div className='d-flex flex-column gap-3'>
        <button className='btn btn-outline-dark btn-lg'>Doná</button>
        <button className='btn btn-outline-dark btn-lg'>Sé Voluntario</button>
        <button className='btn btn-outline-dark btn-lg' onClick={handleReportIssue}>Reportar Falla</button>
        <button className='btn btn-outline-dark btn-lg' onClick={handleConsultaCanje}>Consulta y Canje</button>
        <button className='btn btn-outline-dark btn-lg' onClick={handlePublicarProducto}>Publicar Producto/Servicio</button>
        <button className='btn btn-outline-dark btn-lg' onClick={handleCargaCSV}>Cargar datos antiguos</button>
        <button className='btn btn-outline-dark btn-lg' onClick={handleRegistrarVulnerable}>Registrar persona vulnerable</button>
      </div>
      <LoadCSVModal show={showCSVModal} onHide={() => {setShowCSVModal(false); navigate('')}} />
    </div>
  );
}

export default HeaderApp;