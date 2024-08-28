import React, { useState, useEffect } from 'react';
import '../assets/styles/SuscripcionHeladeraPage.css';

const SuscripcionHeladeraPage = () => {
    const [heladeras, setHeladeras] = useState([]);
    const [filteredHeladeras, setFilteredHeladeras] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [selectedHeladera, setSelectedHeladera] = useState(null);

    useEffect(() => {
        // Fetch the list of heladeras when the component mounts
        const fetchHeladeras = async () => {
            try {
                const response = await fetch('/api/heladeras');
                const data = await response.json();
                setHeladeras(data);
                setFilteredHeladeras(data); // Initialize filteredHeladeras
            } catch (error) {
                console.error('Error al obtener las heladeras:', error);
            }
        };

        fetchHeladeras();
    }, []);

    const handleSearch = (e) => {
        setSearchTerm(e.target.value);
        const filtered = heladeras.filter(heladera =>
            heladera.nombre.toLowerCase().includes(e.target.value.toLowerCase())
        );
        setFilteredHeladeras(filtered);
    };

    const handleSubscribeClick = (heladera) => {
        setSelectedHeladera(heladera);
    };

    const handleFormSubmit = async (e) => {
        e.preventDefault();
        if (!selectedHeladera) {
            alert('Por favor, seleccione una heladera.');
            return;
        }

        const formData = {
            heladeraId: selectedHeladera.id,
            // Add other form fields here if necessary
        };

        try {
            const response = await fetch('/api/subscribe', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });
            if (response.ok) {
                console.log('Suscripción realizada con éxito');
                // Reset form and state
                setSelectedHeladera(null);
            } else {
                console.log('Error al realizar la suscripción');
            }
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <div className="suscripcion-heladera-page">
            <h2>Suscripción a Heladera</h2>

            <input
                type="text"
                placeholder="Buscar heladera por nombre..."
                value={searchTerm}
                onChange={handleSearch}
                className="search-input"
            />

            <div className="heladeras-grid">
                {filteredHeladeras.map((heladera) => (
                    <div key={heladera.id} className="heladera-card">
                        <h3>{heladera.nombre}</h3>
                        <button onClick={() => handleSubscribeClick(heladera)}>
                            Suscribirse
                        </button>
                    </div>
                ))}
            </div>

            {selectedHeladera && (
                <form className="suscripcion-form" onSubmit={handleFormSubmit}>
                    <h3>Formulario de Suscripción</h3>

                    <p><strong>Heladera Seleccionada:</strong> {selectedHeladera.nombre}</p>

                    <label>
                        Información Adicional:
                        <input
                            type="text"
                            placeholder="Ingrese información adicional"
                        // Add value and onChange handlers here if needed
                        />
                    </label>

                    <button type="submit">Enviar</button>
                </form>
            )}
        </div>
    );
};

export default SuscripcionHeladeraPage;