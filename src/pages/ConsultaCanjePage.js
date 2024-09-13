import React from 'react';
import ConsultaCanjeForm from '../components/ConsultaCanjeForm';
import '../assets/styles/ConsultaCanjePage.css';
import '../assets/styles/App.css';
function ConsultaCanjePage() {
    return (
        <div className="consulta-canjes-page d-flex flex-column align-items-center justify-content-center">
            <h1 className="page-title">Consulta y Canje de Productos</h1>
            <ConsultaCanjeForm />
        </div>
    );
}

export default ConsultaCanjePage;