import React from 'react';
import Form from 'react-bootstrap/Form';
import { ModalFooter } from 'react-bootstrap';
import ContactMethod from './ContactMethod';

function HumanForm({ formData, handleInputChange, contactMethod, setContactMethod }) {
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
      <Form.Group className='mt-2 mb-3' controlId="formBasicContact">
        <ContactMethod
          contactMethod={contactMethod}
          setContactMethod={setContactMethod}
          contactInfo={formData.contactInfo}
          setContactInfo={(value) => handleInputChange({ target: { name: 'contactInfo', value } })}
        />
      </Form.Group>
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
        <Form.Group controlId="formBasicBirthdate">
          <Form.Label>Fecha de Nacimiento (opcional)</Form.Label>
          <Form.Control
            type="date"
            name="birthdate"
            value={formData.birthdate}
            onChange={handleInputChange}
          />
        </Form.Group>
        <Form.Group controlId="formBasicAddress">
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