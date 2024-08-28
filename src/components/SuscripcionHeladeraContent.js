import React, { useState } from 'react';
import '../assets/styles/SuscripcionHeladeraContent.css';

const heladeras = [
    { id: 1, nombre: 'Heladera 1', descripcion: 'Descripción de la Heladera 1', imagen: 'https://via.placeholder.com/150' },
    { id: 2, nombre: 'Heladera 2', descripcion: 'Descripción de la Heladera 2', imagen: 'https://via.placeholder.com/150' },
    { id: 3, nombre: 'Heladera 3', descripcion: 'Descripción de la Heladera 3', imagen: 'https://via.placeholder.com/150' },
    { id: 4, nombre: 'Heladera 4', descripcion: 'Descripción de la Heladera 4', imagen: 'https://via.placeholder.com/150' },
    { id: 5, nombre: 'Heladera 5', descripcion: 'Descripción de la Heladera 5', imagen: 'https://via.placeholder.com/150' },
    { id: 6, nombre: 'Heladera 6', descripcion: 'Descripción de la Heladera 6', imagen: 'https://via.placeholder.com/150' },
    // Agrega más heladeras si es necesario
];

function SuscripcionHeladeraContent() {
    const [formVisible, setFormVisible] = useState(false);
    const [selectedHeladera, setSelectedHeladera] = useState(null);
    const [searchTerm, setSearchTerm] = useState('');
    const [notificationSettings, setNotificationSettings] = useState({
        case1: false,
        case2: false,
        case3: false,
    });
    const [viandasNumber, setViandasNumber] = useState({
        case1: '',
        case2: ''
    });

    const handleSuscribirse = (heladera) => {
        setSelectedHeladera(heladera);
        setFormVisible(true);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        // Aquí puedes manejar la lógica de envío del formulario
        alert('Formulario enviado');
        setFormVisible(false); // Ocultar el formulario después de enviarlo
    };

    const handleCheckboxChange = (event) => {
        const { name, checked } = event.target;
        setNotificationSettings(prevState => ({
            ...prevState,
            [name]: checked
        }));
    };

    const handleViandasChange = (event) => {
        const { name, value } = event.target;
        setViandasNumber(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const filteredHeladeras = heladeras.filter(heladera =>
        heladera.nombre.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <div className="suscripcion-heladera-content-container">
            <h2>Suscripción a Heladeras</h2>
            <div className="search-container">
                <input
                    type="text"
                    placeholder="Buscar por nombre..."
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                    className="search-input"
                />
                <button className="search-button">
                    Buscar
                </button>
            </div>
            <div className="heladeras-grid">
                {filteredHeladeras.map(heladera => (
                    <div key={heladera.id} className="heladera-card">
                        <img src={heladera.imagen} alt={heladera.nombre} />
                        <h3>{heladera.nombre}</h3>
                        <p>{heladera.descripcion}</p>
                        <button
                            className="suscribirse-button"
                            onClick={() => handleSuscribirse(heladera)}
                        >
                            Suscribirse
                        </button>
                    </div>
                ))}
            </div>
            {formVisible && selectedHeladera && (
                <div className="form-overlay">
                    <div className="form-container">
                        <h3>Formulario de Suscripción para {selectedHeladera.nombre}</h3>
                        <form onSubmit={handleSubmit}>
                            <div className="notification-settings">
                                <label>
                                    <input
                                        type="checkbox"
                                        name="case1"
                                        checked={notificationSettings.case1}
                                        onChange={handleCheckboxChange}
                                    />
                                    Quedan únicamente n viandas disponibles
                                    <input
                                        type="number"
                                        name="case1"
                                        value={viandasNumber.case1}
                                        onChange={handleViandasChange}
                                        min="1"
                                        placeholder="Número de viandas"
                                    />
                                </label>
                                <label>
                                    <input
                                        type="checkbox"
                                        name="case2"
                                        checked={notificationSettings.case2}
                                        onChange={handleCheckboxChange}
                                    />
                                    Faltan n viandas para llenar la heladera
                                    <input
                                        type="number"
                                        name="case2"
                                        value={viandasNumber.case2}
                                        onChange={handleViandasChange}
                                        min="1"
                                        placeholder="Número de viandas"
                                    />
                                </label>
                                <label>
                                    <input
                                        type="checkbox"
                                        name="case3"
                                        checked={notificationSettings.case3}
                                        onChange={handleCheckboxChange}
                                    />
                                    La heladera sufrió un desperfecto
                                </label>
                            </div>
                            <button type="submit">Enviar</button>
                            <button type="button" onClick={() => setFormVisible(false)}>Cancelar</button>
                        </form>
                    </div>
                </div>
            )}
        </div>
    );
}

export default SuscripcionHeladeraContent;