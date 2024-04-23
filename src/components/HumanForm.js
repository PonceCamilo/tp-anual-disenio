import React, { useState } from 'react';
import Form from 'react-bootstrap/Form';
import Dropdown from 'react-bootstrap/Dropdown';
import { ModalFooter } from 'react-bootstrap';

function HumanForm() {
  const [contactMethod, setContactMethod] = useState('Seleccione...');
  const [contactInfo, setContactInfo] = useState('');
  const [birthdate, setBirthdate] = useState('');
  const [address, setAddress] = useState('');

  const handleContactMethodChange = (event) => {
    setContactMethod(event.target.value);
    setContactInfo('');
  };

  const handleContactInfoChange = (event) => {
    setContactInfo(event.target.value);
  };

  const handleBirthdateChange = (event) => {
    setBirthdate(event.target.value);
  };

  const handleAddressChange = (event) => {
    setAddress(event.target.value);
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
          onChange={handleContactInfoChange}
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
          onChange={handleContactInfoChange}
        />
      );
      break;
    default:
      contactInput = null;
  }

  return (
    <>
      <Form.Group controlId="formBasicName">
        <Form.Label>Nombre</Form.Label>
        <Form.Control type="text" placeholder="Ingrese su nombre" />
      </Form.Group>
      <Form.Group className='mt-2' controlId="formBasicLastName">
        <Form.Label>Apellido</Form.Label>
        <Form.Control type="text" placeholder="Ingrese su apellido" />
      </Form.Group>
      <Form.Group className='mt-2 mb-3' controlId="formBasicContact">
        <Form.Label>Medio de Contacto</Form.Label>
        <Form.Select aria-label="Default select example" onChange={handleContactMethodChange} value={contactMethod}>
          <option>Seleccione...</option>
          <option value="Correo Electrónico">Correo Electrónico</option>
          <option value="Whatsapp">Whatsapp</option>
          <option value="Teléfono">Teléfono</option>
        </Form.Select>
      </Form.Group>
      {contactInput}
      <div className="d-flex justify-content-around align-items-center">
      <ModalFooter >
      <Form.Group controlId="formBasicBirthdate">
        <Form.Label>Fecha de Nacimiento (opcional)</Form.Label>
        <Form.Control type="date" value={birthdate} onChange={handleBirthdateChange} />
      </Form.Group>
      <Form.Group controlId="formBasicAddress">
        <Form.Label>Dirección (opcional)</Form.Label>
        <Form.Control type="text" placeholder="Ingrese su dirección" value={address} onChange={handleAddressChange} />
      </Form.Group>
      </ModalFooter>
      </div>
    </>
  );
}

export default HumanForm;