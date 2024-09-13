import React, { useState } from 'react';
import '../assets/styles/ReportIssueForm.css';
import '../assets/styles/App.css';
import {  Button } from 'react-bootstrap';
const ReportIssueForm = ({ collaborators, fridges }) => {
    const [date, setDate] = useState(new Date().toISOString().slice(0, 16));
    const [fridge, setFridge] = useState('');
    const [description, setDescription] = useState('');
    const [photo, setPhoto] = useState(null);

    const handleSubmit = async (e) => {
        e.preventDefault();

        const formData = new FormData();
        formData.append('date', date);
        formData.append('fridge', fridge);
        formData.append('description', description);
        if (photo) {
            formData.append('photo', photo);
        }

        try {
            const response = await fetch('/api/report-issue', {
                method: 'POST',
                body: formData,
            });
            if (response.ok) {
                console.log('Reporte enviado con éxito');
            } else {
                console.log('Error al enviar el reporte');
            }
        } catch (error) {
            console.error('Error:', error);
        }

        // Reiniciar formulario
        setDate(new Date().toISOString().slice(0, 16));
        setFridge('');
        setDescription('');
        setPhoto(null);
    };

    return (
        <form className="report-issue-form" onSubmit={handleSubmit}>
            <h2>Reportar Fallas Técnicas</h2>

            <label>
                Fecha y Hora:
                <input
                    type="datetime-local"
                    value={date}
                    onChange={(e) => setDate(e.target.value)}
                    required
                />
            </label>

            <label>
                Heladera:
                <select
                    value={fridge}
                    onChange={(e) => setFridge(e.target.value)}
                    required
                >
                    <option value="">Seleccione una heladera</option>
                    {fridges.map((f) => (
                        <option key={f.id} value={f.id}>{f.name}</option>
                    ))}
                </select>
            </label>

            <label>
                Descripción:
                <textarea
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    required
                ></textarea>
            </label>

            <label>
                Foto:
                <input
                    type="file"
                    accept="image/*"
                    onChange={(e) => setPhoto(e.target.files[0])}
                />
            </label>

            <Button type="submit">Enviar Reporte</Button>
        </form>
    );
};

export default ReportIssueForm;