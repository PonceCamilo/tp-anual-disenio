import React from 'react';
import PublicarProductoForm from '../components/PublicarProductoForm';
import '../assets/styles/PublicarProductoPage.css';
import '../assets/styles/App.css';
function PublicarProductoPage() {
    return (
        <div className="publicar-producto-page d-flex">
            <div className="left-side">
                <h1>Publicar Producto/Servicio</h1>
                <p>Ofrece tu producto o servicio y ayuda a la comunidad. Completa el formulario para que otros puedan verlo.</p>
            </div>
            <div className="right-side">
                <div className="form-container">
                    <PublicarProductoForm />
                </div>
            </div>
        </div>
    );
}

export default PublicarProductoPage;