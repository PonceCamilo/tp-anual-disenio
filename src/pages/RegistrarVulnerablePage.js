import React from "react";
import VulnerableForm from "../components/VulnerableForm";
import "../assets/styles/RegistrarVulnerable.css";
function RegistrarVulnerablePage() {
  return (
    <div className="registrar-vulnerable-page">
      <h2>Registrar persona vulnerable</h2>
      <div className="consulta-canjes-form-container">
        <VulnerableForm />
      </div>
    </div>
  );
}
export default RegistrarVulnerablePage;