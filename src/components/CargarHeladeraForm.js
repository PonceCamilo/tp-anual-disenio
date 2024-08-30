import React, { useState } from 'react';
import '../assets/styles/CargarHeladeraForm.css';

const CargarHeladeraForm = () => {
    const [capacidad, setCapacidad] = useState('');
    const [temperaturaMinima, setTemperaturaMinima] = useState('');
    const [temperaturaMaxima, setTemperaturaMaxima] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        const formData = {
            capacidad: parseInt(capacidad, 10),
            temperaturaMinima: parseFloat(temperaturaMinima),
            temperaturaMaxima: parseFloat(temperaturaMaxima),
        };

        try {
            const response = await fetch('/api/add-fridge', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });
            if (response.ok) {
                console.log('Heladera añadida con éxito');
            } else {
                console.log('Error al añadir la heladera');
            }
        } catch (error) {
            console.error('Error:', error);
        }

        setCapacidad('');
        setTemperaturaMinima('');
        setTemperaturaMaxima('');
    };

    return (
        <form className="cargar-heladera-form" onSubmit={handleSubmit}>
            <h2>Cargar Heladera</h2>

            <label>
                Capacidad (cantidad viandas):
                <input
                    type="number"
                    value={capacidad}
                    onChange={(e) => setCapacidad(e.target.value)}
                    required
                />
            </label>

            <label>
                Temperatura Mínima (°C):
                <input
                    type="number"
                    step="0.1"
                    value={temperaturaMinima}
                    onChange={(e) => setTemperaturaMinima(e.target.value)}
                    required
                />
            </label>

            <label>
                Temperatura Máxima (°C):
                <input
                    type="number"
                    step="0.1"
                    value={temperaturaMaxima}
                    onChange={(e) => setTemperaturaMaxima(e.target.value)}
                    required
                />
            </label>

            <button type="submit">Añadir Heladera</button>
        </form>
    );
};

export default CargarHeladeraForm;