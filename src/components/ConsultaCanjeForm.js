import React from 'react';
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
        id: 2,
        nombre: 'Producto 2',
        puntos: 200,
        imagen: 'https://via.placeholder.com/150'
    },
    {
        id: 2,
        nombre: 'Producto 2',
        puntos: 200,
        imagen: 'https://via.placeholder.com/150'
    },
    {
        id: 2,
        nombre: 'Producto 2',
        puntos: 200,
        imagen: 'https://via.placeholder.com/150'
    },
    {
        id: 2,
        nombre: 'Producto 2',
        puntos: 200,
        imagen: 'https://via.placeholder.com/150'
    },
    // Agrega más productos según sea necesario
];

function ConsultaCanjeForm() {
    const handleCanjear = (productoId) => {
        // Lógica para canjear el producto
        alert(`Producto ${productoId} canjeado.`);
    };

    return (
        <div className="consulta-canjes-form-container">
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