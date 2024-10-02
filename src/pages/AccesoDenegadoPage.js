import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../assets/styles/AccesoDenegadoPage.css'; 
import '../assets/styles/App.css';
const AccesoDenegadoPage = () => {
  const navigate = useNavigate();

  return (
    <div className="denied-page">
      <h1>¿Qué estás intentando flaco?</h1>
      <p>Parece que estás tratando de entrar a un lugar al que no tienes acceso...</p>
      <div className="emoji">🚫🙅‍♂️🚪</div>
      <p>Este no es tu terreno. Mejor volvé a donde sí te dejan entrar.</p>
      <button className="back-button" onClick={() => navigate('/')}>
        Volver a lo seguro
      </button>
    </div>
  );
};

export default AccesoDenegadoPage;
