import React from 'react';
import CargarHeladeraForm from '../components/CargarHeladeraForm';
import '../assets/styles/CargarHeladeraPage.css';

function CargarHeladeraPage() {
    return (
        <div className="cargar-heladera-page d-flex flex-column align-items-center justify-content-center">
            <h1 className="page-title">Añadir Nueva Heladera</h1>
            <CargarHeladeraForm />
        </div>
    );
}

export default CargarHeladeraPage;