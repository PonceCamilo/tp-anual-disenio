import React, { useState } from 'react';
import Form from 'react-bootstrap/Form';

function ContactMethod() {
  const [contactMethod, setContactMethod] = useState('Seleccione...');
  const [contactInfo, setContactInfo] = useState('');

  const handleContactMethodChange = (event) => {
    setContactMethod(event.target.value);
    setContactInfo('');
  };

  let contactInput = null;

  switch (contactMethod) {
    case 'Correo Electrónico':
      contactInput = (
        <Form.Control
          type="email"
          placeholder="Ingrese su correo electrónico"
          value={contactInfo}
          className='mb-3'
          onChange={(event) => setContactInfo(event.target.value)}
        />
      );
      break;
    case 'Whatsapp':
    case 'Teléfono':
      contactInput = (
        <Form.Control
          type="text"
          placeholder={`Ingrese su ${contactMethod}`}
          value={contactInfo}
          className='mb-3'
          onChange={(event) => setContactInfo(event.target.value)}
        />
      );
      break;
    default:
      contactInput = null;
  }

  return (
    <Form.Group controlId="formBasicContact">
      <Form.Label>Medio de Contacto</Form.Label>
      <Form.Select className='mb-3' aria-label="Medios Contactos" onChange={handleContactMethodChange} value={contactMethod}>
        <option>Seleccione...</option>
        <option value="Correo Electrónico">Correo Electrónico</option>
        <option value="Whatsapp">Whatsapp</option>
        <option value="Teléfono">Teléfono</option>
      </Form.Select>
      {contactInput}
    </Form.Group>
  );
}

export default ContactMethod;


