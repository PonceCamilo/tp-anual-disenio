import React from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

function LoginForm({ onRegisterClick }) {
  return (
    <Form>
      <Form.Group controlId="formBasicEmail">
        <Form.Label>Usuario</Form.Label>
        <Form.Control type="text" placeholder="Ingresar Usuario" />
      </Form.Group>

      <Form.Group className='mt-3' controlId="formBasicPassword">
        <Form.Label>Contraseña</Form.Label>
        <Form.Control type="password" placeholder="Contraseña" />
      </Form.Group>

      <Form.Group className='mt-3' controlId="formBasicCheckbox">
        <Form.Check type="checkbox" label="Recuérdame"/>
      </Form.Group>
      <div className="d-grid gap-2">
        <Button className='mt-3' variant="primary" type="submit">
          Iniciar Sesión
        </Button>
        <Button className='mt-1' variant="outline-primary" onClick={onRegisterClick}>
          Registrarme
        </Button>
      </div>
    </Form>
  );
}

export default LoginForm;