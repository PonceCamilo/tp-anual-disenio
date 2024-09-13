import React, { useState, useEffect } from "react";
import DonacionDineroForm from "../components/DonacionDineroForm";
import "../assets/styles/DonacionDineroPage.css";
import '../assets/styles/App.css';
function DonacionDineroPage() {
  const [animationClasses, setAnimationClasses] = useState({
    left: "",
    right: ""
  });

  useEffect(() => {
    const timer = setTimeout(() => {
      setAnimationClasses({
        left: "animate-slide-in-top",
        right: "animate-slide-in-bottom"
      });
    }, 100); 
    return () => clearTimeout(timer);
  }, []); 

  return (
    <div className="donacion-dinero-page">
      <div className={`left-side ${animationClasses.left}`}>
        <h1>Donar Dinero</h1>
        <p>
          Con tu ayuda, podemos marcar una diferencia. Cualquier cantidad que
          puedas donar es invaluable para nuestro esfuerzo continuo.
        </p>
      </div>
      <div className={`right-side ${animationClasses.right}`}>
        <DonacionDineroForm />
      </div>
    </div>
  );
}

export default DonacionDineroPage;
