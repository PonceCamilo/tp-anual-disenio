import React, { useState, useEffect } from 'react';
import '../assets/styles/DistribucionViandasForm.css';

const DistribucionViandasForm = () => {
    const [heladeras, setHeladeras] = useState([]);
    const [heladeraOrigen, setHeladeraOrigen] = useState('');
    const [heladeraDestino, setHeladeraDestino] = useState('');
    const [cantidadDeViandas, setCantidadDeViandas] = useState('');
    const [motivo, setMotivo] = useState('');

    useEffect(() => {
        // Simulación de obtención de heladeras desde una API
        const fetchHeladeras = async () => {
            try {
                const response = await fetch('/api/heladeras');
                const data = await response.json();
                setHeladeras(data);
            } catch (error) {
                console.error('Error al obtener las heladeras:', error);
            }
        };

        fetchHeladeras();
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();

        const formData = {
            heladeraOrigen,
            heladeraDestino,
            cantidadDeViandas: parseInt(cantidadDeViandas),
            motivo,
        };

        try {
            const response = await fetch('/api/distribute-meal', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });
            if (response.ok) {
                console.log('Distribución de viandas enviada con éxito');
            } else {
                console.log('Error al enviar la distribución de viandas');
            }
        } catch (error) {
            console.error('Error:', error);
        }

        setHeladeraOrigen('');
        setHeladeraDestino('');
        setCantidadDeViandas('');
        setMotivo('');
    };

    return (
        <form className="distribucion-viandas-form" onSubmit={handleSubmit}>
            <div className="form-group">
                <label>Heladera de Origen:</label>
                <select
                    value={heladeraOrigen}
                    onChange={(e) => setHeladeraOrigen(e.target.value)}
                    required
                >
                    <option value="">Seleccionar</option>
                    {heladeras.map((heladera) => (
                        <option key={heladera.id} value={heladera.id}>
                            {heladera.nombre}
                        </option>
                    ))}
                </select>
            </div>

            <div className="form-group">
                <label>Heladera de Destino:</label>
                <select
                    value={heladeraDestino}
                    onChange={(e) => setHeladeraDestino(e.target.value)}
                    required
                >
                    <option value="">Seleccionar</option>
                    {heladeras.map((heladera) => (
                        <option key={heladera.id} value={heladera.id}>
                            {heladera.nombre}
                        </option>
                    ))}
                </select>
            </div>

            <div className="form-group">
                <label>Cantidad de Viandas a Mover:</label>
                <input
                    type="number"
                    value={cantidadDeViandas}
                    onChange={(e) => setCantidadDeViandas(e.target.value)}
                    required
                />
            </div>

            <div className="form-group">
                <label>Motivo:</label>
                <textarea
                    value={motivo}
                    onChange={(e) => setMotivo(e.target.value)}
                    required
                />
            </div>

            <button type="submit" className="submit-button">Distribuir Viandas</button>
        </form>
    );
};

export default DistribucionViandasForm;