import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import Container from 'react-bootstrap/Container';
import UTNlogo from '../assets/logos/utn.svg';
import IcoProfile from '../assets/iconos/IcoProfile.svg';
import IcoHeladera from '../assets/iconos/IcoHeladera.svg';
import Logout from '../assets/iconos/Logout.svg';
import { useAuth0 } from '@auth0/auth0-react'; 
import '../assets/styles/CustomContainer.css';
import UserProfileModal from './UserProfileModal'; // Importar el nuevo componente

function NavApp({ className }) {
  const { loginWithRedirect, logout, isAuthenticated, user } = useAuth0(); 
  const [expanded, setExpanded] = useState(false);
  const [showProfileModal, setShowProfileModal] = useState(false); // Estado para el modal

  const handleResize = () => {
    if (window.innerWidth >= 992 && expanded) {
      setExpanded(false);
    }
  };

  const handleNavLinkClick = () => {
    setExpanded(false); 
  };

  const handleProfileClick = () => {
    setShowProfileModal(true); // Mostrar modal al hacer clic en el perfil
  };

  const handleCloseProfileModal = () => {
    setShowProfileModal(false); // Cerrar modal
  };

  useEffect(() => {
    window.addEventListener('resize', handleResize);
    return () => {
      window.removeEventListener('resize', handleResize);
    };
  }, [expanded]);

  useEffect(() => {
    if (isAuthenticated && user) {
      console.log("Usuario logueado:", {
        nombre: user.name,
        email: user.email,
        sub: user.sub 
      });
    }
  }, [isAuthenticated, user]); 

  return (
    <>
      <Navbar
        className={`Nav-Bar ${className}`}
        expand="lg"
        fixed="top"
        expanded={expanded}
        onToggle={() => setExpanded(!expanded)}
        style={{ zIndex: 1050 }}
      >
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
                <>
                  <Nav.Link href="#profile" onClick={handleProfileClick} className="fs-5">
                    <img
                      alt=""
                      src={user.picture || IcoProfile}
                      width="25"
                      height="25"
                      className="d-inline-block me-2 rounded-circle"
                    />
                    {user.name}
                  </Nav.Link>
                </>
              ) : (
                <Nav.Link href="#login" onClick={loginWithRedirect} className="fs-5">
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
              <Nav.Link as={Link} to="/map" onClick={handleNavLinkClick} className="fs-5">
                <img
                  alt=""
                  src={IcoHeladera}
                  width="30"
                  height="30"
                  className="d-inline-block me-2"
                />
                {' Heladeras'}
              </Nav.Link>
              
              {isAuthenticated && (
                <>
                  <Nav.Link href="#logout" onClick={() => logout({ returnTo: window.location.origin })} className="fs-5">
                    <img 
                      alt=""
                      src={Logout}
                      width="30"
                      height="30"
                      className="d-inline-block me-2"></img>
                    Cerrar sesión
                  </Nav.Link>
                </>
              )}
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
      <UserProfileModal show={showProfileModal} onHide={handleCloseProfileModal} />
    </>
  );
}

export default NavApp;
