import React, { useRef, useEffect } from 'react';
import { Container } from 'react-bootstrap';
import Button from 'react-bootstrap/Button';
import './css/CustomContainer.css';

function HeaderApp({ setHeaderHeight }) {
  const headerRef = useRef(null);

  useEffect(() => {
    if (headerRef.current) {
      setHeaderHeight(headerRef.current.clientHeight);
    }
  }, [setHeaderHeight]);
  
  return (
    <div ref={headerRef} className='text-center' style={{ marginTop: '65px' }}>
      <h1 className="font-monospace">Heladeras Comunitarias</h1>
      <h3>
        Sistema para la mejora del acceso alimentario<br />
        en Contextos de Vulnerabilidad Socioeconómica
      </h3>
      <Container fluid className='d-flex bg-primary justify-content-around'>
        <Button className='fs-4 shadow-lg p-2' variant="primary" style={{ minWidth: '200px' }}>
          Se voluntario
        </Button>
        <Button className='fs-4 shadow-lg p-2' variant="primary" style={{ minWidth: '200px' }}>
          Doná
        </Button>
      </Container>
    </div>
  );
}

export default HeaderApp;
