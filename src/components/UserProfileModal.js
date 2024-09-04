import React from 'react';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import { useNavigate } from 'react-router-dom'; // Importa useNavigate
import LaDonate from '../assets/iconos/LaDonate.svg';
import Form from '../assets/iconos/Form.svg';
import Vianda from '../assets/iconos/Heladera.svg';
import Suscripcion from '../assets/iconos/Suscripcion.svg';
import FallaTecnica from '../assets/iconos/FallaTecnica.svg';
import Canje from '../assets/iconos/Canje.svg';
import Config from '../assets/iconos/Config.svg';

function UserProfileModal({ show, onHide }) {
  const navigate = useNavigate(); // Define el hook de navegación

  const handleNavigate = (path) => {
    navigate(path); // Redirige a la ruta especificada
    onHide(); // Cierra el modal después de la redirección
  };

  return (
    <Modal show={show} onHide={onHide}>
      <Modal.Header className='w-100 d-flex justify-content-center' closeButton>
        <Modal.Title>Opciones de Usuario</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Row>
          <Col xs={4} className="text-center h-100">
            <Button variant="light" className="d-flex flex-column align-items-center justify-content-center w-100 h-100"
              onClick={() => handleNavigate('/donacion-dinero')}>
              <img src={LaDonate} alt="Donar Dinero" width="40" height="40" />
              Donar Dinero
            </Button>
          </Col>
          <Col xs={4} className="text-center h-100">
            <Button variant="light" className="d-flex flex-column align-items-center justify-content-center w-100 h-100"
              onClick={() => handleNavigate('/donacion-vianda')}>
              <img src={Vianda} alt="Donar Vianda" width="40" height="40" />
              Donar Vianda
            </Button>
          </Col>
          <Col xs={4} className="text-center h-100">
            <Button variant="light" className="d-flex flex-column align-items-center justify-content-center w-100 h-100"
              onClick={() => handleNavigate('/registro-vulnerable')}>
              <img src={Form} alt="Registrar Persona Vulnerable" width="40" height="40" />
              Registrar Persona Vulnerable
            </Button>
          </Col>
        </Row>
        <Row>
          <Col xs={4} className="text-center h-100">
            <Button variant="light" className="d-flex flex-column align-items-center justify-content-center w-100 h-100"
              onClick={() => handleNavigate('/suscribirse')}>
              <img src={Suscripcion} alt="Suscribirse" width="40" height="40" />
              Suscribirse
            </Button>
          </Col>
          <Col xs={4} className="text-center h-100">
            <Button variant="light" className="d-flex flex-column align-items-center justify-content-center w-100 h-100"
              onClick={() => handleNavigate('/report-issue')}>
              <img src={FallaTecnica} alt="Reportar Falla" width="40" height="40" />
              Reportar Falla Técnica
            </Button>
          </Col>
          <Col xs={4} className="text-center h-100">
            <Button variant="light" className="d-flex flex-column align-items-center justify-content-center w-100 h-100"
              onClick={() => handleNavigate('/consulta-canje')}>
              <img src={Canje} alt="Canjear" width="40" height="40" />
              Consultar o Canjear productos
            </Button>
          </Col>
        </Row>
        <Row>
          <Col xs={4} className="text-center h-100">
            <Button variant="light" className="d-flex flex-column align-items-center justify-content-center w-100 h-100"
              onClick={() => handleNavigate('/configuracion')}>
              <img src={Config} alt="Configuracion" width="40" height="40" />
              Configuración
            </Button>
          </Col>
          <Col xs={4} className="text-center h-100">
            <Button variant="light" className="d-flex flex-column align-items-center justify-content-center w-100 h-100"
              onClick={() => handleNavigate('/opcion-7')}>
              <img src="" alt="" width="40" height="40" />
            </Button>
          </Col>
          <Col xs={4} className="text-center h-100">
            <Button variant="light" className="d-flex flex-column align-items-center justify-content-center w-100 h-100"
              onClick={() => handleNavigate('/opcion-8')}>
              <img src="" alt="" width="40" height="40" />
            </Button>
          </Col>
        </Row>
      </Modal.Body>
    </Modal>
  );
}

export default UserProfileModal;
