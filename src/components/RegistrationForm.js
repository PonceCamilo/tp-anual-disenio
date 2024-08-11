import React, { useState } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import HumanForm from './HumanForm';
import LegalForm from './LegalForm';
import { FormGroup } from 'react-bootstrap';
import { type } from '@testing-library/user-event/dist/type';

function RegistrationForm() {
  const [personType, setPersonType] = useState('');
  const [formData, setFormData] = useState({
    name: '',
    lastName: '',
    contactInfo: '',
    password: '',
    birthdate: '',
    address: '',
    rol: '',
    donationChecked: false,
    foodDonationChecked: false,
    mealDistributionChecked: false,
  });

  const handlePersonTypeChange = (event) => {
    setPersonType(event.target.value);
  };

  const handleInputChange = (event) => {
    const { name, value, type, checked } = event.target;
    setFormData({
      ...formData,
      [name]: type === 'checkbox' ? checked : value,
    });

    if (name === 'rol') {
      setPersonType('');
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    fetch('http://localhost:8080/usuarios/registrar', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        email: formData.contactInfo, 
        whatsapp: formData.contactInfo, 
        telefono: formData.contactInfo, 
        password: formData.password,
        rol: formData.rol,
        type: personType,
        name: formData.name,
        lastName: formData.lastName,
        birthdate: formData.birthdate,
        address: formData.address,
        donationChecked: formData.donationChecked,
        foodDonationChecked: formData.foodDonationChecked,
        mealDistributionChecked: formData.mealDistributionChecked,
      }),
    })
      .then((response) => response.text())
      .then((data) => {
        alert(data);
      })
      .catch((error) => {
        console.error('Error:', error);
      });
  };

  return (
    <Form onSubmit={handleSubmit}>
      <Form.Group className='mb-3' controlId="formBasicContactInfo">
        <Form.Label>Ingrese su Email</Form.Label>
        <Form.Control
          type="email"
          placeholder="Ingrese su correo electrónico"
          name="contactInfo"
          value={formData.contactInfo}
          onChange={handleInputChange}
        />
      </Form.Group>
      <Form.Group className='mb-3' controlId="formBasicPassword">
        <Form.Label>Ingrese una Contraseña</Form.Label>
        <Form.Control
          type="password"
          name="password"
          value={formData.password}
          onChange={handleInputChange}
          placeholder="********"
        />
      </Form.Group>

      <FormGroup className='mb-3' controlId="formBasicRol">
        <Form.Label>Usted es</Form.Label>
        <Form.Select
          aria-label="Rol"
          name="rol"
          value={formData.rol}
          onChange={handleInputChange}
        >
          <option value="">Seleccione...</option>
          <option value="Colaborador">Colaborador</option>
          <option value="Persona Vulnerable">Persona Vulnerable</option>
        </Form.Select>
      </FormGroup>
      <Form.Label>Tipo de Persona</Form.Label>
      <Form.Group className='d-flex justify-content-start' controlId="formBasicPersonType">
        {['humana', 'juridica'].map((type) => (
          <div key={`inline-${type}`} className="mb-3">
            <Form.Check
              inline
              label={`Persona ${type === 'humana' ? 'Humana' : 'Jurídica'}`}
              name="personType"
              type="radio"
              id={`inline-${type}`}
              value={type}
              checked={personType === type}
              onChange={handlePersonTypeChange}
              disabled={
                !formData.rol || (formData.rol === 'Persona Vulnerable' && type === 'juridica')
              }
            />
          </div>
        ))}
      </Form.Group>

      {personType === 'humana' && (
        <HumanForm formData={formData} handleInputChange={handleInputChange} />
      )}

      {personType === 'juridica' && (
        <LegalForm formData={formData} handleInputChange={handleInputChange} />
      )}

      <div className="d-grid gap-2">
        <Button className='mt-1' variant="outline-primary" type='submit'>
          Registrarme
        </Button>
      </div>
    </Form>
  );
}

export default RegistrationForm;
