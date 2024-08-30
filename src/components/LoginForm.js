import React, { useState } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Alert from 'react-bootstrap/Alert';

function LoginForm({ onRegisterClick }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('');
  const [loginError, setLoginError] = useState('');

  const handleLogin = (event) => {
    event.preventDefault();
    if (username && password && role) {
      authenticateUser(username, password, role);
    } else {
      setLoginError('Por favor, completa todos los campos.');
    }
  };

  const authenticateUser = (username, password, role) => {
    // ACA HAY QUE CONECTAR EL BACK-END PARA VALIDAR LAS CREDENCIALES 
    setTimeout(() => {
      if (username === 'admin' && password === 'admin123' && role === 'administrador') {
        alert('Administrador autenticado');
      } else if (username === 'colab' && password === 'colab123' && role === 'colaborador') {
        alert('Colaborador autenticado');
      } else if (username === 'tech' && password === 'tech123' && role === 'tecnico') {
        alert('Técnico autenticado');
      } else {
        setLoginError('Credenciales incorrectas.');
      }
    }, 1000);
  };

  return (
    <Form onSubmit={handleLogin}>
      <Form.Group controlId="formBasicEmail">
        <Form.Label>Usuario</Form.Label>
        <Form.Control
          type="text"
          placeholder="Ingresar Usuario"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
        />
      </Form.Group>

      <Form.Group className='mt-3' controlId="formBasicPassword">
        <Form.Label>Contraseña</Form.Label>
        <Form.Control
          type="password"
          placeholder="Contraseña"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
      </Form.Group>

      <Form.Group className='mt-3' controlId="formRole">
        <Form.Label>Rol</Form.Label>
        <Form.Control
          as="select"
          value={role}
          onChange={(e) => setRole(e.target.value)}
          required
        >
          <option value="" disabled>Selecciona tu rol</option>
          <option value="administrador">Administrador</option>
          <option value="colaborador">Colaborador</option>
          <option value="tecnico">Técnico</option>
        </Form.Control>
      </Form.Group>

      {loginError && <Alert variant="danger" className="mt-3">{loginError}</Alert>}

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