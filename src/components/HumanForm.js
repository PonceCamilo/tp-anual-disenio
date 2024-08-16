import React, { useState } from 'react';
import Form from 'react-bootstrap/Form';
import ContactMethod from './ContactMethod';
import ModalFooter from 'react-bootstrap/ModalFooter';

function HumanForm({ formData, handleInputChange }) {
  const [contactMethod, setContactMethod] = useState('');

  const handleContactMethodChange = (event) => {
    setContactMethod(event.target.value);
  };

  const handleContactInfoChange = (newValue) => {
    handleInputChange({
      target: { name: contactMethod, value: newValue }
    });
  };

  return (
    <>
      <Form.Group controlId="formBasicName">
        <Form.Label>Nombre</Form.Label>
        <Form.Control
          type="text"
          placeholder="Ingrese su nombre"
          name="name"
          value={formData.name}
          onChange={handleInputChange}
        />
      </Form.Group>
      <Form.Group className='mt-2' controlId="formBasicLastName">
        <Form.Label>Apellido</Form.Label>
        <Form.Control
          type="text"
          placeholder="Ingrese su apellido"
          name="lastName"
          value={formData.lastName}
          onChange={handleInputChange}
        />
      </Form.Group>
      
      <Form.Group className='mt-2 mb-3' controlId="formBasicContactMethod">
        <Form.Label>Seleccione un Método de Contacto</Form.Label>
        <Form.Select
          aria-label="Método de Contacto"
          value={contactMethod}
          onChange={handleContactMethodChange}
        >
          <option value="">Seleccione...</option>
          <option value="emailContact">Email</option>
          <option value="telefono">Teléfono</option>
          <option value="whatsapp">WhatsApp</option>
        </Form.Select>
      </Form.Group>

      {contactMethod && (
        <Form.Group className='mt-3' controlId={`formBasic${contactMethod}`}>
          <ContactMethod
            type={contactMethod}
            formData={formData}
            setFormData={handleContactInfoChange}
          />
        </Form.Group>
      )}
      
      <Form.Label>Formas de Contribuir</Form.Label>
      <div className='d-flex'>
        <Form.Check
          type='checkbox'
          label='Donación de dinero'
          name='donationChecked'
          checked={formData.donationChecked}
          onChange={handleInputChange}
        />
        <Form.Check
          type='checkbox'
          label='Donación de vianda'
          name='foodDonationChecked'
          checked={formData.foodDonationChecked}
          onChange={handleInputChange}
        />
        <Form.Check
          type='checkbox'
          label='Distribución de "viandas"'
          name='mealDistributionChecked'
          checked={formData.mealDistributionChecked}
          onChange={handleInputChange}
        />
      </div>
      <ModalFooter fluid className='d-flex justify-content-around flex-nowrap'>
      <Form.Group className='mb-3' controlId="formBasicBirthdate">
        <Form.Label>Fecha de Nacimiento (opcional)</Form.Label>
        <Form.Control
          type="date"
          name="birthdate"
          value={formData.birthdate}
          onChange={handleInputChange}
        />
      </Form.Group>
      <Form.Group className='mb-3' controlId="formBasicAddress">
        <Form.Label>Dirección<br /> (opcional)</Form.Label>
        <Form.Control
          type="text"
          placeholder="Ingrese su dirección"
          name="address"
          value={formData.address}
          onChange={handleInputChange}
        />
      </Form.Group>
      </ModalFooter>
    </>
  );
}

export default HumanForm;
