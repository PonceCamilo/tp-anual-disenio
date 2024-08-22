import React from "react";
import '../assets/styles/CustomInfoApp.css';
function InfoApp({ isVisible }) {
    return (
      <div className={`info-container d-flex flex-column justify-content-center align-items-center ${isVisible ? 'visible' : ''}`}>
        <p className='manrope-font text-wrap fs-3 fw-normal text-break w-50 text-center'>
        Más del X% de los argentinos viven en situación de calle, y muchos de ellos no pueden cumplir con sus necesidades alimenticias. Te invitamos a ayudar a estas personas de la siguiente manera:
        </p>
      </div>
    );
  }
export default InfoApp;