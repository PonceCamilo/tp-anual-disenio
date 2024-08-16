import React from 'react';
import Form from 'react-bootstrap/Form';

function ContactMethod({ type, formData, setFormData }) {
  if (!type || !setFormData) {
    console.error("El tipo de contacto o la función setFormData no se han proporcionado correctamente.");
    return null;
  }

  const handleContactInfoChange = (event) => {
    setFormData(event.target.value);
  };

  const getPlaceholder = (type) => {
    switch (type) {
      case 'emailContact':
        return 'Ingrese su correo electrónico';
      case 'telefono':
        return 'Ingrese su teléfono';
      case 'whatsapp':
        return 'Ingrese su WhatsApp';
      default:
        return '';
    }
  };
  const getLabel = (type) => {
    switch (type) {
      case 'emailContact':
        return 'Correo Electrónico';
      case 'telefono':
        return 'Teléfono';
      case 'whatsapp':
        return 'WhatsApp';
      default:
        return '';
    }
  };

  return (
    <Form.Group controlId={`formBasic${type}`}>
      <Form.Label>{`Ingrese su ${getLabel(type)}`}</Form.Label>
      <Form.Control
        type={type === 'emailContact' ? 'email' : 'text'}
        placeholder={getPlaceholder(type)}
        value={formData[type] || ''}
        onChange={handleContactInfoChange}
        className='mb-3'
      />
    </Form.Group>
  );
}

export default ContactMethod;
