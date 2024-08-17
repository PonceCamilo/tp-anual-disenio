import React, { useState } from 'react';
import Modal from 'react-bootstrap/Modal';

function LoadCSVModal({ show, onHide }) {

    const [file, setFile] = useState(null);

    const handleFileChange = (event) => {
        setFile(event.target.files[0]);        
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        const formData = new FormData();
        formData.append('file', file);

        try {
            const response = await fetch('http://localhost:8080/cargaCSV/cargarCSV', {
              method: 'POST',
              body: formData,
            });
        
            if (!response.ok) {
              throw new Error(`HTTP error. Status: ${response.status}`);
            }
        
            const data = await response.text();
            alert(data);
          } catch (error) {
            console.error('Error:', error);
            alert('Error al cargar el archivo');
          }
    };

    return (
        <Modal show={show} onHide={onHide}>
            <Modal.Header closeButton>
                <Modal.Title>Cargar archivo CSV</Modal.Title>
            </Modal.Header>
            <Modal.Body className="d-flex flex-column align-items-center gap-3">
                <input type='file' accept='.csv' onChange={handleFileChange} className="form-control form-control-sm"/>
                <div className="d-flex justify-content-center">
                    <button onClick={handleSubmit} className="btn btn-outline-dark btn-lg">Cargar</button>
                </div>
            </Modal.Body>
        </Modal>
    );

}

export default LoadCSVModal;