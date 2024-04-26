import React, { useState } from 'react';
import Form from 'react-bootstrap/Form';
import { ModalFooter } from 'react-bootstrap';
import ContactMethod from './ContactMethod';
function HumanForm({ contactMethod, setContactMethod, contactInfo, setContactInfo }) {
  const [selectedOptions, setSelectedOptions] = useState([]);
  const [birthdate, setBirthdate] = useState('');
  const [address, setAddress] = useState('');
  const [donationChecked, setDonationChecked] = useState(false);
  const [foodDonationChecked, setFoodDonationChecked] = useState(false);
  const [mealDistributionChecked, setMealDistributionChecked] = useState(false);
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
  const handleCheckboxChange = (event) => {
    const { name, checked } = event.target;
    switch (name) {
      case 'donation':
        setDonationChecked(checked);
        break;
      case 'foodDonation':
        setFoodDonationChecked(checked);
        break;
      case 'mealDistribution':
        setMealDistributionChecked(checked);
        break;
      default:
        break;
    }
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
      <Form.Label>Formas de Contribuir</Form.Label>
     <div className='d-flex'>
        <Form.Check
        type='checkbox'
        label='Donación de dinero'
        name='donation'
        checked={donationChecked}
        onChange={handleCheckboxChange}
      />
      <Form.Check
        type='checkbox'
        label='Donación de vianda'
        name='foodDonation'
        checked={foodDonationChecked}
        onChange={handleCheckboxChange}
      />
      <Form.Check
        type='checkbox'
        label='Distribución de "viandas"'
        name='mealDistribution'
        checked={mealDistributionChecked}
        onChange={handleCheckboxChange}
      />
      </div>
        <ModalFooter fluid className='d-flex justify-content-around flex-nowrap'>
          <Form.Group controlId="formBasicBirthdate">
            <Form.Label>Fecha de Nacimiento (opcional)</Form.Label>
            <Form.Control type="date" value={birthdate} onChange={handleBirthdateChange} />
          </Form.Group>
          <Form.Group controlId="formBasicAddress">
            <Form.Label>Dirección<br/> (opcional)</Form.Label>
            <Form.Control type="text" placeholder="Ingrese su dirección" value={address} onChange={handleAddressChange} />
          </Form.Group>
        </ModalFooter>
        <Form.Group className='mb-3'controlId="formBasicPassword">
            <Form.Label>Ingrese una Contraseña</Form.Label>
            <Form.Control type="password" placeholder=""/>
          </Form.Group>    
    </>
  );
}

export default HumanForm;