import React from 'react';
import { Container, Card, Row, Col } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import LaDonate from '../assets/iconos/LaDonate.svg';
import Heladera from '../assets/iconos/Heladera.svg';
import Food from '../assets/iconos/Food.svg';

function FormasColaborarGrid() {
  const navigate = useNavigate();

  const formasColaborar = [
    {
      id: 1,
      titulo: 'Donación de Dinero',
      descripcion: 'Contribuye con una donación monetaria.',
      imagen: LaDonate,
      link: '/registro-vulnerable'
    },
    {
      id: 2,
      titulo: 'Donación de Viandas',
      descripcion: 'Prepara y dona viandas para quienes lo necesitan.',
      imagen: Heladera,
      link: '/publicar-producto'
    },
    {
      id: 3,
      titulo: 'Distribución de Viandas',
      descripcion: 'Ayuda a distribuir viandas entre diferentes puntos.',
      imagen: Food,
      link: '/consulta-canje'
    },
    {
      id: 4,
      titulo: 'Distribución de Viandas',
      descripcion: 'Ayuda a distribuir viandas entre diferentes puntos.',
      imagen: Heladera,
      link: '/report-issue'
    },
  ];

  const handleCardClick = (link) => {
    navigate(link);
  };

  return (
    <Container fluid>
      <Row xs={1} md={2} lg={4} className="mt-5 g-4">
        {formasColaborar.map((forma) => (
          <Col key={forma.id} className="d-flex justify-content-center align-items-stretch">
            <Card
              className="text-center h-100"
              onClick={() => handleCardClick(forma.link)}
              style={{ cursor: 'pointer' }} // Cambia el cursor para indicar que es clickeable
            >
              <Card.Img
                className="mx-auto mt-4 mb-4"
                style={{ width: '5rem', height: '5rem', objectFit: 'contain' }}
                variant="top"
                src={forma.imagen}
              />
              <Card.Body className="d-flex flex-column justify-content-between">
                <Card.Title className="mt-3">{forma.titulo}</Card.Title>
                <Card.Text className="" style={{ maxWidth: '20rem' }}>
                  {forma.descripcion}
                </Card.Text>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>
    </Container>
  );
}

export default FormasColaborarGrid;
