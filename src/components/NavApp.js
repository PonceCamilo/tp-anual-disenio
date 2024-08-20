import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import Container from 'react-bootstrap/Container';
import UTNlogo from '../assets/logos/utn.svg';
import IcoProfile from '../assets/iconos/IcoProfile.svg';
import IcoHeladera from '../assets/iconos/IcoHeladera.svg';
import LoginModal from './LoginModal';
import '../assets/styles/CustomContainer.css'; 

function NavApp() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [showModal, setShowModal] = useState(false);

  const handleLoginClick = () => {
    setShowModal(true);
  };

  return (
    <>
      <Navbar className="Nav-Bar"expand="lg" fixed="top">
        <Container>
          <Navbar.Brand as={Link} to="/" className="fs-3 d-flex align-items-center">
            <img
              alt=""
              src={UTNlogo}
              width="30"
              height="30"
              className="d-inline-block align-top me-2"
            />
            {' Heladeras DDS'}
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse className="justify-content-end" id="basic-navbar-nav">
            <Nav>
              {isAuthenticated ? (
                <Nav.Link href="#profile" className="fs-5">
                  <img
                    alt=""
                    src={IcoProfile}
                    width="25"
                    height="25"
                    className="d-inline-block me-2"
                  />
                  {' Nombre del Usuario'}
                </Nav.Link>
              ) : (
                <Nav.Link href="#login" onClick={handleLoginClick} className="fs-5">
                  <img
                    alt=""
                    src={IcoProfile}
                    width="30"
                    height="30"
                    className="d-inline-block me-2"
                  />
                  {' Ingresar'}
                </Nav.Link>
              )}
              <Nav.Link as={Link} to="/map" className="fs-5">
                <img
                  alt=""
                  src={IcoHeladera}
                  width="30"
                  height="30"
                  className="d-inline-block me-2"
                />
                {' Heladeras'}
              </Nav.Link>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
      <LoginModal show={showModal} onHide={() => setShowModal(false)} />
    </>
  );
}
export default NavApp;