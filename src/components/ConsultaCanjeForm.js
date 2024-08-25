import React from 'react';
import '../assets/styles/ConsultaCanjeForm.css';
import { notice } from '@pnotify/core';
import '@pnotify/core/dist/PNotify.css';
import '@pnotify/core/dist/BrightTheme.css';
import '../assets/styles/ConsultaCanjeForm.css';

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
        puntos: 200,
        imagen: 'https://via.placeholder.com/150'
    },
    {
        id: 4,
        nombre: 'Producto 4',
        puntos: 200,
        imagen: 'https://via.placeholder.com/150'
    },
    {
        id: 5,
        nombre: 'Producto 5',
        puntos: 200,
        imagen: 'https://via.placeholder.com/150'
    },
    {
        id: 6,
        nombre: 'Producto 6',
        puntos: 200,
        imagen: 'https://via.placeholder.com/150'
    },
    {
        id: 7,
        nombre: 'Producto 7',
        puntos: 200,
        imagen: 'https://via.placeholder.com/150'
    },
];

function ConsultaCanjeForm() {
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

    return (
        <div className="consulta-canjes-form-container">
            <div className="puntos-actuales">
                <p>Puntos actuales: {puntosActuales}</p>
            </div>
            <h2>Consulta y Canje de Productos</h2>
            <div className="productos-grid">
                {productos.map(producto => (
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
