import React, { useRef, useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../assets/styles/CustomContainer.css';
import HeaderImage from '../assets/imgs/Header.png';
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
    <div ref={headerRef} className='header-container d-flex flex-row justify-content-around align-items-center vh-100'>
      <div className='title-container'>
        <h1 className='manrope-font fw-bold pb-4'>Heladeras <br></br>Comunitarias</h1>
        <p className='text-wrap fs-5 fw-normal text-break w-50'>
          Un espacio solidario para compartir alimentos con quienes más lo necesitan.
        </p>
        <div className='btn-group mt-4'>
          <button className='btn btn-dark btn-lg me-3 flex-grow-1'>Doná</button>
          <button className='btn btn-dark btn-lg flex-grow-1'>Sé Voluntario</button>
        </div>
      </div>

      <div className='image-container'>
        <img src={HeaderImage} alt='Descripción' className='img-fluid' />
      </div>
    </div>
  );
}

export default HeaderApp;