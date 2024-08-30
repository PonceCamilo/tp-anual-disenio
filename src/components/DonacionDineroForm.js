import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import MercadoPago from "../assets/iconos/MercadoPago.svg";
import PayPal from "../assets/iconos/PayPal.svg";
import CreditCard from "../assets/iconos/CreditCard.svg"; // Fixed typo from "CreditCar.svg"
import "../assets/styles/DonacionDineroForm.css";

function DonacionDineroForm() {
  return (
    <Form className="donacion-dinero-form">
      <h1 className="form-title text-center">Donación de dinero</h1>

      <Form.Group controlId="monto" className="form-group">
        <Form.Label>Monto:</Form.Label>
        <Form.Control
          type="number"
          name="monto"
          placeholder="Ingrese el monto a donar"
          className="form-control"
        />
      </Form.Group>

      <h3 className="payment-methods-title">Doná con los siguientes métodos de pago:</h3>
      <div className="payment-methods d-flex justify-content-around">
        <img src={MercadoPago} alt="Mercado Pago" className="payment-icon" />
        <img src={PayPal} alt="PayPal" className="payment-icon" />
        <img src={CreditCard} alt="Tarjeta de Crédito" className="payment-icon" />
      </div>

      <Button variant="primary" type="submit" className="submit-button btn-lg">
        Donar
      </Button>
    </Form>
  );
}

export default DonacionDineroForm;