import React, { useState } from 'react';
import Form from 'react-bootstrap/Form';
import ContactMethod from './ContactMethod';

function LegalForm({ formData, handleInputChange, setFormData }) {
  const [contactMethod, setContactMethod] = useState('');

  const handleContactMethodChange = (event) => {
    setContactMethod(event.target.value);
  };

  const handleContactInfoChange = (newData) => {
    setFormData(prevData => ({
      ...prevData,
      [contactMethod]: newData
    }));
  };
  
  return (
    <>
      <Form.Group controlId="formBasicCompanyName">
        <Form.Label>Razón Social</Form.Label>
        <Form.Control
          type="text"
          placeholder="Razón Social"
          name="companyName"
          value={formData.companyName || ''} // Handle undefined value
          onChange={handleInputChange}
        />
      </Form.Group>
      <Form.Group className='mt-2 mb-3' controlId="formBasicType">
        <Form.Label>Tipo</Form.Label>
        <Form.Select
          aria-label="Tipo Organización"
          name="organizationType"
          value={formData.organizationType || ''} // Handle undefined value
          onChange={handleInputChange}
        >
          <option value="">Seleccione...</option>
          <option value="Gubernamental">Gubernamental</option>
          <option value="ONG">ONG</option>
          <option value="Empresa">Empresa</option>
          <option value="Institución">Institución</option>
        </Form.Select>
      </Form.Group>
      <Form.Group className='mt-2 mb-2' controlId="formBasicCategory">
        <Form.Label>Rubro</Form.Label>
        <Form.Control
          type="text"
          placeholder="Rubro"
          name="category"
          value={formData.category || ''} // Handle undefined value
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
        <Form.Group className='mt-3' controlId="formBasicContactInfo">
          <ContactMethod
            type={contactMethod}
            formData={formData}
            setFormData={handleContactInfoChange} 
          />
        </Form.Group>
      )}
      <Form.Group className='mb-3' controlId="formBasicAddress">
        <Form.Label>Dirección (opcional)</Form.Label>
        <Form.Control
          type="text"
          placeholder="Ingrese su dirección"
          name="address"
          value={formData.address || ''} // Handle undefined value
          onChange={handleInputChange}
        />
      </Form.Group>
    </>
  );
}

export default LegalForm;
