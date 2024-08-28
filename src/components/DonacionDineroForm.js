import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import MercadoPago from '../assets/iconos/MercadoPago.svg';
import PayPal from '../assets/iconos/PayPal.svg';
import CreditCard from '../assets/iconos/CreditCar.svg';
function DonacionDineroForm() {
  return (
    <Form className=' d-flex flex-column justify-content-between'>
      <h1 className='mb-5 text-center'>Donación de dinero</h1>
      <Form.Group controlId="monto">
        <Form.Label>Monto:</Form.Label>
        <Form.Control type="number" name="monto" placeholder="Ingrese el monto a donar" />
      </Form.Group>

      <h3 className='mt-4'>Doná con los siguientes métodos de pago:</h3>
      <div className="mt-3 d-flex justify-content-around">
        <img src={MercadoPago} alt="Mercado Pago" />
        <img src={PayPal} alt="PayPal" />
        <img src={CreditCard} alt="Tarjeta de Credito" />
      </div>


      {/* 
      
       Confirmation Modal 
      <Modal show={showModal} onHide={handleModalClose}>
        <Modal.Header closeButton>
          <Modal.Title>Confirmación de Donación</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <p>Estás a punto de donar {donationAmount} {currency}.</p>
          <p>¿Deseas continuar?</p>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleModalClose}>
            Cancelar
          </Button>
          <Button variant="primary" onClick={handleModalClose}>
            Confirmar Donación
          </Button>
        </Modal.Footer>
      </Modal>
    </Form>
      
      ---------- Progress Bar-------------
     <div className="mt-4">
        <ProgressBar now={60} label={`$600 recaudados`} />
      </div>

      <Button variant="primary" type="submit" className="mt-4">Donar</Button>
    </Form>
    */ }


<button className='btn btn-outline-dark btn-lg mt-4'>Donar</button>
    </Form>
  );
}

export default DonacionDineroForm;

