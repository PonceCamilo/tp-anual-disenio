import React from "react";
import '../assets/styles/CustomInfoApp.css';
import FormasColaborarGrid from './FormasColaborarGrid';
function InfoApp({ isVisible }) {
    return (
      <div className={`info-container d-flex flex-column justify-content-center align-items-center ${isVisible ? 'visible' : ''}`}>
        <p className='manrope-font text-wrap fs-3 fw-normal text-break w-50 text-center'>
          Más del 10% de los argentinos viven en situación de calle, y muchos de ellos no pueden cumplir con sus necesidades alimenticias. Te invitamos a ayudar a estas personas de la siguiente manera:
        </p>
        <FormasColaborarGrid />
      </div>
    );
}

export default InfoApp;
