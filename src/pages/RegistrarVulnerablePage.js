import React from "react";
import VulnerableForm from "../components/VulnerableForm";
import "../assets/styles/RegistrarVulnerablePage.css";

function RegistrarVulnerablePage() {
  return (
    <div className="registrar-vulnerable-page">
      <div className="left-side">
        <h1>Registrar Persona Vulnerable</h1>
        <p>
          Cada registro ayuda a construir un futuro mejor para quienes más lo necesitan. Tu acción es fundamental para nuestro esfuerzo continuo.
        </p>
      </div>
      <div className="registrar-vulnerable-form">
        <VulnerableForm />
      </div>
    </div>
  );
}

export default RegistrarVulnerablePage;