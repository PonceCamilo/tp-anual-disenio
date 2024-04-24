import React from 'react';
import Form from 'react-bootstrap/Form';
import ContactMethod from './ContactMethod';

function LegalForm({ contactMethod, setContactMethod, contactInfo, setContactInfo }) {
  
  return (
    <>
      <Form.Group controlId="formBasicCompanyName">
        <Form.Label>Razón Social</Form.Label>
        <Form.Control type="text" placeholder="Razón Social" />
      </Form.Group>
      <Form.Group className='mt-2 mb-3' controlId="formBasicType">
        <Form.Label>Tipo</Form.Label>
        <Form.Select aria-label="Tipo Organización">
          <option>Seleccione...</option>
          <option value="Gubernamental">Gubernamental</option>
          <option value="ONG">ONG</option>
          <option value="Empresa">Empresa</option>
          <option value="Institución">Institución</option>
        </Form.Select>
      </Form.Group>
      <Form.Group className='mt-2 mb-2' controlId="formBasicCategory">
        <Form.Label>Rubro</Form.Label>
        <Form.Control type="text" placeholder="Rubro" />
      </Form.Group>
      <ContactMethod
        contactMethod={contactMethod}
        setContactMethod={setContactMethod}
        contactInfo={contactInfo}
        setContactInfo={setContactInfo}
      />
      <Form.Group className='mt-2 mb-2' controlId="formBasicLegalId">
        <Form.Label>RUC / CUIT</Form.Label>
        <Form.Control type="text" placeholder="Enter RUC / CUIT" />
      </Form.Group>
      <Form.Group className='mb-3' controlId="formBasicAddress">
        <Form.Label>Dirección (opcional)</Form.Label>
        <Form.Control type="text" placeholder="Ingrese su dirección"/>
      </Form.Group>
    </>
  );
}

export default LegalForm;