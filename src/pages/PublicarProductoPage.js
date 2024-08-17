import React from 'react';
import PublicarProductoForm from '../components/PublicarProductoForm';
import '../assets/styles/PublicarProductoPage.css';

function PublicarProductoPage() {
    return (
        <div className="publicar-producto-page">
            <h1>Publicar Producto/Servicio</h1>
            <PublicarProductoForm />
        </div>
    );
}

export default PublicarProductoPage;