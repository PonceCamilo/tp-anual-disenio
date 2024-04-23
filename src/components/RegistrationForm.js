import React from 'react';
import { useState } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import HumanForm from './HumanForm';
import LegalForm from './LegalForm';

function RegistrationForm() {
  const [personType, setPersonType] = useState('humana');

  const handlePersonTypeChange = (event) => {
    setPersonType(event.target.value);
  };

  return (
    <Form>
      <Form.Label>Tipo de Persona</Form.Label>
      <Form.Group className='d-flex justify-content-start' controlId="formBasicCheckbox">
        
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
            />
          </div>
        ))}
      </Form.Group>

      {personType === 'humana' && (
        <HumanForm />
      )}

      {personType === 'juridica' && (
        <LegalForm />
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