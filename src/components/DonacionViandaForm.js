import React, { useState } from 'react';
import '../assets/styles/DonacionViandaForm.css';

const DonacionViandaForm = () => {
  const [comida, setComida] = useState('');
  const [fechaDeCaducidad, setFechaDeCaducidad] = useState('');
  const [calorias, setCalorias] = useState('');
  const [peso, setPeso] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = {
      comida,
      fechaDeCaducidad,
      calorias: parseFloat(calorias),
      peso: parseFloat(peso),
    };

    try {
      const response = await fetch('/api/donate-meal', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });
      if (response.ok) {
        console.log('Donación enviada con éxito');
      } else {
        console.log('Error al enviar la donación');
      }
    } catch (error) {
      console.error('Error:', error);
    }

    // Reiniciar formulario
    setComida('');
    setFechaDeCaducidad('');
    setCalorias('');
    setPeso('');
  };

  return (
    <form className="donacion-vianda-form" onSubmit={handleSubmit}>
      <h2>Donar Vianda</h2>

      <label>
        Comida:
        <input
          type="text"
          value={comida}
          onChange={(e) => setComida(e.target.value)}
          required
        />
      </label>

      <label>
        Fecha de Caducidad:
        <input
          type="date"
          value={fechaDeCaducidad}
          onChange={(e) => setFechaDeCaducidad(e.target.value)}
          required
        />
      </label>

      <label>
        Calorías:
        <input
          type="number"
          step="0.01"
          value={calorias}
          onChange={(e) => setCalorias(e.target.value)}
          required
        />
      </label>

      <label>
        Peso (en gramos):
        <input
          type="number"
          step="0.01"
          value={peso}
          onChange={(e) => setPeso(e.target.value)}
          required
        />
      </label>

      <button type="submit">Enviar Donación</button>
    </form>
  );
};

export default DonacionViandaForm;