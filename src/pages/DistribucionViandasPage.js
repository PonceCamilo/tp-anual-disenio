import React from 'react';
import DistribucionViandasForm from '../components/DistribucionViandasForm';
import '../assets/styles/DistribucionViandasPage.css';

const DistribucionViandasPage = () => {
    return (
        <div className="distribucion-viandas-page d-flex">
            <div className="left-side">
                <h1>Distribución de Viandas</h1>
                <p>Realiza la distribución de viandas entre heladeras completando el formulario a continuación.</p>
            </div>
            <div className="right-side">
                <DistribucionViandasForm />
            </div>
        </div>
    );
};

export default DistribucionViandasPage;