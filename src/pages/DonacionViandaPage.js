import React from 'react';
import DonacionViandaForm from '../components/DonacionViandaForm';
import '../assets/styles/DonacionViandaPage.css';
import '../assets/styles/App.css';
const DonacionViandaPage = () => {
    return (
        <div className="donacion-vianda-page d-flex">
            <div className="left-side">
                <h1>Donar Vianda</h1>
                <p>Con tu ayuda, podemos alimentar a quienes más lo necesitan. Completa el formulario para donar comida saludable y fresca.</p>
            </div>
            <div className="right-side">
                <DonacionViandaForm />
            </div>
        </div>
    );
};

export default DonacionViandaPage;