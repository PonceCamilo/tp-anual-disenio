import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import UTNlogo from '../assets/logos/utn.svg';
import IcoProfile from '../assets/iconos/IcoProfile.svg';
import IcoHeladera from '../assets/iconos/IcoHeladera.svg';
import LoginModal from './LoginModal';
function NavApp() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [showModal, setShowModal] = useState(false);
  const handleLoginClick = () => {
    setShowModal(true);
  };

  return (
    <>
      <Navbar expand="lg" fixed="top" bg="primary" data-bs-theme="primary">
        <Container>
          <Navbar.Brand as={Link} to="/">
            <img
              alt=""
              src={UTNlogo}
              width="30"
              height="30"
              className="d-inline-block align-top"
            />{' Heladeras DDS'}
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse className="justify-content-end" id="basic-navbar-nav">
            <Nav>
            {isAuthenticated ? (
                <Nav.Link href="#profile">
                  <img
                    alt=""
                    src={IcoProfile}
                    width="25"
                    height="25"
                    className="d-inline-block"
                  />{' Nombre del Usuario'}
                </Nav.Link>
              ) : (
                <Nav.Link href="#login" onClick={handleLoginClick}>
                  <img
                    alt=""
                    src={IcoProfile}
                    width="25"
                    height="25"
                    className="d-inline-block"
                  />{' Ingresar'}
                </Nav.Link>
              )}
              <Nav.Link as={Link} to="/map">
                <img
                  alt=""
                  src={IcoHeladera}
                  width="25"
                  height="25"
                  className="d-inline-block"
                />{' Heladeras'}
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
