import React from 'react';
import Form from 'react-bootstrap/Form';

function LegalForm() {
  return (
    <>
      <Form.Group controlId="formBasicCompanyName">
        <Form.Label>Nombre de la Empresa</Form.Label>
        <Form.Control type="text" placeholder="Enter company name" />
      </Form.Group>

      <Form.Group controlId="formBasicLegalId">
        <Form.Label>RUC / CUIT</Form.Label>
        <Form.Control type="text" placeholder="Enter RUC / CUIT" />
      </Form.Group>
    </>
  );
 
}

export default LegalForm;