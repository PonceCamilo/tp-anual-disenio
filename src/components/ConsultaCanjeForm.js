import React, { useState } from 'react';
import '../assets/styles/ConsultaCanjeForm.css';
import { notice } from '@pnotify/core';
import '@pnotify/core/dist/PNotify.css';
import '@pnotify/core/dist/BrightTheme.css';

const productos = [
    {
        id: 1,
        nombre: 'Producto 1',
        puntos: 100,
        imagen: 'https://via.placeholder.com/150'
    },
    {
        id: 2,
        nombre: 'Producto 2',
        puntos: 200,
        imagen: 'https://via.placeholder.com/150'
    },
    {
        id: 3,
        nombre: 'Producto 3',
        puntos: 300,
        imagen: 'https://via.placeholder.com/150'
    },
    {
        id: 4,
        nombre: 'Producto 4',
        puntos: 400,
        imagen: 'https://via.placeholder.com/150'
    },
    {
        id: 5,
        nombre: 'Producto 5',
        puntos: 500,
        imagen: 'https://via.placeholder.com/150'
    },
];

function ConsultaCanjeForm() {
    const [searchTerm, setSearchTerm] = useState('');
    const puntosActuales = 2000;

    const handleCanjear = (productoId) => {
        const producto = productos.find(p => p.id === productoId);

        if (producto) {
            const notification = notice({
                title: 'Producto Canjeado',
                text: `Has canjeado: ${producto.nombre}, Click para ver`,
                textTrusted: true,
                icon: false
            });

            notification.refs.elem.style.cursor = 'pointer';
            notification.on('click', e => {
                if ([...notification.refs.elem.querySelectorAll('.pnotify-closer *, .pnotify-sticker *')].indexOf(e.target) !== -1) {
                    return;
                }
                notification.update({
                    type: 'success',
                    text: `Canjeaste: ${producto.nombre}! <br></br> <div style="text-align: center;"><img src="${producto.imagen}" alt="${producto.nombre}" /></div>`,
                    textTrusted: true,
                    maxTextHeight: null
                });
            });
        }
    };

    const filteredProducts = productos.filter(producto =>
        producto.nombre.toLowerCase().includes(searchTerm.toLowerCase())
    );

    const handleSearch = () => {
        // Optionally, add any additional search handling logic here
    };

    return (
        <div className="consulta-canjes-form-container">
            <div className="puntos-actuales">
                <h4>Puntos actuales:</h4>
                <h1>{puntosActuales}</h1>
            </div>
            <div className="search-container">
                <input
                    type="text"
                    placeholder="Buscar producto..."
                    className="search-input"
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                />
                <button className="search-button" onClick={handleSearch}>
                    Buscar
                </button>
            </div>
            <div className="productos-grid">
                {filteredProducts.map(producto => (
                    <div key={producto.id} className="producto-card">
                        <img src={producto.imagen} alt={producto.nombre} />
                        <h3>{producto.nombre}</h3>
                        <p>Puntos necesarios: {producto.puntos}</p>
                        <button
                            className="canjear-button"
                            onClick={() => handleCanjear(producto.id)}
                        >
                            Canjear
                        </button>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default ConsultaCanjeForm;