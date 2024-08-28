import React, { useState, useEffect } from 'react';
import '../assets/styles/DistribucionViandasForm.css';

const DistribucionViandasForm = () => {
    const [heladeras, setHeladeras] = useState([]);
    const [heladeraOrigen, setHeladeraOrigen] = useState('');
    const [heladeraDestino, setHeladeraDestino] = useState('');
    const [cantidadViandas, setCantidadViandas] = useState('');
    const [motivo, setMotivo] = useState('');

    useEffect(() => {
        // Fetch the list of heladeras when the component mounts
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
            cantidadViandas: parseInt(cantidadViandas, 10),
            motivo,
        };

        try {
            const response = await fetch('/api/move-viandas', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });
            if (response.ok) {
                console.log('Viandas movidas con éxito');
            } else {
                console.log('Error al mover las viandas');
            }
        } catch (error) {
            console.error('Error:', error);
        }

        // Reset form fields
        setHeladeraOrigen('');
        setHeladeraDestino('');
        setCantidadViandas('');
        setMotivo('');
    };

    return (
        <form className="distribucion-viandas-form" onSubmit={handleSubmit}>
            <h2>Distribuir Viandas</h2>

            <label>
                Seleccione Heladera de Origen:
                <select
                    value={heladeraOrigen}
                    onChange={(e) => setHeladeraOrigen(e.target.value)}
                    required
                >
                    <option value="">Seleccione una heladera</option>
                    {heladeras.map((heladera) => (
                        <option key={heladera.id} value={heladera.id}>
                            {heladera.nombre}
                        </option>
                    ))}
                </select>
            </label>

            <label>
                Seleccione Heladera de Destino:
                <select
                    value={heladeraDestino}
                    onChange={(e) => setHeladeraDestino(e.target.value)}
                    required
                >
                    <option value="">Seleccione una heladera</option>
                    {heladeras.map((heladera) => (
                        <option key={heladera.id} value={heladera.id}>
                            {heladera.nombre}
                        </option>
                    ))}
                </select>
            </label>

            <label>
                Cantidad de Viandas a Mover:
                <input
                    type="number"
                    value={cantidadViandas}
                    onChange={(e) => setCantidadViandas(e.target.value)}
                    required
                />
            </label>

            <label>
                Motivo:
                <input
                    type="text"
                    value={motivo}
                    onChange={(e) => setMotivo(e.target.value)}
                    required
                />
            </label>

            <button type="submit">Mover Viandas</button>
        </form>
    );
};

export default DistribucionViandasForm;