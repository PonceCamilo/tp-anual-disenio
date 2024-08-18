import React, { useState } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

function VulnerableForm() {
  const [formData, setFormData] = useState({
    name: '',
    LastName: '',
    birthDate: '',
    registrationDate: '', 
    isHomeless: false,
    address: '',
    documentType: '',
    documentNumber: '',
    hasMinors: false,
    minorsCount: ''
  });

  const handleInputChange = (event) => {
    const { name, value, type, checked } = event.target;
    setFormData({
      ...formData,
      [name]: type === 'checkbox' ? checked : value
    });

    // Forzar actualización del DOM para evitar bucles del ResizeObserver
    setTimeout(() => window.dispatchEvent(new Event('resize')), 0);
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    // Captura la fecha y hora actual como fecha de registro
    const currentTimestamp = new Date().toISOString();

    // Añadir la fecha de registro al formData antes de enviar
    const dataToSubmit = {
      ...formData,
      registrationDate: currentTimestamp
    };

    console.log("Datos a enviar:", dataToSubmit);
    //  enviar dataToSubmit a un backend o realizar otra acción
  };

  return (
    <Form onSubmit={handleSubmit}>
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

      <Form.Group controlId="formBasicLastName" className="mt-3">
        <Form.Label>Apellido</Form.Label>
        <Form.Control
          type="text"
          placeholder="Ingrese su Apellido"
          name="LastName"
          value={formData.LastName}
          onChange={handleInputChange}
        />
      </Form.Group>

      <Form.Group controlId="formBasicBirthDate" className="mt-3">
        <Form.Label>Fecha de Nacimiento</Form.Label>
        <Form.Control
          type="date"
          name="birthDate"
          value={formData.birthDate}
          onChange={handleInputChange}
        />
      </Form.Group>

      <Form.Group controlId="formBasicIsHomeless" className="mt-3">
        <Form.Check
          type="checkbox"
          label="¿En situación de calle?"
          name="isHomeless"
          checked={formData.isHomeless}
          onChange={handleInputChange}
        />
      </Form.Group>

      {!formData.isHomeless && (
        <Form.Group controlId="formBasicAddress" className="mt-3">
          <Form.Label>Domicilio</Form.Label>
          <Form.Control
            type="text"
            placeholder="Ingrese su domicilio"
            name="address"
            value={formData.address}
            onChange={handleInputChange}
          />
        </Form.Group>
      )}

      <Form.Group controlId="formBasicDocumentType" className="mt-3">
        <Form.Label>Tipo de Documento</Form.Label>
        <Form.Control
          as="select"
          name="documentType"
          value={formData.documentType}
          onChange={handleInputChange}
        >
          <option value="">Seleccione...</option>
          <option value="DNI">DNI</option>
          <option value="Pasaporte">Pasaporte</option>
          <option value="Otro">Otro</option>
        </Form.Control>
      </Form.Group>

      <Form.Group controlId="formBasicDocumentNumber" className="mt-3">
        <Form.Label>Número de Documento</Form.Label>
        <Form.Control
          type="text"
          placeholder="Ingrese su número de documento"
          name="documentNumber"
          value={formData.documentNumber}
          onChange={handleInputChange}
        />
      </Form.Group>

      <Form.Group controlId="formBasicHasMinors" className="mt-3">
        <Form.Check
          type="checkbox"
          label="¿Posee menores a cargo?"
          name="hasMinors"
          checked={formData.hasMinors}
          onChange={handleInputChange}
        />
      </Form.Group>

      {formData.hasMinors && (
        <Form.Group controlId="formBasicMinorsCount" className="mt-3">
          <Form.Label>Cantidad de menores a cargo</Form.Label>
          <Form.Control
            type="number"
            placeholder="Ingrese la cantidad de menores"
            name="minorsCount"
            value={formData.minorsCount}
            onChange={handleInputChange}
          />
        </Form.Group>
      )}

      <Button variant="primary" type="submit" className="mt-4">
        Registrar
      </Button>
    </Form>
  );
}

export default VulnerableForm;
