import React, { useRef, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../assets/styles/CustomContainer.css';

function HeaderApp({ setHeaderHeight }) {
  const headerRef = useRef(null);
  const navigate = useNavigate(); // Hook para manejar la navegación

  useEffect(() => {
    if (headerRef.current) {
      setHeaderHeight(headerRef.current.clientHeight);
    }
  }, [setHeaderHeight]);

  const handleReportIssue = () => {
    navigate('/report-issue'); // Navega a la ruta del formulario
  };
  const handleConsultaCanje = () => {
    navigate('/consulta-canje');
  };

  const handlePublicarProducto = () => {
    navigate('/publicar-producto');
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
      </div>
    </div>
  );
}

export default HeaderApp;