import React, { useState } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import HumanForm from './HumanForm';
import LegalForm from './LegalForm';

function RegistrationForm() {
  const [personType, setPersonType] = useState('');
  const [formData, setFormData] = useState({
    name: '',
    lastName: '',
    email: '',
    emailContact: '',
    telefono: '',
    whatsapp: '',
    password: '',
    birthdate: '',
    address: '',
    rol: '',
    donationChecked: false,
    foodDonationChecked: false,
    mealDistributionChecked: false,
    companyName: '',
    organizationType: '',
    category: '',
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
  
    const dataToSend = {
      email: formData.email || null,
      emailContact: formData.emailContact || null, 
      telefono: formData.telefono || null,
      whatsapp: formData.whatsapp || null,
      password: formData.password || null,
      rol: formData.rol || null,
      type: personType || null,
      name: formData.name || null,
      lastName: formData.lastName || null,
      birthdate: formData.birthdate || null,
      address: formData.address || null,
      donationChecked: formData.donationChecked,
      foodDonationChecked: formData.foodDonationChecked,
      mealDistributionChecked: formData.mealDistributionChecked,
      companyName: formData.companyName || null,
      organizationType: formData.organizationType || null,
      category: formData.category || null,
    };

    fetch('http://localhost:8080/usuarios/registrar', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(dataToSend),
    })
    .then((response) => response.text())
    .then((data) => {
      alert(data);
    })
    .catch((error) => {
      console.error('Error:', error);
    });
  };
  

  // Determine if the personType group should be disabled
  const isPersonTypeDisabled = !formData.rol;

  return (
    <Form onSubmit={handleSubmit}>
      <Form.Group className='mb-3' controlId="formBasicEmail">
        <Form.Label>Ingrese su Email</Form.Label>
        <Form.Control
          type="email"
          placeholder="Ingrese su correo electrónico"
          name="email"
          value={formData.email}
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

      <Form.Group className='mb-3' controlId="formBasicRol">
        <Form.Label>Usted es</Form.Label>
        <Form.Select
          aria-label="Rol"
          name="rol"
          value={formData.rol}
          onChange={handleInputChange}
        >
          <option value="">Seleccione...</option>
          <option value="Colaborador">Colaborador</option>
        </Form.Select>
      </Form.Group>

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
              disabled={isPersonTypeDisabled} // Disable if rol is not selected
            />
          </div>
        ))}
      </Form.Group>

      {personType === 'humana' && (
        <HumanForm 
          formData={formData} 
          handleInputChange={handleInputChange} 
        />
      )}

      {personType === 'juridica' && (
        <LegalForm 
          formData={formData} 
          handleInputChange={handleInputChange} 
          setFormData={setFormData} // Pass setFormData to LegalForm
        />
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
