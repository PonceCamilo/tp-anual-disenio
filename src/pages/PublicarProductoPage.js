import React from 'react';
import PublicarProductoForm from '../components/PublicarProductoForm';
import '../assets/styles/PublicarProductoPage.css';

function PublicarProductoPage() {
    return (
        <div className="publicar-producto-page ">
            <div className="form-container">
                <PublicarProductoForm />
            </div>
        </div>
    );
}

export default PublicarProductoPage;