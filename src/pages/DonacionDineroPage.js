// DonacionDineroPage.js
import DonacionDineroForm from "../components/DonacionDineroForm";
import "../assets/styles/DonacionDineroPage.css";

function DonacionDineroPage() {
  return (
    <div className="donacion-dinero-page">
      <div className="left-side">
        <h1>Donar Dinero</h1>
        <p>
          Con tu ayuda, podemos marcar una diferencia. Cualquier cantidad que
          puedas donar es invaluable para nuestro esfuerzo continuo.
        </p>
      </div>
      <div className="right-side">
        <DonacionDineroForm />
      </div>
    </div>
  );
}

export default DonacionDineroPage;