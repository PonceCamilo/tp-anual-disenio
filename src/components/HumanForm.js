import React, { useState } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import { ModalFooter } from 'react-bootstrap';
import ContactMethod from './ContactMethod'; // Asumiendo que 'Contact' es el nombre correcto del archivo

function HumanForm({ contactMethod, setContactMethod, contactInfo, setContactInfo }) {
  const [selectedOptions, setSelectedOptions] = useState([]);
  const [birthdate, setBirthdate] = useState('');
  const [address, setAddress] = useState('');
  const [helpMethod, setHelpMethod] = useState('Seleccione...');
  const handleBirthdateChange = (event) => {
    setBirthdate(event.target.value);
  };
  const handleRemoveOption = (option) => {
    const updatedOptions = selectedOptions.filter((item) => item !== option);
    setSelectedOptions(updatedOptions);
  };

  const handleAddressChange = (event) => {
    setAddress(event.target.value);
  };
  const handleHelpMethodChange = (event) => {
    const option = event.target.value;
    setHelpMethod(option);
    setSelectedOptions([...selectedOptions, option]);
  };

  return (
    <>
      <Form.Group controlId="formBasicName">
        <Form.Label>Nombre</Form.Label>
        <Form.Control type="text" placeholder="Ingrese su nombre" />
      </Form.Group>
      <Form.Group className='mt-2' controlId="formBasicLastName">
        <Form.Label>Apellido</Form.Label>
        <Form.Control type="text" placeholder="Ingrese su apellido" />
      </Form.Group>
      <Form.Group className='mt-2 mb-3' controlId="formBasicContact">
        <ContactMethod 
          contactMethod={contactMethod}
          setContactMethod={setContactMethod}
          contactInfo={contactInfo}
          setContactInfo={setContactInfo}
        />
      </Form.Group>
      <Form.Group controlId="formBasicHelp">
        <Form.Label>Formas de Contribuir</Form.Label>
        <Form.Select aria-label="Default select example" onChange={handleHelpMethodChange} value={helpMethod}>
          <option>Seleccione...</option>
          <option value="Donación de dinero">Donación de dinero</option>
          <option value="Donación de vianda">Donación de vianda</option>
          <option value="Distribución de viandas">Distribución de "viandas"</option>
        </Form.Select>
      </Form.Group>
      <div className='mt-3'>
        {selectedOptions.map((option, index) => (
          <div key={index} className="d-flex justify-content-between align-items-center mb-2">
            <span className="mr-2">{option}</span>
            <Button variant="outline-black" size="sm" onClick={() => handleRemoveOption(option)}>Eliminar</Button>
          </div>
        ))}
      </div>
      <div className="d-flex justify-content-around align-items-center">
        <ModalFooter>
          <Form.Group controlId="formBasicBirthdate">
            <Form.Label>Fecha de Nacimiento (opcional)</Form.Label>
            <Form.Control type="date" value={birthdate} onChange={handleBirthdateChange} />
          </Form.Group>
          <Form.Group controlId="formBasicAddress">
            <Form.Label>Dirección (opcional)</Form.Label>
            <Form.Control type="text" placeholder="Ingrese su dirección" value={address} onChange={handleAddressChange} />
          </Form.Group>
        </ModalFooter>
      </div>
    </>
  );
}

export default HumanForm;