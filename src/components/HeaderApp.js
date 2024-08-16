import React, { useRef, useEffect } from 'react';
import './css/CustomContainer.css'; // Si ya no necesitas estilos personalizados, este import puede ser opcional

function HeaderApp({ setHeaderHeight }) {
  const headerRef = useRef(null);

  useEffect(() => {
    if (headerRef.current) {
      setHeaderHeight(headerRef.current.clientHeight);
    }
  }, [setHeaderHeight]);
  
  return (
    
    <div ref={headerRef} className='header-container d-flex flex-column justify-content-center align-items-center vh-100'>
      <h1 className='font-monospace mb-5'>Heladeras Comunitarias</h1>
      <div className='d-flex flex-column gap-3'>
        <button className='btn btn-outline-dark btn-lg'>Doná</button>
        <button className='btn btn-outline-dark btn-lg'>Sé Voluntario</button>
      </div>
    </div>
    
  );
}

export default HeaderApp;
