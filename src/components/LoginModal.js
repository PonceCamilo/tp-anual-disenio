import React, { useState } from 'react';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import LoginForm from './LoginForm';
import RegistrationForm from './RegistrationForm';

function LoginModal({ show, onHide }) {
  const [isLoginForm, setIsLoginForm] = useState(true);

  const toggleForm = () => {
    setIsLoginForm(!isLoginForm);
  };

  return (
    <Modal show={show} onHide={onHide}>
      <Modal.Header closeButton>
        <Modal.Title>{isLoginForm ? 'Iniciar sesión' : 'Registrarse'}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        {isLoginForm ? (
          <LoginForm onRegisterClick={toggleForm} />
        ) : (
          <RegistrationForm />
        )}
      </Modal.Body>
      <Modal.Footer className="justify-content-center">
        {isLoginForm ? (
          <>
            <Button variant="link">¿Olvidaste tu contraseña?</Button>
            
          </>
        ) : (
          <>
            <Button variant="link" onClick={toggleForm}>¿Ya tienes una cuenta? Inicia sesión</Button>
          </>
        )}
      </Modal.Footer>
    </Modal>
  );
}

export default LoginModal;