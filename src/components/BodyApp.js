import React from 'react';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import FoodAnimation from './FoodAnimation';
import './css/CustomContainer.css';

function BodyApp({ headerHeight }) {
  return (
    <Container className='custom-container' fluid>
      <Row className="justify-content-center align-items-center" style={{ minHeight: `calc(100vh - ${headerHeight}px)` }}>
        <Col md={6} className="d-flex justify-content-center flex-column align-items-center">
          <FoodAnimation />
        </Col>
        <Col className="d-flex justify-content-center align-items-center" md={6}>
          <article className='text-center align-items-center' style={{ maxWidth: '350px' }}>
            <h2>¿Qué son las heladeras comunitarias?</h2>
            <p>
              Son una iniciativa de la organización sin fines de lucro, que tiene como objetivo principal mejorar el acceso alimentario en entornos de vulnerabilidad socioeconómica. Consisten en la instalación de heladeras comunitarias en puntos estratégicos de la ciudad, donde los vecinos pueden donar y tomar alimentos de forma gratuita.
            </p>
          </article>
        </Col>
      </Row>
    </Container>
  );
}

export default BodyApp;
